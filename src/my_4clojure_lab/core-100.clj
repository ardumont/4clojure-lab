(ns ^{:doc "http://www.4clojure.com/problem/100#prob-title"}
  my-4clojure-lab.core-100
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 100  ----------" (java.util.Date.))

;; Write a function which calculates the least common multiple. Your
;; function should accept a variable number of positive integers or ratios.

(defn lcm "Least common multiple"
  [f & r]
  (letfn [(g [a b] (if (= 0 b) a (g b (mod a b))))]
    (reduce #(/ (* % %2) (g % %2)) f r)))

(fact 
  (lcm 2 3) => 6
  (lcm 5 3 7) => 105
  (lcm 1/3 2/5) => 2
  (lcm 3/4 1/6) => 3/2
  (lcm 7 5/7 2 3/5) => 210)

(println "--------- END 100  ----------" (java.util.Date.))