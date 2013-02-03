(ns ^{:doc "http://www.4clojure.com/problem/125#prob-title"}
  my-4clojure-lab.149.core125
  (:use [midje.sweet]))

;; Gus' Quinundrum

;; Difficulty:	Hard
;; Topics:	logic fun brain-teaser

;; Create a function of no arguments which returns a string that is an exact copy of the function itself.

;; Hint: read this if you get stuck (this question is harder than it first appears); but it's worth the effort to solve it independently if you can!

;; Fun fact: Gus is the name of the 4Clojure dragon.

;(define function define data)

((fn []
   (str ((fn [x] (list x (list (quote quote) x)))
         (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x)))))))))))


(comment ;; I'm not crazy, this works :D but 4clojure is not satisfied
  (eval "((fn [] (str ((fn [x] (list x (list (quote quote) x))) (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x)))))))))))")
        "((fn [] (str ((fn [x] (list x (list (quote quote) x))) (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x)))))))))))")

(fact
  (str ((fn [] (str ((fn [x] (list x (list (quote quote) x)))
                    (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x))))))))))))
  => "((fn [] (str (quote (fn [x] (list x (list (quote quote) x)))))) (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x))))))))")

;; does not work for 4clojure
(= (str '(fn [] (str ((fn [x] (list x (list (quote quote) x)))
                     (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x)))))))))))
   ((fn [] (str ((fn [x] (list x (list (quote quote) x)))
                (quote (fn [] (str (quote (fn [x] (list x (list (quote quote) x))))))))))))
