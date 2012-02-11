(ns ^{:doc "http://www.4clojure.com/problem/79#prob-title"}
  my-4clojure-lab.99.core79
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

;; Triangle Minimal Path

;; Difficulty:Hard
;; Topics:graph-theory

;; Write a function which calculates the sum of the minimal path through a triangle. The triangle is represented as a
;; collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next
;; row until the bottom of the triangle is reached.

(unfinished)

(defn pf "Path from a coordinate c to the end of the triangle"
  [[x y :as c] t]
  (let [f (get-in t c)
        c0 [(+ 1 x) y]
        c1 [(+ 1 x) (+ 1 y)]
        f0 (get-in t c0)
        f1 (get-in t c1)]
    (if f [[f f0] [f f1]])))

;.;. It takes time to succeed because success is merely the natural reward of taking time to do anything well. -- Ross
(fact "path from the coordinate"
  (pf [0 0] [[:a]
             [:b :c]]) => [[:a :b] [:a :c]])

(future-fact "path from more complex"
             (pf [0 0] [[:a]
                        [:b :c]
                        [:d :e :f]]) =>  [[:a :b :d]
                                          [:a :b :e]
                                          [:a :c :e]
                                          [:a :c :f]])

(defn min-path "Triangle minimal path"
  [t]
  (apply min (map #(apply + %) (pf [0 0] t))))

(fact "Compute the minimal path"
  (min-path [:l0 :l1 :l2]) => 3
  (provided
    (pf [0 0] [:l0 :l1 :l2]) => [[1 2 3]
                                 [1 1 1]]))

(future-fact  "IT"
  (min-path '([1]
                [2 4]
                [5 1 4]
                [2 3 4 5])) => 7        ; 1->2->1->3
  (min-path '([3]
                [2 4]
                [1 9 3]
                [9 9 2 4]
                [4 6 6 7 8]
                [5 7 3 5 1 4])) => 20)  ; 3->4->3->2->7->1
