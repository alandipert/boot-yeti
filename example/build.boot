(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti     "1.0.0-1" :scope "test"]
                 [alandipert/boot-trinkets "2.0.0"   :scope "test"]
                 [alandipert/yeti-lib      "0.9.9.1" :scope "runtime"]])

(require '[alandipert.boot-yeti :refer [yeti]]
         '[alandipert.boot-trinkets :refer [run]])
