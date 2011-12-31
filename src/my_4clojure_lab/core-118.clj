(ns ^{:doc "http://www.4clojure.com/problem/118#prob-title"}
  my-4clojure-lab.core-118
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 118  ----------" (java.util.Date.))

;; Map is one of the core elements of a functional programming
;; language. Given a function f and an input sequence s, return a lazy
;; sequence of (f x) for each element x in s.

(defn mmap "Map implementation"
  [f [e & r]]
  (if (nil? e)
    nil
    (cons (f e) (lazy-seq (mmap f r)))))

(fact 
  (mmap inc [2 3 4 5 6]) => [3 4 5 6 7]
  (mmap (fn [_] nil) (range 10)) => (repeat 10 nil)
  (->> (map inc (range)) (drop (dec 1e6)) (take 2)) => [1e6 (inc 1e6)])

(println "--------- END 118  ----------" (java.util.Date.))
