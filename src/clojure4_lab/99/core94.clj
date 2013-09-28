(ns ^{:doc "http://www.4clojure.com/problem/94#prob-title"}
  clojure4-lab.99.core94
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
;; cell  -> {:s :l-or-dead :n 0}
;; :l -> state of the cell, here alive!
;; :d -> state of the cell, here dead!
;; st    -> state
;; ln    -> number of living neighbours for the cell

(unfinished)

(defn nb "Given a coordinate, returns the sequence of neighbours"
  [m [a b :as c]]
  (let [n (count (nth m 0))]
    (for [x [-1 0 1]
          y [-1 0 1]
          :let [k (+ a x)
                p (+ b y)
                d [k p]]
          :when (and (<= 0 k) (<= 0 p)
                     (< k n)  (< p n)
                     (not= c d))]
      d)))

(fact "nb"
  (nb ["  "
       "  "] [0 0]) => [[0 1] [1 0] [1 1]]
  (nb ["  "
       "  "] [0 1]) => [[0 0] [1 0] [1 1]])

(defn cn "Count the neighbours of a cell"
  [m c]
  (reduce (fn [s c]
            (if (= \# (get-in m c)) (+ s 1) s)) 0
            (nb m c)))

(fact
  (cn ["#  "
       " # "
       "  #"
       "   " ] [0 0]) => 1)

(defn ngc "Compute the next generation for the cell c"
  [m c]
  (let [v (get-in m c)
        a (= \# v)
        n  (cn m c)]
    (cond
     (and a       (< n 2))    \
     (and a       (<= 2 n 3)) \#
     (and a       (< 3 n))    \
     (and (not a) (= 3 n))    \#
     :else v)))

(fact "ngc"
  (ngc ["##"
        " "] [0 0]) => \
  (ngc ["##"
        "# "] [0 0]) => \#
  (ngc ["## "
        "###"
        "   "] [1 1]) => \
  (ngc [" # "
        "# #"
        "   "] [1 1]) => \#)

(defn gol "Game of life"
  [b]
  (let [l (count b)
        c (count (nth b 0))]
    (map #(apply str %)
         (partition c
                    (for [y (range l) x (range c)]
                      (ngc b [y x]))))))

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
