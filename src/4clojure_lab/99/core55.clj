(ns ^{:doc "http://www.4clojure.com/problem/55#prob-title"}
  4clojure-lab.99.core55
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which returns a map containing the number of
;; occurences of each distinct item in a sequence.

(defn occurences ""
  [s]
  (reduce #(assoc % %2 (+ 1 (% %2 0))) {} s))

(fact
  (occurences [1 1 2 3 2 1 1]) => {1 4, 2 2, 3 1}
  (occurences [:b :a :b :a :b]) => {:a 2, :b 3}
  (occurences '([1 2] [1 3] [1 3])) => {[1 2] 1, [1 3] 2})
