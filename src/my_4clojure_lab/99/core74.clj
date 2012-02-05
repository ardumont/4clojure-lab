(ns ^{:doc "http://www.4clojure.com/problem/74#prob-title"}
  my-4clojure-lab.99.core74
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Given a string of comma separated integers, write a function which
;; returns a new comma separated string that only contains the numbers
;; which are perfect squares.

(defn psquare "Returns a new comma separated string that only contains the numbers which are perfect squares."
  [s]
  (clojure.string/join
   ","
   (filter
    #(let [r (Math/sqrt %)]
       (= r (Math/floor r)))
    (map read-string (re-seq #"\d+" s)))))

(fact
  (psquare "4,5,6,7,8,9") => "4,9"
  (psquare "15,16,25,36,37") => "16,25,36")


