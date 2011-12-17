(ns ^{:doc "http://www.4clojure.com/problem/41#prob-title"}
  my-4clojure-lab.core-41
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 41  ----------" (java.util.Date.))

;; Write a function which drops every Nth item from a sequence.

(defn drop-nth "drops every nth item from a sequence."
  [s n]
  (for [x (range 0 (count s)) :when (< 0 (rem (inc x) n))] (s x)))

(fact
  (drop-nth [1 2 3 4 5 6 7 8] 3)   => [1 2 4 5 7 8]
  (drop-nth [:a :b :c :d :e :f] 2) => [:a :c :e]
  (drop-nth [1 2 3 4 5 6] 4)       => [1 2 3 5 6])

(println "--------- END 41  ----------" (java.util.Date.))
[:A :a :B][:A :a :B]