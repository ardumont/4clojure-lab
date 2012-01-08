(ns ^{:doc "http://www.4clojure.com/problem/55#prob-title"}
  my-4clojure-lab.core-56
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 56  ----------" (java.util.Date.))

;; Write a function which removes the duplicates from a sequence.
;; Order of the items must be maintained.

(defn my-distinct "Distinct implementation"
  [s]
  (into #{} s))

(fact 
  (my-distinct [1 2 1 3 1 2 4]) => [1 2 3 4]
  (my-distinct [:a :a :b :b :c :c]) => [:a :b :c]
  (my-distinct '([2 4] [1 2] [1 3] [1 3])) => '([2 4] [1 2] [1 3])
  (my-distinct (range 50)) => (range 50))

(println "--------- END 56  ----------" (java.util.Date.))
