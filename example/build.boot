(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti "0.9.9-3" :scope "test"]
                 [yeti/yeti-lib        "0.9.9"   :scope "runtime"]]
 :repositories '[["yeti-repo" "https://dl.dropboxusercontent.com/u/12379861/yeti-repo/"]])

(require
 '[alandipert.boot-yeti :refer [yeti run]])

(deftask develop
  "Continuously compile and run the Yeti program."
  []
  (comp (watch)
        (yeti)
        (run :main 'fac.Main :args ["5"])))

(deftask build
  "Compile Yeti sources and build a jar."
  []
  (comp (yeti)
        (uber)
        (jar :main 'fac.Main)))
