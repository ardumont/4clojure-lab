(ns ^{:doc "http://www.4clojure.com/problem/34#prob-title"}
  my-4clojure-lab.core-pal
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN pal  ----------" (java.util.Date.))

;; Palindrome detector

(defn pal? "My range function implementation - start inclusive, end exclusive"
  [s]
  )

(fact
  (pal? [1 2 3]) => falsey
  (pal? [1 2 1]) => truthy)


(println "--------- END pal  ----------" (java.util.Date.))
