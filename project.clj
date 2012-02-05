(defproject my-4clojure-lab "1.0.0-SNAPSHOT"
  :description "My-4clojure-lab - reference my experimentation to solve the problems."
  :dependencies [[org.clojure/clojure "1.3.0"]]
  :dev-dependencies [[midje           "1.3.1"]
                     [lein-midje      "1.0.8"]
                     [lein-marginalia "0.7.0-SNAPSHOT"]
                     [com.intelie/lazytest "1.0.0-SNAPSHOT" :exclusions [swank-clojure]]])