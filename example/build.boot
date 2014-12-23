(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti "0.9.9-0" :scope "test"]
                 [yeti/yeti-lib        "0.9.9"   :scope "runtime"]]
 :repositories '[["yeti-repo" "http://tailrecursion.com/~alan/yeti-repo/"]])

(require
 '[alandipert.boot-yeti :refer [yeti]])

(deftask build
  "Compile Yeti sources and build a jar."
  []
  (comp (pom :project 'yeti-example
             :version "1.0.0")
        (yeti)
        (uber)
        (jar :main 'fac.Main)))
