(ns alandipert.boot-yeti
  {:boot/export-tasks true}
  (:require [boot.pod  :as pod]
            [boot.core :as core]
            [boot.util :as util]))

(def ^:private yeti-env
  {:repositories ["yeti-dropbox"
                  {:url "https://www.dropbox.com/sh/7f1kxv4ckzy3wy5/AADaZfIiExGJ6LYX9sDbfb-Za"
                   :checksum :ignore}]
   :dependencies '[yeti "0.9.9"]})

(core/deftask yeti
  "Compile yeti files"
  []
  (let [tgt         (core/temp-dir!)
        compile-env (merge-with conj (core/get-env) yeti-env)
        compile-pod (pod/make-pod compile-env)]
    (core/with-pre-wrap fileset
      (core/empty-dir! tgt)
      (pod/with-eval-in @compile-pod
        (util/info "Compiling yeti sources...")
        (yeti.lang.compiler.yeti (into-array ["-d" ~(.getPath tgt)])))
      (-> fileset (core/add-resource tgt) core/commit!))))
