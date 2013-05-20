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

(defn solve
  [maze])

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
