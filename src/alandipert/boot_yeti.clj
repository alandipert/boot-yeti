(ns alandipert.boot-yeti
  {:boot/export-tasks true}
  (:require [boot.pod  :as pod]
            [boot.core :as core]
            [boot.util :as util]))

(def ^:private yeti-env
  '{:repositories ["yeti-repo" "http://tailrecursion.com/~alan/yeti-repo/"]
    :dependencies [yeti "0.9.9"]})

(core/deftask yeti
  "Compile Yeti source files."
  []
  (let [tgt         (core/temp-dir!)
        compile-env (merge-with conj (core/get-env) yeti-env)
        compile-pod (future (pod/make-pod compile-env))]
    (core/with-pre-wrap fileset
      (let [yeti-files  (->> fileset
                             core/input-files
                             (core/by-ext [".yeti"]))
            yeti-argv   (->> (map #(.getPath (core/tmpfile %)) yeti-files)
                             (list* "-d" (.getPath tgt))
                             vec)]
        (core/empty-dir! tgt)
        (pod/with-eval-in @compile-pod
          (util/info "Compiling Yeti sources...\n")
          (yeti.lang.compiler.yeti/main (into-array ~yeti-argv)))
        (-> fileset
            (core/add-resource tgt)
            (core/rm yeti-files)
            core/commit!)))))
