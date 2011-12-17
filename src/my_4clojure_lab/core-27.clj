(ns ^{:doc "http://www.4clojure.com/problem/34#prob-title"}
  my-4clojure-lab.core-pal
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN pal  ----------" (java.util.Date.))

;; Palindrome detector

(defn pal? "Is the sequence a palindrome?"
  [s]
  (= (seq s) (reverse s)))

(fact
  (pal? [1 2 3]) => falsey
  (pal? [1 2 1]) => truthy)

(fact
  (pal? "racecar") => truthy
  (pal? [:foo :bar :foo]) => truthy
  (pal? '(1 1 3 3 1 1))  => truthy
  (pal? '(:a :b :c))  => falsey)

(println "--------- END pal  ----------" (java.util.Date.))
