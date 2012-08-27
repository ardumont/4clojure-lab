(defproject my-4clojure-lab/my-4clojure-lab "1.0.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :profiles {:dev
             {:dependencies
              [[com.intelie/lazytest
                "1.0.0-SNAPSHOT"
                :exclusions
                [swank-clojure]]]}}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [midje "1.4.0"]]
  :description "My-4clojure-lab - reference my experimentations to solve the problems.")
