(ns my-4clojure-lab.149.core117
  "For science - http://www.4clojure.com/problem/117#prob-title
A mad scientist with tenure has created an experiment tracking mice in a maze.
Several mazes have been randomly generated, and you've been tasked with writing a program to determine the mazes in which it's possible for the mouse to reach the cheesy endpoint.
Write a function which accepts a maze in the form of a collection of rows, each row is a string where:
- spaces represent areas where the mouse can walk freely
- hashes (#) represent walls where the mouse can not walk
- M represents the mouse's starting point
- C represents the cheese which the mouse must reach

The mouse is not allowed to travel diagonally in the maze (only up/down/left/right), nor can he escape the edge of the maze.
Your function must return true iff the maze is solvable by the mouse."
  (:use [clojure.repl]
        [clojure.java.javadoc])
  (:require [midje.sweet :as m]))

(defn pp
  "Simple maze pretty printing"
  [maze]
  (->> maze
       (map println)
       dorun))

(defn nbs
  "Compute the possible neighbours of a cell."
  [[y x]]
  (let [x- (dec x)
        x+ (inc x)
        y- (dec y)
        y+ (inc y)]
    (->> [[y- x] [y+ x] [y x+] [y x-]] ;; N S E W
         (filter (fn [[y x]] (and (<= 0 x) (<= 0 y)))))))

(m/fact
  (nbs [0 0]) => (m/just [0 1] [1 0] :in-any-order)
  (nbs [1 1]) => (m/just [1 0] [1 2] [0 1] [2 1] :in-any-order))

(defn next-move
  "Given a cell, compute the next possible move from such cell."
  [[y x :as c] maze]
  (->> c
       nbs
       (filter (comp #{\space \C} (partial get-in maze)))))

(m/fact
  (next-move [0 0] ["M   C"]) => [[0 1]]
  (next-move [0 0] ["M   C"
                    " #   "]) => (m/just [1 0] [0 1] :in-any-order)
  (next-move [2 2] ["     "
                    "  #  "
                    " #M# "
                    "  # C"]) => [])

(defn mouse
  [maze]
  (let [r (-> maze first count)
        c (-> maze count)]
    (first
     (for [x (range r)
           y (range c)
           :when (= \M (get-in maze [y x]))]
       [y x]))))

(m/fact
  (mouse ["M   C"]) => [0 0]
  (mouse ["#######"
          "#     #"
          "#  #  #"
          "#M # C#"
          "#######"]) => [3 1]
  (mouse ["C######"
          " #     "
          " #   # "
          " #   #M"
          "     # "]) => [3 6])

(defn solve
  [maze]
  )

(m/fact
  (solve ["M   C"]) => true
  (solve ["M # C"]) => false
  (solve ["#######"
          "#     #"
          "#  #  #"
          "#M # C#"
          "#######"]) => true
  (solve ["########"
          "#M  #  #"
          "#   #  #"
          "# # #  #"
          "#   #  #"
          "#  #   #"
          "#  # # #"
          "#  #   #"
          "#  #  C#"
          "########"]) => false
  (solve ["M     "
          "      "
          "      "
          "      "
          "    ##"
          "    #C"]) => false
  (solve ["C######"
          " #     "
          " #   # "
          " #   #M"
          "     # "]) => true
  (solve ["C# # # #"
          "        "
          "# # # # "
          "        "
          " # # # #"
          "        "
          "# # # #M"]) => true)
