(ns ^{:doc "http://www.4clojure.com/problem/122#prob-title"}
  4clojure-lab.149.core122
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Convert a binary number, provided in the form of a string, to its
;; numerical value.

(defn bin "Convert a binary number into its numerical value"
  [b]
  (read-string (str "2r" b)))

(fact
  (bin "0") => 0
  (bin "111") => 7
  (bin "1000") => 8
  (bin "1001") => 9
  (bin "11111111") => 255
  (bin "10101010101") => 1365
  (bin "1111111111111111")) => 65535
