(ns ^{:doc "http://www.4clojure.com/problem/34#prob-title"}
  clojure4-lab.49.core27
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Palindrome detector

(defn pal? "Is the sequence a palindrome?"
  [s]
  (= (seq s) (reverse s)))

(fact
  (pal? [1 2 3]) => falsey
  (pal? [1 2 1]) => truthy
  (pal? "racecar") => truthy
  (pal? [:foo :bar :foo]) => truthy
  (pal? '(1 1 3 3 1 1))  => truthy
  (pal? '(:a :b :c)) => falsey)
