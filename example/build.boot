(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti     "0.9.9-7" :scope "test"]
                 [alandipert/boot-trinkets "1.0.0"   :scope "test"]
                 [alandipert/yeti-lib      "0.9.9"   :scope "runtime"]])

(require '[alandipert.boot-yeti :refer [yeti]]
         '[alandipert.boot-trinkets :refer [run]])
