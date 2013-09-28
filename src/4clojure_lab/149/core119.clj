(ns 4clojure-lab.149.core119
  "http://www.4clojure.com/problem/119#prob-title"
  (:use [midje.sweet :only [future-fact fact contains exactly]])
  (:require [clojure.tools.trace :as t]))

;; Difficulty:	Hard
;; Topics:	game

;; As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and empty is represented by :e. Create a function that accepts a game piece and board as arguments, and returns a set (possibly empty) of all valid board placements of the game piece which would result in an immediate win.

;; Board coordinates should be as in calls to get-in. For example, [0 1] is the topmost row, center position.

(defn w?
  [b]
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

(defn win [p b]
  (let [n (-> b first count)
        r (range n)]
    (set (for [x r
               y r
               :let [v [x y]
                     e (get-in b v)]
               :when (and (= :e e) (= p (-> b (assoc-in v p) w?)))]
           v))))

(fact (win :x [[:o :e :e]
               [:o :x :o]
               [:x :x :e]]) => #{[2 2] [0 1] [0 2]})

(fact (win :x [[:x :o :o]
               [:x :x :e]
               [:e :o :e]]) => #{[2 2] [1 2] [2 0]})

(fact (win :x [[:x :e :x]
               [:o :x :o]
               [:e :o :e]]) => #{[2 2] [0 1] [2 0]})

(fact (win :x [[:x :x :o]
               [:e :e :e]
               [:e :e :e]]) => #{})

(fact (win :o [[:x :x :o]
               [:o :e :o]
               [:x :e :e]]) => #{[2 2] [1 1]})
