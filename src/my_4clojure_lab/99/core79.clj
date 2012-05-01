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

(defn nb "Given a coordinate vector, return the neighbours for this v."
  [[y x] l]
  (if (= l y)
    []
    [[(+ 1 y) x] [(+ 1 y) (+ 1 x)]]))

(fact "neighbour"
  (nb [0 0] 1) => [[1 0] [1 1]]
  (nb [2 2] 2) => [])

(defn ct "Compute the neighbours"
  [t]
  (let [l (count t)
        c (count (last t))]
    (reduce
     (fn [m c]
       (let [v (get-in t c)
             l1 (- l 1)]
         (assoc m c {:v v :c (nb c l1) :s v})))
     {}
     (for [y (range l)
           x (range c)
           :when (<= x y)] [y x]))))

(fact "Mock - Compute the tree"
  (ct [[:a]
       [:b :c]
       [:d :e :f]]) => {[0 0] {:v :a :c [[1 0] [1 1]] :s :a}
                        [1 0] {:v :b :c [[2 0] [2 1]] :s :b}
                        [1 1] {:v :c :c [[2 1] [2 2]] :s :c}
                        [2 0] {:v :d :c [] :s :d}
                        [2 1] {:v :e :c [] :s :e}
                        [2 2] {:v :f :c [] :s :f}}
  (provided
    (nb [0 0] 2) => [[1 0] [1 1]]
    (nb [1 0] 2) => [[2 0] [2 1]]
    (nb [1 1] 2) => [[2 1] [2 2]]
    (nb [2 0] 2) => []
    (nb [2 1] 2) => []
    (nb [2 2] 2) => []))

(fact "IT - Compute the tree"
  (ct [[1]
       [2 3]
       [4 5 6]]) => {[0 0] {:v 1 :c '([1 0] [1 1]) :s 1}
                     [1 0] {:v 2 :c '([2 0] [2 1]) :s 2}
                     [1 1] {:v 3 :c '([2 1] [2 2]) :s 3}
                     [2 0] {:v 4 :c [] :s 4}
                     [2 1] {:v 5 :c [] :s 5}
                     [2 2] {:v 6 :c [] :s 6}})

(defn nb "Given a coordinate vector, return the neighbours for this v."
  [[y x] l]
  (if (= l y)
    []
    [[(+ 1 y) x] [(+ 1 y) (+ 1 x)]]))
(defn ct "Compute the neighbours"
  [t]
  (let [l (count t)
        c (count (last t))]
    (reduce
     (fn [m c]
       (let [v (get-in t c)
             l1 (- l 1)]
         (assoc m c {:v v :c (nb c l1) :s v})))
     {}
     (for [y (range l)
           x (range c)
           :when (<= x y)] [y x]))))
(defn cm "Find the minimal sum route."
  ([t cd]
     (letfn [(cmm [t cd]
               (let [{:keys [v c s]} (t cd)]
                 (if (empty? c)
                   [s]
                   (mapcat (fn [ncd]
                             (let [sc (:s ncd)
                                   vc (:v ncd)]
                               (if (= sc vc) ;; first visit?
                                 (cmm (update-in t [ncd :s] (fn [s1] (+ s s1))) ncd)
                                 (cmm (update-in t [ncd :s] (fn [s1] (min s1 (+ v s)))) ncd)))) c))))]
       (apply min (cmm t cd)))))

(fact "compute from neightbours"
  (cm {[0 0] {:v 1 :c '([1 0] [1 1]) :s 1}
       [1 0] {:v 2 :c '([2 0] [2 1]) :s 2}
       [1 1] {:v 3 :c '([2 1] [2 2]) :s 3}
       [2 0] {:v 4 :c [] :s 4}
       [2 1] {:v 5 :c [] :s 5}
       [2 2] {:v 6 :c [] :s 6}} [0 0]) => 7)

(fact
  (cm {[0 0] {:v 1, :c [[1 0] [1 1]], :s 1},
       [1 0] {:v 2, :c [[2 0] [2 1]], :s 2},
       [1 1] {:v 4, :c [[2 1] [2 2]], :s 4},
       [2 0] {:v 5, :c [[3 0] [3 1]], :s 5}
       [2 1] {:v 1, :c [[3 1] [3 2]], :s 1},
       [2 2] {:v 4, :c [[3 2] [3 3]], :s 4},
       [3 0] {:v 2, :c [], :s 2},
       [3 1] {:v 3, :c [], :s 3},
       [3 2] {:v 4, :c [], :s 4},
       [3 3] {:v 5, :c [], :s 5}} [0 0]) => 7)

(defn min-path "Triangle minimal path"
  [t]
  (cm (ct (vec t)) [0 0]))

(fact "IT"
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


;; 4clojure function

(defn min-path "Triangle minimal path"
  [t]
  (letfn [(nb [[y x] l] (if (= l y) [] [[(+ 1 y) x] [(+ 1 y) (+ 1 x)]]))
          (ct [t]
            (let [l (count t)
                  c (count (last t))]
              (reduce (fn [m c]
                        (let [v (get-in t c)
                              l1 (- l 1)]
                          (assoc m c {:v v :c (nb c l1) :s v})))
                      {}
                      (for [y (range l)
                            x (range c)
                            :when (<= x y)] [y x]))))
          (cmm [t cd]
            (let [{:keys [v c s]} (t cd)]
              (if (empty? c)
                [s]
                (mapcat (fn [ncd]
                          (let [sc (:s ncd)
                                vc (:v ncd)]
                            (if (= sc vc) ;; first visit?
                              (cmm (update-in t [ncd :s] (fn [s1] (+ s s1))) ncd)
                              (cmm (update-in t [ncd :s] (fn [s1] (min s1 (+ v s)))) ncd)))) c))))]
    (apply min (cmm (ct (vec t)) [0 0]))))

(fact "IT"
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