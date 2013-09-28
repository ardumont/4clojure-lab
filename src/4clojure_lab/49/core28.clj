(ns ^{:doc "http://www.4clojure.com/problem/28#prob-title"}
  4clojure-lab.49.core28
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which flattens a sequence.

(defn my-flatten "flatten implementation"
  [s]
  (if (coll? s) (mapcat my-flatten s) [s]))

(fact
  (my-flatten '((1 2) 3 [4 [5 6]])) => '(1 2 3 4 5 6)
  (my-flatten ["a" ["b"] "c"]) => '("a" "b" "c")
  (my-flatten '((((:a))))) => '(:a))
