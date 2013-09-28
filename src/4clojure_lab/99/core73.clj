(ns ^{:doc "http://www.4clojure.com/problem/73#prob-title"}
  4clojure-lab.99.core73
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Difficulty:	Hard
;; Topics:	game

;; A tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and
;; empty is represented by :e. A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row.
;; Write a function which analyzes a tic-tac-toe board and returns :x if X has won, :o if O has won, and nil if neither
;; player has won.

(defn ttt [b]
  (letfn [(w [[[a b c]
               [d e f]
               [g h i]] p] (or (= p a b c)
                               (= p d e f)
                               (= p g h i)
                               (= p a d g)
                               (= p b e h)
                               (= p c f i)
                               (= p a e i)
                               (= p c e g)))]
    (cond (w b :x) :x
          (w b :o) :o
          :else nil)))

(fact
  (ttt [[:e :e :e]
       [:e :e :e]
       [:e :e :e]]) => nil
  (ttt [[:x :e :o]
       [:x :e :e]
       [:x :e :o]]) => :x
  (ttt [[:e :x :e]
       [:o :o :o]
       [:x :e :x]]) => :o
  (ttt [[:x :e :o]
       [:x :x :e]
       [:o :x :o]]) => nil
  (ttt [[:x :e :e]
       [:o :x :e]
       [:o :e :x]]) => :x
  (ttt [[:x :e :o]
       [:x :o :e]
       [:o :e :x]]) => :o
  (ttt [[:x :o :x]
       [:x :o :x]
       [:o :x :o]]) => nil)
