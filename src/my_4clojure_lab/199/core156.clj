(ns ^{:doc "http://www.4clojure.com/problem/156#prob-title"}
  my-4clojure-lab.199.core156
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Map Defaults
 
;; Difficulty:	Elementary
;; Topics:	seqs

;; When retrieving values from a map, you can specify default values in case the key is not found:

;; (= 2 (:foo {:bar 0, :baz 1} 2))

;; However, what if you want the map itself to contain the default values? Write a function which takes a default value
;; and a sequence of keys and constructs a map.

(defn md "map default"
  [n c]
  (apply assoc {} (interleave c (repeat (count c) n))))

(fact
  (md 0 [:a :b :c]) => {:a 0 :b 0 :c 0}
  (md "x" [1 2 3]) => {1 "x" 2 "x" 3 "x"}
  (md [:a :b] [:foo :bar]) => {:foo [:a :b] :bar [:a :b]})