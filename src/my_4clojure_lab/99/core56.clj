(ns ^{:doc "http://www.4clojure.com/problem/55#prob-title"}
  my-4clojure-lab.99.core56
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which removes the duplicates from a sequence.
;; Order of the items must be maintained.

(defn my-distinct "Distinct implementation"
  [s]
  (reduce #(if ((set %) %2) % (conj % %2)) [] s))

(fact 
  (my-distinct [1 2 1 3 1 2 4]) => [1 2 3 4]
  (my-distinct [:a :a :b :b :c :c]) => [:a :b :c]
  (my-distinct '([2 4] [1 2] [1 3] [1 3])) => '([2 4] [1 2] [1 3])
  (my-distinct (range 50)) => (range 50))


