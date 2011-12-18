(ns ^{:doc "http://www.4clojure.com/problem/90#prob-title"}
  my-4clojure-lab.core-90
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 90  ----------" (java.util.Date.))

;; Write a function which calculates the Cartesian product of two
;; sets.

(defn cart "cartesian product"
  [s0 s1]
  (set (for [x s0, y s1] [x y])))

(fact 
  (cart #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"}) => #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
                                                       ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
                                                       ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]}
  (cart #{1 2 3} #{4 5}) => #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]}
  (count (cart (into #{} (range 10)) (into #{} (range 30)))) => 300)

(println "--------- END 90  ----------" (java.util.Date.))