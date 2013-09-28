(ns ^{:doc "http://www.4clojure.com/problem/79#prob-title"}
  4clojure-lab.99.core79
  (:use [midje.sweet])
  (:require [clojure.tools.trace :as t]))

;; Triangle Minimal Path

;; Difficulty:Hard
;; Topics:graph-theory

;; Write a function which calculates the sum of the minimal path through a triangle. The triangle is represented as a
;; collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next
;; row until the bottom of the triangle is reached.

(defn mkc
  "Compute the children from a given node"
  [{:keys [c]} t]
  (let [[y x] c
        y+ (+ y 1)
        x+ (+ x 1)]
    (filter (comp not nil? :v) [{:c [y+ x] :v (get-in t [y+ x])}
                                {:c [y+ x+] :v (get-in t [y+ x+])}])))

(fact
  (mkc {:c [0 0]} [[1]
                   [2 4]
                   [5 1 4]
                   [2 3 4 5]]) => [{:c [1 0] :v 2} {:c [1 1] :v 4}]
  (mkc {:c [2 1]} [[1]
                   [2 4]
                   [5 1 4]
                   [2 3 4 5]]) => [{:c [3 1] :v 3} {:c [3 2] :v 4}]
  (mkc {:c [3 1]} [[1]
                   [2 4]
                   [5 1 4]
                   [2 3 4 5]]) => [])

(defn bfs
  [t]
  ((fn nx [q]
     (lazy-seq
      (when (seq q)
        (let [n (peek q)
              c (map #(update-in % [:v] + (:v n)) (mkc n t))]
          (cons n (nx (into (pop q) c)))))))
   (conj clojure.lang.PersistentQueue/EMPTY {:c [0 0] :v (get-in t [0 0])})))

(fact
  (take 3 (bfs [[1]
                [2 4]
                [5 1 4]
                [2 3 4 5]])) => '({:c [0 0], :v 1} {:c [1 0], :v 3} {:c [1 1], :v 5})
  (bfs [[1]
        [2 4]
        [5 1 4]
        [2 3 4 5]]) => '({:c [0 0], :v 1}
                         {:c [1 0], :v 3} {:c [1 1], :v 5}
                         {:c [2 0], :v 8} {:c [2 1], :v 4} {:c [2 1], :v 6} {:c [2 2], :v 9}
                         {:c [3 0], :v 10} {:c [3 1], :v 11} {:c [3 1], :v 7} {:c [3 2], :v 8} {:c [3 1], :v 9} {:c [3 2], :v 10} {:c [3 2], :v 13} {:c [3 3], :v 14}))

(defn min-path "Triangle minimal path"
  [t]
  (let [v (vec t)
        l (-> v count dec)]
    (->> v
         bfs
         (drop-while #(not= ((:c %) 0) l))
         (map :v)
         (apply min))))

(fact
  (min-path '([1]
              [2 4]
              [5 1 4]
              [2 3 4 5])) => 7)       ; 1->2->1->3

(fact
  (min-path '([3]
              [2 4]
              [1 9 3]
              [9 9 2 4]
              [4 6 6 7 8]
              [5 7 3 5 1 4])) => 20)  ; 3->4->3->2->7->1
