(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti "0.9.9-0" :scope "test"]
                 [yeti/yeti-lib        "0.9.9"   :scope "runtime"]]
 :repositories '[["yeti-dropbox" "http://tailrecursion.com/~alan/yeti-repo/"]])

(require
 '[alandipert.boot-yeti :refer [yeti]])

(task-options!
 pom {:project 'yeti-example
      :version "1.0.0"}
 jar {:main 'fac.Main})

(deftask build
  "Build a jar."
  []
  (set-env! :dependencies )
  (comp (pom)
        (yeti)
        (uber)
        (jar)))
