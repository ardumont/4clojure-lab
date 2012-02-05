(ns ^{:doc "http://www.4clojure.com/problem/32#prob-title"}
  my-4clojure-lab.49.core32
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which duplicates each element of a sequence.

(defn dup "Duplicate each element of the sequence"
  [s]
  (for [x s, y [x x]] y))

(fact
  (dup [1 2 3]) => '(1 1 2 2 3 3)
  (dup [:a :a :b :b]) => '(:a :a :a :a :b :b :b :b)
  (dup [[1 2] [3 4]]) => '([1 2] [1 2] [3 4] [3 4])
  (dup [[1 2] [3 4]]) => '([1 2] [1 2] [3 4] [3 4]))


