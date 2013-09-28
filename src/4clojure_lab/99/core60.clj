(ns ^{:doc "http://www.4clojure.com/problem/60#prob-title"}
  4clojure-lab.99.core60
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function which behaves like reduce, but returns each
;; intermediate value of the reduction. Your function must accept
;; either two or three arguments, and the return sequence must be
;; lazy.

(defn mred "Reductions implementation."
  ([g [f & r]] (mred g f r))
  ([g i [f & r :as v]]
     (cons i
           (lazy-seq
            (if (seq v)
              (mred g (g i f) r))))))

(fact
  (take 5 (mred + (range))) => [0 1 3 6 10]
  (mred conj [1] [2 3 4]) => [[1] [1 2] [1 2 3] [1 2 3 4]]
   (last (mred * 2 [3 4 5])) => 120)
