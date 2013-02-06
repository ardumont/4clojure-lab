(ns ^{:doc "http://www.4clojure.com/problem/125#prob-title"}
  my-4clojure-lab.149.core125
  (:use [midje.sweet]))

;; Gus' Quinundrum

;; Difficulty:	Hard
;; Topics:	logic fun brain-teaser

;; Create a function of no arguments which returns a string that is an exact copy of the function itself.

;; Hint: read this if you get stuck (this question is harder than it first appears); but it's worth the effort to solve it independently if you can!

;; Fun fact: Gus is the name of the 4Clojure dragon.

((fn [] (let [s "(fn [] (let [s %s] (format s s s)))"] (format s s s))))

(= (str '(fn [] (let [s "(fn [] (let [s \"%s\"] (format s s s)))"] (format s s s))))
   ((fn [] (let [s "(fn [] (let [s \"%s\"] (format s s s)))"] (format s s s)))))
