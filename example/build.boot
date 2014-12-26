(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti "0.9.9-4" :scope "test"]
                 [yeti/yeti-lib        "0.9.9"   :scope "runtime"]]
 :repositories '[["yeti-repo" "https://dl.dropboxusercontent.com/u/12379861/yeti-repo/"]])

(require '[alandipert.boot-yeti :refer [yeti run]])
