(ns ^{:doc "http://www.4clojure.com/problem/94#prob-title"}
  my-4clojure-lab.99.core94
  (:use clojure.repl
        [clojure.pprint :only [pp pprint]]
        clojure.java.javadoc
        [midje.sweet]))

;; The game of life is a cellular automaton devised by mathematician John Conway.

;; The 'board' consists of both live (#) and dead ( ) cells. Each cell interacts with its eight neighbours (horizontal, vertical, diagonal), and its next state is dependent on the following rules:

;; 1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
;; 2) Any live cell with two or three live neighbours lives on to the next generation.
;; 3) Any live cell with more than three live neighbours dies, as if by overcrowding.
;; 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

;; Write a function that accepts a board, and returns a board representing the next generation of cells.

; Solution:
;; Structure i use to represent a cell
;; cell  -> {:st :live-or-dead :ln 0}
;; :live -> state of the cell, here alive!
;; :dead -> state of the cell, here dead!
;; st    -> state
;; ln    -> number of living neighbours for the cell

(unfinished)

(defn init-cells "Given a board, return the map initialized for the cells"
  [[& s]]
  (let [m {\# :live \space :dead}
        l (count s)
        c (count (nth s 0))]
    (apply merge
           (for [x (range c) y (range l)]
             {[y x] {:st (m (nth (nth s y) x)) :ln 0}}))))

(fact "init-cells"
  (init-cells ["#  "
               " # "
               "  #"
               "   " ]) => {[0 0] {:st :live :ln 0} [0 1] {:st :dead :ln 0} [0 2] {:st :dead :ln 0}
                            [1 0] {:st :dead :ln 0} [1 1] {:st :live :ln 0} [1 2] {:st :dead :ln 0}
                            [2 0] {:st :dead :ln 0} [2 1] {:st :dead :ln 0} [2 2] {:st :live :ln 0}
                            [3 0] {:st :dead :ln 0} [3 1] {:st :dead :ln 0} [3 2] {:st :dead :ln 0}})

(defn neighbours "Given a coordinate, returns the sequence of neighbours"
  [[a b :as c] n]
  (filter (fn [[k p :as d]] (and (<= 0 k) (<= 0 p) (< k n) (< p n) (not= c d)))
          (for [x [-1 0 1] y [-1 0 1]] [(+ a x) (+ b y)])))

(fact "neighbours"
  (neighbours [0 0] 2) => [[0 1] [1 0] [1 1]])

(defn alive? "Is a cell alive?"
  [m c]
  (= :live ((m c) :st)))

(fact "alive?"
  (alive? {:c1 {:st :live}} :c1) => true
  (alive? {:c1 {:st :dead}} :c1) => false)

(defn compute-cells "Compute the living neighbours for each cell."
  [m max]
  (letfn [(cn [c]
            (reduce (fn [s c]
                      (if (alive? m c) (+ s 1) s)) 0
                      (neighbours c max)))]
    (reduce
     (fn [n c]
       (update-in n [c :ln] (fn [e] (cn c)))) m (keys m))))

;.;. Not in rewards, but in the strength to strive, the blessing lies. -- Towbridge
(fact "compute-cells"
  (let [m {:c0 {:st :live :ln 0} :c1 {:st :live :ln 0} :c2 {:st :live :ln 0}}]
    (compute-cells m :max) => {:c0 {:st :live :ln 1} :c1 {:st :live :ln 0} :c2 {:st :live :ln 0}} 
    (provided
      (neighbours :c0 :max) => [:c1 :c2]
      (neighbours :c1 :max) => []
      (neighbours :c2 :max) => []
      (alive? m :c1) => true
      (alive? m :c2) => false)))

(defn cells "Given a board, returns the map representing the different cells from the board"
  [b n]
  (compute-cells (init-cells b) n))

(fact
  (cells [[:a :b] [:c :d]] :nb-cells) => :map-cells
  (provided
    (init-cells [[:a :b] [:c :d]]) => :init-cells
    (compute-cells :init-cells :nb-cells) => :map-cells))

(defn nxt-gen-cell
  "1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   2) Any live cell with two or three live neighbours lives on to the next generation.
   3) Any live cell with more than three live neighbours dies, as if by overcrowding.
   4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
  [m]
  (let [a? (= :live (m :st))
        n  (m :ln)]
    (cond
     (and a? (< n 2))       {:st :dead :ln 0}
     (and a? (<= 2 n 3))    {:st :live :ln 0}
     (and a? (< 3 n))       {:st :dead :ln 0}
     (and (not a?) (= 3 n)) {:st :live :ln 0}
     :else m)))

(fact "nxt-gen-cell"
  (nxt-gen-cell {:st :live :ln 1}) => {:st :dead :ln 0}
  (nxt-gen-cell {:st :live :ln 2}) => {:st :live :ln 0}
  (nxt-gen-cell {:st :live :ln 4}) => {:st :dead :ln 0}
  (nxt-gen-cell {:st :dead :ln 3}) => {:st :live :ln 0})

(defn nxt-gen "Next gen matrix"
  [m]
  (reduce (fn [n k] (assoc n k (nxt-gen-cell (m k)))) m (keys m)))

(fact "nxt-gen"
  (nxt-gen {:c1 :m1 :c2 :m2}) => {:c1 :m3 :c2 :m4}
  (provided
    (nxt-gen-cell :m1) => :m3
    (nxt-gen-cell :m2) => :m4))

(defn board "Create the board from the next generation cells"
  [m n]
  (let [y {:live "#" :dead " "}]
    (map #(reduce str "" %)
         (partition-all n
                        (reduce (fn [s k]
                                  (conj s (y ((m k) :st))))
                                []
                                (sort (keys m)))))))

(fact "board"
  (board {:c1 {:st :live}
          :c2 {:st :dead}
          :c3 {:st :live}
          :c4 {:st :live}} 2) => ["# "
                                  "##"])

(fact "board 2"
  (board {:c1 {:st :dead} :c2 {:st :dead} :c3 {:st :dead}
          :c4 {:st :live} :c5 {:st :live} :c6 {:st :live}} 3) => ["   "
                                                                  "###"])

(defn gol "Game of life"
  [b]
  (let [n (count (first b))]
    (board (nxt-gen (cells b n)) n)))

(fact
  (gol [[:a :b] [:c :d]]) => :nwb
  (provided
    (cells [[:a :b] [:c :d]] 2) => :map-cells
    (nxt-gen :map-cells) => :next-gen-map
    (board :next-gen-map 2) => :nwb))

(fact "IT test 0"
  (gol ["      " 
        " ##   "
        " ##   "
        "   ## "
        "   ## "
        "      "]) => ["      " 
                       " ##   "
                       " #    "
                       "    # "
                       "   ## "
                       "      "])
(fact "IT test 1." 
  (gol ["     "
        "     "
        " ### "
        "     "
        "     "]) =>  ["     "
                       "  #  "
                       "  #  "
                       "  #  "
                       "     "])

(fact "IT test 2"
  (gol ["      "
        "      "
        "  ### "
        " ###  "
        "      "
        "      "]) => ["      "
                       "   #  "
                       " #  # "
                       " #  # "
                       "  #   "
                       "      "])
