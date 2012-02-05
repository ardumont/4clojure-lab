(ns ^{:doc "http://www.4clojure.com/problem/39#prob-title"}
  my-4clojure-lab.49.core39
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))




;; Write a function which takes two sequences and returns
;; the first item from each, then the second item from each,
;; then the third, etc.

(defn my-interleave "My interleave implementation"
  [s0 s1]
  (mapcat list s0 s1))

(fact 
  (my-interleave [1 2 3] [:a :b :c]) => '(1 :a 2 :b 3 :c)
  (my-interleave [1 2] [3 4 5 6]) => '(1 3 2 4)
  (my-interleave [1 2 3 4] [5]) => [1 5]
  (my-interleave [30 20] [25 15]) => [30 25 20 15])


