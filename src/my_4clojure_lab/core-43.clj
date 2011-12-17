(ns ^{:doc "http://www.4clojure.com/problem/43#prob-title"}
  my-4clojure-lab.core-43
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 43  ----------" (java.util.Date.))

;; Write a function which reverses the interleave process into x number of subsequences.

(defn rev-int "Reverses the interleave process into x number of subsequences."
  [s n]
  (for [x (range n)] (map #(nth % x) (partition n s))))

(fact
  (rev-int [1 2 3 4 5 6] 2) => '((1 3 5) (2 4 6))
  (rev-int (range 9) 3) => '((0 3 6) (1 4 7) (2 5 8))
  (rev-int (range 10) 5) => '((0 5) (1 6) (2 7) (3 8) (4 9)))

(println "--------- END 43  ----------" (java.util.Date.))