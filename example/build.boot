(set-env!
 :source-paths #{"src"}
 :dependencies '[[alandipert/boot-yeti     "1.0.0-0"        :scope "test"]
                 [alandipert/boot-trinkets "1.0.0"          :scope "test"]
                 [alandipert/yeti-lib      "1.0.0-SNAPSHOT" :scope "runtime"]])

(require '[alandipert.boot-yeti :refer [yeti]]
         '[alandipert.boot-trinkets :refer [run]])
