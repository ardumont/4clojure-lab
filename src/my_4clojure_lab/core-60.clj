(ns ^{:doc "http://www.4clojure.com/problem/60#prob-title"}
  my-4clojure-lab.core-60
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 60  ----------" (java.util.Date.))

;; Write a function which behaves like reduce, but returns each
;; intermediate value of the reduction. Your function must accept
;; either two or three arguments, and the return sequence must be
;; lazy.

(defn mred "Reductions implementation."
  ([f v])
  ([f i v]))

(fact
  (take 5 (mred + (range))) => [0 1 3 6 10]
  (mred conj [1] [2 3 4]) => [[1] [1 2] [1 2 3] [1 2 3 4]])

(future-fact
 (last (mred * 2 [3 4 5])) => 120)

(println "--------- END 60  ----------" (java.util.Date.))
