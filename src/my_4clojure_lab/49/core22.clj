(ns ^{:doc "http://www.4clojure.com/problem/22#prob-title"}
  my-4clojure-lab.49.core22
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pp pprint]]))

;; Write a function which returns the total number of elements in a sequence.



(defn method-22 "Count the total number of elements in a sequence"
  [s]
  (.size (seq s)))

(fact
  (method-22 '(1 2 3 3 1)) => 5
  (method-22 "Hello World") => 11
  (method-22 [[1 2] [3 4] [5 6]]) => 3
  (method-22 '(13)) => 1
  (method-22 '(:a :b :c)) => 3)



