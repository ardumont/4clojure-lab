(ns ^{:doc "http://www.4clojure.com/problem/115#prob-title"}
  my-4clojure-lab.core-115
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 115  ----------" (java.util.Date.))

;; A balanced number is one whose component digits have the same sum on the left and right halves of the number. Write a
;; function which accepts an integer n, and returns true iff n is balanced.

(defn balance? "Is the number balanced?"
  [n]
  (let [s (str n)]
    (if (= (seq s) (seq (reverse s)))
      true
      (let [c (fn [m v] (reduce #(+ % (m %2)) 0 v))
            m (zipmap "0123456789" (range 10))
            h (Math/floor (/ (count s) 2))]
         (=  (c m (take (inc h) s)) (c m (drop h s)))))))

(fact
  (balance? 11)    => true
  (balance? 121)   =>  true
  (balance? 123)   =>  false
  (balance? 0)     =>  true
  (balance? 88099) =>  false
  (balance? 89098) =>  true
  (balance? 89089) =>  true
  (take 20 (filter balance? (range))) => [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])

(println "--------- END 115  ----------" (java.util.Date.))
