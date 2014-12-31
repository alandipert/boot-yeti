(set-env!
  :dependencies '[[org.clojure/clojure      "1.6.0"     :scope "provided"]
                  [boot/core                "2.0.0-rc4" :scope "provided"]
                  [adzerk/bootlaces         "0.1.8"     :scope "test"]
                  [alandipert/boot-trinkets "1.0.0"     :scope "test"]]
  :source-paths #{"src"})

(require '[adzerk.bootlaces :refer :all]
         '[alandipert.boot-yeti :refer [yeti]])

(def +version+ "0.9.9-7")

(bootlaces! +version+)

(task-options!
 pom  {:project     'alandipert/boot-yeti
       :version     +version+
       :description "Boot task to compile Yeti."
       :url         "https://github.com/alandipert/boot-yeti"
       :scm         {:url "https://github.com/alandipert/boot-yeti"}
       :license     {:name "Eclipse Public License"
                     :url  "http://www.eclipse.org/legal/epl-v10.html"}})
