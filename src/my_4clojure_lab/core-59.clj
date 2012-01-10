(ns ^{:doc "http://www.4clojure.com/problem/59#prob-title"}
  my-4clojure-lab.core-59
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 59  ----------" (java.util.Date.))

;; Take a set of functions and return a new function that takes a
;; variable number of arguments and returns a sequence containing the
;; result of applying each function left-to-right to the argument
;; list.

(defn mjuxt "Juxt implementation"
  ([& r] (fn [& a] (map (fn [f] (apply f a)) r))))

;.;. The work itself praises the master. -- CPE Bach
(fact
  ((mjuxt + max min) 2 3 5 1 6 4) => [21 6 1]
  ((mjuxt :a :c :b) {:a 2, :b 4, :c 6, :d 8, :e 10}) => [2 6 4])

(fact
  ((mjuxt #(.toUpperCase %) count) "hello") => ["HELLO" 5])

(println "--------- END 59  ----------" (java.util.Date.))