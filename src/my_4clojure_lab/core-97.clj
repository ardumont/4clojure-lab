(ns ^{:doc "http://www.4clojure.com/problem/97#prob-title"}
  my-4clojure-lab.core-97
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 97  ----------" (java.util.Date.))

;; Pascal's triangle is a triangle of numbers computed using the following rules:

;; - The first row is 1.
;; - Each successive row is computed by adding together adjacent numbers in the row above, and adding a 1 to the beginning and end of the row.

;; Write a function which returns the nth row of Pascal's Triangle.

(defn triangle ""
  [n]
  (if (= 1 n) [1]
      (let [t (triangle (dec n))]
        (concat [1] (map + t (rest t)) [1]))))

(fact 
  (triangle 1) => [1]
  (triangle 2) => [1 1]
  (triangle 3) => [1 2 1]
  (map triangle (range 1 6)) => [[1]
                                 [1 1]
                                 [1 2 1]
                                 [1 3 3 1]
                                 [1 4 6 4 1]]
  (triangle 11) => [1 10 45 120 210 252 210 120 45 10 1])

(println "--------- END 97  ----------" (java.util.Date.))
