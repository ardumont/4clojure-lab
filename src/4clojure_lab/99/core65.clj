(ns ^{:doc "http://www.4clojure.com/problem/65#prob-title"}
  4clojure-lab.99.core65
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Clojure has many sequence types, which act in subtly different ways.
;; The core functions typically convert them into a uniform "sequence" type
;; and work with them that way, but it can be important to understand the behavioral
;; and performance differences so that you know which kind is appropriate for your application.

;; Write a function which takes a collection and returns one of :map, :set, :list, or :vector
;; - describing the type of collection it was given.
;; You won't be allowed to inspect their class or use the built-in predicates like list?
;; - the point is to poke at them and understand their behavior.

(defn which-seq
  [s]
  (let [x :a
        y :b]
    (cond
     (= (x (conj s [x y])) y) :map
     (= (conj s x x) (conj s x)) :set
     (= (last (conj s x y)) y) :vector
     :else :list)))

;.;. Out of clutter find simplicity; from discord find harmony; in the middle of difficulty lies opportunity. -- Einstein
(fact "Tests ok"
  (which-seq [1 2 3 4 5 6]) => :vector
  (which-seq (range (rand-int 20))) => :list
  (which-seq #{10 (rand-int 5)}) => :set
  (which-seq {:a 1, :b 2})  =>  :map
  (map which-seq [{} #{} [] ()])   =>  [:map :set :vector :list])
