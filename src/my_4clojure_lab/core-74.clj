(ns ^{:doc "http://www.4clojure.com/problem/74#prob-title"}
  my-4clojure-lab.core-74
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 74  ----------" (java.util.Date.))

;; Given a string of comma separated integers, write a function which
;; returns a new comma separated string that only contains the numbers
;; which are perfect squares.

(defn psquare ""
  [s]
  (clojure.string/join "," (filter #(let [r (Math/sqrt %)] (= r (Math/floor r))) (map read-string (re-seq #"\d+" s)))))

(fact
  (psquare "4,5,6,7,8,9") => "4,9"
  (psquare "15,16,25,36,37") => "16,25,36")

(println "--------- END 74  ----------" (java.util.Date.))
