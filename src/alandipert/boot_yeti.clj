(ns alandipert.boot-yeti
  {:boot/export-tasks true}
  (:require [boot.pod  :as pod]
            [boot.core :as core]
            [boot.util :as util]
            [clojure.java.io :as io])
  (:refer-clojure :exclude [compile]))

(def ^:private version "0.9.9")

(def ^:private compile-env
  {:dependencies [['alandipert/yeti version]
                  ['jline "0.9.94"]]})

(def ^:private run-env
  {:dependencies [['alandipert/yeti-lib version]]})

(core/deftask yeti
  "Compile Yeti source files or use the REPL."
  [r repl bool "Run the Yeti REPL with JLine support"]
  (let [tgt         (core/temp-dir!)
        env         (merge-with into (core/get-env) compile-env)
        compile-pod (future (pod/make-pod env))
        yeti-sm     (proxy [SecurityManager] []
                      (checkPermission [_])
                      (checkExit [status] (throw (SecurityException.))))]
    (fn [next-handler]
      (fn [fileset]
        (if repl
          ;; Run REPL with JLine
          (pod/with-eval-in @compile-pod
            (jline.ConsoleRunner/main (into-array ["yeti.lang.compiler.yeti"])))
          ;; Compile files
          (let [yeti-files  (->> fileset
                                 core/input-files
                                 (core/by-ext [".yeti"]))
                yeti-argv   (->> (map #(.getPath (core/tmpfile %)) yeti-files)
                                 (list* "-d" (.getPath tgt))
                                 vec)]
            (core/empty-dir! tgt)
            (util/info "Compiling Yeti sources...\n")
            (when (util/without-exiting
                   (pod/with-eval-in @compile-pod
                     (yeti.lang.compiler.yeti/main (into-array ~yeti-argv)))
                   true)
              (-> fileset
                  (core/add-resource tgt)
                  (core/rm yeti-files)
                  core/commit!
                  next-handler))))))))
