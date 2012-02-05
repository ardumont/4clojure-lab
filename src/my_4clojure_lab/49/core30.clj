
(ns ^{:doc "http://www.4clojure.com/problem/30#prob-title"}
  my-4clojure-lab.49.core30
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which removes consecutive duplicates from a sequence.

(defn rem-dup "Remove duplicate entries from a sequence"
  [s]
  (map first (partition-by identity s)))

(fact 
  (apply str (rem-dup "Leeeeeerrroyyy")) => "Leroy"
  (rem-dup [1 1 2 3 3 2 2 3]) => '(1 2 3 2 3)
  (rem-dup [[1 2] [1 2] [3 4] [1 2]]) => '([1 2] [3 4] [1 2]))


