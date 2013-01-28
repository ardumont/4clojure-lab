(ns ^{:doc "http://www.4clojure.com/problem/106#prob-title"}
  my-4clojure-lab.149.core106
  (:use [midje.sweet :only [fact future-fact contains exactly]])
  (:require [clojure.tools.trace :as t]))

;; Difficulty:	Hard
;; Topics:	numbers


;; Given a pair of numbers, the start and end point, find a path between the two using only three possible operations:

;;     double
;;     halve (odd numbers cannot be halved)
;;     add 2

;; Find the shortest path through the "maze". Because there are multiple shortest paths, you must return the length of the shortest path, not the path itself.

(defn mkc
  [{:keys [s d]}]
  (let [[_ & r :as vf] [/ * + ]]
    (map
     (fn [f] {:s (f s 2)
             :d (+ 1 d)})
     (if (even? s) vf r))))

(fact
  (mkc {:s 10 :d 1}) => (contains {:s 5 :d 2}
                                  {:s 20 :d 2}
                                  {:s 12 :d 2}
                                  :in-any-order)
  (mkc {:s 9 :d 1}) => [{:s 18 :d 2}
                        {:s 11 :d 2}])

(defn bfs
  "breadth-first search lazily"
  ([s e] (bfs {:s s :d 1}))
  ([t]
      ((fn nx [q]
         (lazy-seq
          (when (seq q)
            (let [n (peek q)
                  c (mkc n)]
              (cons n (nx (into (pop q) c)))))))
       (conj clojure.lang.PersistentQueue/EMPTY t))))

(fact
  (take 1 (bfs 1 10)) => '({:s 1 :d 1})
  (take 4 (bfs 2 3)) => '({:s 2 :d 1} {:s 1 :d 2} {:s 4 :d 2} {:s 4 :d 2}))

(defn maze
  [s e]
  (if (= s e)
    1
    (->>
     (bfs s e)
     (drop-while
      #(not= (:s %) e))
     first
     :d)))

;; 1 -> 1                                                                :: 1
;; 3 -*2> 6 -*2> 12                                                      :: 3
;; 12 -/2> 6 -/2> 3                                                      :: 3
;; 5 -+2> 7 -+2> 9                                                       :: 3

;; 9 -*2> 18 -+2> 20 -+2> 22 -+2> 24 -/2> 12 -/2> 6 -+2> 8 -/2> 4 -/2> 2 :: 10
;; 9 -*2> 18 -+2> 20 -/2> 10 -+2> 12 -/2> 6 -+2> 8 -/2> 4 -/2> 2         :: 9

;; 9 -*2> 18 -+2> 20 -/2> 10 -+2> 12                                     :: 5

(fact (maze 1 1)  =>  1) ; 1
(fact (maze 3 12) =>  3) ; 3 6 12
(fact (maze 12 3) =>  3) ; 12 6 3
(fact (maze 5 9)  =>  3) ; 5 7 9
(fact (maze 9 2)  =>  9) ; 9 18 20 10 12 6 8 4 2
(fact (maze 9 12) =>  5) ; 9 11 22 24 12
