(ns ^{:doc "http://www.4clojure.com/problem/115#prob-title"}
  clojure4-lab.149.core115
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; A balanced number is one whose component digits have the same sum on the left and right halves of the number. Write a
;; function which accepts an integer n, and returns true iff n is balanced.

(defn balance? "Is the number balanced?"
  [n]
  (let [c #(apply + %)
        i (map int (str n))
        h (quot (count i) 2)]
    (=  (c (take h i)) (c (take h (reverse i))))))

(fact
  (balance? 11)    => true
  (balance? 121)   =>  true
  (balance? 123)   =>  false
  (balance? 0)     =>  true
  (balance? 88099) =>  false
  (balance? 89098) =>  true
  (balance? 89089) =>  true
  (take 20 (filter balance? (range))) => [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])
