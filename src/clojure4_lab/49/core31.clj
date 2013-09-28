(ns ^{:doc "http://www.4clojure.com/problem/31#prob-title"}
  clojure4-lab.49.core31
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which packs consecutive duplicates into sub-lists.

(defn pack "packs consecutive duplicates into sub-lists."
  [s] (partition-by identity s))

(fact
  (pack [1 1 2 1 1 1 3 3]) => '((1 1) (2) (1 1 1) (3 3))
  (pack [:a :a :b :b :c]) => '((:a :a) (:b :b) (:c))
  (pack [[1 2] [1 2] [3 4]]) => '(([1 2] [1 2]) ([3 4])))
