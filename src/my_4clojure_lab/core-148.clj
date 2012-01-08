(ns ^{:doc "http://www.4clojure.com/problem/148#prob-title"}
  my-4clojure-lab.core-148
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 148  ----------" (java.util.Date.))

;; Write a function which calculates the sum of all natural numbers
;; under n (first argument) which are evenly divisible by at least one
;; of a and b (second and third argument).
;; Numbers a and b are guaranteed to be coprimes.

;; Note: Some test cases have a very large n, so the most obvious
;; solution will exceed the time limit.

(defn big-divide "Calculates the sum of all natural numbers under which are evenly divisible by at least one of a and b"
  [n a b]
  (letfn [(borne-max [n b] (loop [curr (dec b)] (if (zero? (rem curr n)) curr (recur (dec curr)))))
          (sum-1-borne [n] (* n 1/2 (inc n)))
          (sum-multiple-of-n [n max] (let [borne (borne-max n max) sum (sum-1-borne (/ borne n))] (* n sum)))]
    (- (+ (sum-multiple-of-n a n) (sum-multiple-of-n b n)) (sum-multiple-of-n (* a b) n))))

(fact 
  (big-divide 3 17 11) => 0
  (big-divide 10 3 5) => 23
  (big-divide 1000 3 5) => 233168)

(future-fact "too big numbers"
             (str (big-divide 100000000 3 5)) => "2333333316666668"
             (str (big-divide (* 10000 10000 10000) 7 11)) => "110389610389889610389610"
             (str (big-divide (* 10000 10000 10000) 757 809)) => "1277732511922987429116"
             (str (big-divide (* 10000 10000 1000) 1597 3571)) => "4530161696788274281")

(println "--------- END 148  ----------" (java.util.Date.))
