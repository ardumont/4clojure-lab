(ns ^{:doc "http://www.4clojure.com/problem/34#prob-title"}
  my-4clojure-lab.core-34
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 34  ----------" (java.util.Date.))

;; Write a function that creates a link of all integers in a certain
;; range.

(defn my-range "My range function implementation - start inclusive, end exclusive"
  [start end]
  (take (- end start)
        (iterate inc start)))

(fact
  (my-range 1 4) => '(1 2 3)
  (my-range -2 2) => '(-2 1 0 1)
  (my-range 5 8) => '(5 6 7))


(println "--------- END 34  ----------" (java.util.Date.))
