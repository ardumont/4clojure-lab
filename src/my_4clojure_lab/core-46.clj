(ns ^{:doc "http://www.4clojure.com/problem/46#prob-title"}
  my-4clojure-lab.core-46
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 46  ----------" (java.util.Date.))

;; Write a higher-order function which flips the order of the
;; arguments of an input function.

(defn flip "Flips the order of the arguments of an input function."
  [f]
  #(f %2 %))

(fact 
  ((flip nth) 2 [1 2 3 4 5]) => 3
  ((flip >) 7 8) => true
  ((flip quot) 2 8) => 4
  ((flip take) [1 2 3 4 5] 3) => [1 2 3])

(println "--------- END 46  ----------" (java.util.Date.))
