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

(unfinished )

(defn nb
  [[x y]]
  [[(+ 1 x) y] [(+ 1 x) (+ 1 y)]])

(fact "neighbour"
  (nb [0 0]) => [[1 0] [1 1]])

(defn cn "Compute the neighbours"
  [t]
  (let [l (count t)
        c (count (last t))]
    (reduce
     (fn [m v] (assoc m (get-in t v) (map #(get-in t %) (nb v))))
     {}
     (for [y (range (- l 1)) x (range c) :when (<= x y)] [y x]))))

(fact "compute the neighbours"
  (cn [[:a]
      [:b :c]
     [:d :e :f]]) => {:a [:b :c]
                       :b [:d :e]
                       :c [:e :f]}
       (provided
         (nb [0 0]) => [[1 0] [1 1]]
         (nb [1 0]) => [[2 0] [2 1]]
         (nb [1 1]) => [[2 1] [2 2]]))

(fact "IT"
  (cn [[1]
      [2 3]
      [4 5 6]]) => {1 '(2 3)
                    2 '(4 5)
                    3 '(5 6)})

(defn cm "compute from neightbours"
  [m]
  )

(future-fact "compute from neightbours"
  (cm {1 [2 3] 2 [4 5]}) => 5)

(defn min-path "Triangle minimal path"
  [t]
  (cm (cn t)))

(fact "Compute the minimal path"
  (min-path :tr) => :res
  (provided
    (cn :tr) => :map
    (cm :map) => :res))

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
