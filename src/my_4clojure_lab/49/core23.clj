(ns ^{:doc "http://www.4clojure.com/problem/23#prob-title"}
  my-4clojure-lab.49.core23
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pp pprint]]))

;; Write a function which reverses a sequence.



(defn rev "Reverse"
  [s] (reduce #(cons %2 %1) [] s)) 

(fact
  (rev [1 2 3 4 5]) => [5 4 3 2 1]
  (rev (sorted-set 5 7 2 7)) => '(7 5 2)
  (rev [[1 2][3 4][5 6]]) => [[5 6][3 4][1 2]])


