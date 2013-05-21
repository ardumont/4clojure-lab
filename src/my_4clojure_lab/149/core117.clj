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

(defn p
  "Simple maze pretty printing"
  [maze]
  (->> maze
       (map println)
       dorun))

(defn nbs
  "Compute the possible neighbours of a cell."
  [maze [y x]]
  (let [r (-> maze first count)
        c (-> maze count)
        x- (dec x)
        x+ (inc x)
        y- (dec y)
        y+ (inc y)]
    (->> [[y- x] [y+ x] [y x+] [y x-]] ;; N S E W
         (filter (fn [[y x]] (and (<= 0 x) (<= 0 y) (< x r) (< y c)))))))

(m/fact
  (nbs [" M# C"
        "     "] [0 0]) => (m/just [0 1] [1 0] :in-any-order)
  (nbs [" M# C"
        "     "] [1 1]) => (m/just [1 0] [1 2] [0 1] :in-any-order))

(defn nxm
  "Given a cell, compute the next possible move from such cell."
  [maze [y x :as c]]
  (let []
    (->> c
         (nbs maze)
         (filter (comp #{\space \C} (partial get-in maze))))))

(m/fact
  (nxm [" M# C"] [0 1]) => [[0 0]]
  (nxm ["M   C"] [0 0]) => [[0 1]]
  (nxm ["M   C"
        " #   "] [0 0]) => (m/just [1 0] [0 1] :in-any-order)
  (nxm ["     "
        "  #  "
        " #M# "
        "  # C"] [2 2]) => [])

(defn get-character
  "Given a maze and a character, return its coordinates in the maze."
  [maze character]
  (let [r (-> maze first count)
        c (-> maze count)]
    (first
     (for [x (range r)
           y (range c)
           :when (= character (get-in maze [y x]))]
       [y x]))))

(m/fact
  (get-character ["M   C"] \M)   => [0 0]
  (get-character ["M   C"] \C)   => [0 4])

(def mouse #(get-character % \M))

(m/fact
  (mouse ["M   C"])   => [0 0]
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

(def cheese #(get-character % \C))

(m/fact
  (cheese ["M   C"])   => [0 4]
  (cheese ["#######"
           "#     #"
           "#  #  #"
           "#M # C#"
           "#######"]) => [3 5]
  (cheese ["C######"
           " #     "
           " #   # "
           " #   #M"
           "     # "]) => [0 0])

(defn range-y
  [[cy _] [dy _]]
  (let [d (- dy cy)]
    (cond (pos? d)  (->> dy inc (range cy))
          (neg? d) (->> cy inc (range dy))
          :else     [])))

(m/fact
  (range-y [5 0] [10 0])  => (range 5 11)
  (range-y [10 0] [5 0])  => (range 5 11)
  (range-y [10 0] [10 0]) => [])

(defn cleanup-fr
  "Cleanup the first row if all first row begin with #"
  [maze]
  (if (->> maze
           (map first)
           (every? #{\#}))
    (->> maze
         (map (comp (partial clojure.string/join "") (partial drop 1))))
    maze))

(m/fact
  (cleanup-fr ["#######"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["######"
                               "     #"
                               "  #  #"
                               "M # C#"
                               "######"]
  (cleanup-fr ["###  ##"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["##  ##"
                               "     #"
                               "  #  #"
                               "M # C#"
                               "######"])

(defn cleanup-lr
  "Cleanup the first row if all first row begin with #"
  [maze]
  (let [l (-> maze first count)]
    (if (->> maze
             (map last)
             (every? #{\#}))
      (->> maze
           (map (comp (partial clojure.string/join "") (partial drop l))))
      maze)))

(m/fact
  (cleanup-lr ["#######"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["######"
                               "#     "
                               "#  #  "
                               "#M # C"
                               "######"]
  (cleanup-lr ["###  ##"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["###  #"
                               "#     "
                               "#  #  "
                               "#M # C"
                               "######"])

(defn cleanup-fc
  [maze]
  (if (->> maze
           first
           (every? #{\#}))
    (drop 1 maze)
    maze))

(m/fact
  (cleanup-fc ["#######"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["#     #"
                               "#  #  #"
                               "#M # C#"
                               "#######"]
  (cleanup-fc ["###  ##"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["###  ##"
                               "#     #"
                               "#  #  #"
                               "#M # C#"
                               "#######"])

(defn cleanup-lc
  [maze]
  (if (->> maze
           last
           (every? #{\#}))
    (drop-last maze)
    maze))

(m/fact
  (cleanup-lc ["#######"
               "#     #"
               "#  #  #"
               "#M # C#"
               "#######"]) => ["#######"
                               "#     #"
                               "#  #  #"
                               "#M # C#"]
  (cleanup-lc ["###  ##"
               "#     #"
               "#  #  #"
               "#M # C#"
               "###  ##"]) => ["###  ##"
                               "#     #"
                               "#  #  #"
                               "#M # C#"
                               "###  ##"])
(defn cleanup
  [maze]
  (->> maze
       cleanup-fc
       cleanup-lc))

(m/fact
  (cleanup ["#######"
            "#      "
            "   #  #"
            "#M # C#"
            "#######"]) => ["#      "
                            "   #  #"
                            "#M # C#"])

(defn to-y?
  "Given a cell with coordinate [cy _], is this possible to simply go to the [dy _]?"
  [maze [cy _ :as c] [dy _ :as d]]
  (if (= cy dy)
    true
    (let [r (range-y (mouse maze) (cheese maze))]
      (->> r
           (map (fn [v] (->> [v]
                            (get-in maze)
                            (filter #{\space})
                            (some #{\space}))))
           (every? #{\space})))))

(m/fact
  (to-y? ["#######"
          "#     #"
          "#  #  #"
          "#M # C#"
          "#######"] [3 0] [3 5]) => true
  (to-y? ["#######"
          "# M   #"
          "#  #  #"
          "#  # C#"
          "#######"] [1 2] [3 5]) => true
  (to-y? ["#######"
          "# M   #"
          "#######"
          "#  # C#"
          "#######"] [1 2] [3 5]) => false
  (to-y? ["#######"
          "# M   #"
          "##### #"
          "#  # C#"
          "#######"] [1 2] [3 5]) => true
  (to-y? ["#######"
          "# M   #"
          " ######"
          "#  # C#"
          "#######"] [1 2] [3 5]) => false)

(defn solve
  [mz]
  (->> [(mouse mz)]
       (iterate (partial mapcat (partial nxm mz)))
       (mapcat (partial map (comp #{\C} (partial get-in mz))))
       (drop-while nil?)
       first
       (= \C)))

(m/fact
  (solve ["M   C"])    => true
  (solve ["#######"
          "#     #"
          "#  #  #"
          "#M # C#"
          "#######"])  => true
  (solve ["C######"
          " #     "
          " #   # "
          " #   #M"
          "     # "])  => true
  (solve ["C# # # #"
          "        "
          "# # # # "
          "        "
          " # # # #"
          "        "
          "# # # #M"]) => true)

(m/future-fact
 (solve ["M # C"])    => false
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
 (psolve ["M     "
         "      "
         "      "
         "      "
         "    ##"
         "    #C"])   => false)
