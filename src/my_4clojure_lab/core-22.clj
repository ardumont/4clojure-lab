(ns ^{:doc "http://www.4clojure.com/problem/34#prob-title"}
  my-4clojure-lab.core-22
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pp pprint]]))

;; Write a function which returns the total number of elements in a sequence.

(println "--------- BEGIN 34  ----------" (java.util.Date.))

(defn method-22 "Count the total number of elements in a sequence"
  [s]
  (apply + (map #(when %1 1) s)))

(fact
  (method-22 '(1 2 3 3 1)) => 5
  (method-22 "Hello World") => 11
  (method-22 [[1 2] [3 4] [5 6]]) => 3
  (method-22 '(13)) => 1
  (method-22 '(:a :b :c)) => 3)

(println "--------- END 34  ----------" (java.util.Date.))

