(ns ^{:doc "http://www.4clojure.com/problem/118#prob-title"}
  my-4clojure-lab.149.core118
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Map is one of the core elements of a functional programming
;; language. Given a function f and an input sequence s, return a lazy
;; sequence of (f x) for each element x in s.

(defn mmap "Map implementation"
  [f [e & r]]
  (cons (f e) (lazy-seq (if r (mmap f r)))))

(fact 
  (mmap inc [2 3 4 5 6]) => [3 4 5 6 7]
  (mmap (fn [_] nil) (range 10)) => (repeat 10 nil)
  (->> (mmap inc (range)) (drop (dec 1e6)) (take 2)) => [1000000 1000001])


