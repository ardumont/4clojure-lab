(ns ^{:doc "http://www.4clojure.com/problem/44#prob-title"}
  my-4clojure-lab.core-44
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 44  ----------" (java.util.Date.))

;; Write a function which can rotate a sequence in either direction.

(defn rotate "Rotate a sequence in either direction."
  [n s]
  (let [p (mod n (count s))
        sp (conj (into [] (rest s)) (first s))]
    (if (= p 1) sp (rotate (dec p) sp))))

(fact 
  (rotate 2 [1 2 3 4 5]) => '(3 4 5 1 2)
  (rotate -2 [1 2 3 4 5]) => '(4 5 1 2 3)
  (rotate 6 [1 2 3 4 5]) => '(2 3 4 5 1)
  (rotate 1 '(:a :b :c)) => '(:b :c :a)
  (rotate -4 '(:a :b :c)) => '(:c :a :b))

(println "--------- END 44  ----------" (java.util.Date.))