(ns alandipert.boot-yeti
  {:boot/export-tasks true}
  (:require [boot.pod  :as pod]
            [boot.core :as core]
            [boot.util :as util]
            [clojure.java.io :as io]
            [alandipert.boot-yeti.util :refer [without-exiting]])
  (:refer-clojure :exclude [compile]))

(def ^:private repo ["yeti-repo" "https://dl.dropboxusercontent.com/u/12379861/yeti-repo/"])
(def ^:private version "0.9.9")

(def ^:private compile-env {:repositories repo :dependencies ['yeti version]})
(def ^:private run-env {:repositories repo :dependencies ['yeti/yeti-lib version]})

(defn- copy [tf dir]
  (let [f (core/tmpfile tf)]
    (io/copy f (doto (io/file dir (:path tf)) io/make-parents))))

(core/deftask run
  "Add classes to a pod and run a main method."
  [m main CLASSNAME sym   "The main class"
   a args ARGUMENTS [str] "String arguments to pass to the main class's main method"]
  (let [classdir (core/temp-dir!)
        runners  (pod/pod-pool (merge-with conj (core/get-env) run-env))]
    (core/with-pre-wrap fileset
      (let [class-files (->> fileset
                             core/output-files
                             (core/by-ext [".class"]))
            main-sym    (symbol (str main) "main")]
        ;; TODO - copy efficiently with fileset diff
        (core/empty-dir! classdir)
        (doseq [tmpfile class-files] (copy tmpfile classdir))
        (pod/with-eval-in (runners :refresh)
          (boot.pod/add-classpath ~(.getPath classdir))
          (~main-sym (into-array String ~(vec args)))))
      fileset)))

(core/deftask yeti
  "Compile Yeti source files."
  []
  (let [tgt         (core/temp-dir!)
        env         (merge-with conj (core/get-env) compile-env)
        compile-pod (future (pod/make-pod env))
        yeti-sm     (proxy [SecurityManager] []
                      (checkPermission [_])
                      (checkExit [status] (throw (SecurityException.))))]
    (fn [next-handler]
      (fn [fileset]
        (let [yeti-files  (->> fileset
                               core/input-files
                               (core/by-ext [".yeti"]))
              yeti-argv   (->> (map #(.getPath (core/tmpfile %)) yeti-files)
                               (list* "-d" (.getPath tgt))
                               vec)]
          (core/empty-dir! tgt)
          (util/info "Compiling Yeti sources...\n")
          (when (without-exiting
                 (pod/with-eval-in @compile-pod
                   (yeti.lang.compiler.yeti/main (into-array ~yeti-argv))))
            (-> fileset
                (core/add-resource tgt)
                (core/rm yeti-files)
                core/commit!
                next-handler)))))))
