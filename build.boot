(set-env!
 :dependencies '[[adzerk/bootlaces "0.1.12" :scope "test"]]
 :source-paths #{"src"})

(require '[adzerk.bootlaces :refer :all]
         '[alandipert.boot-yeti :refer [yeti]])

(def +version+ "1.0.0-1")

(bootlaces! +version+)

(task-options!
 pom  {:project     'alandipert/boot-yeti
       :version     +version+
       :description "Boot task to compile Yeti."
       :url         "https://github.com/alandipert/boot-yeti"
       :scm         {:url "https://github.com/alandipert/boot-yeti"}
       :license     {"Eclipse Public License"
                     "http://www.eclipse.org/legal/epl-v10.html"}})
