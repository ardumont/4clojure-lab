(ns ^{:doc "http://www.4clojure.com/problem/104#prob-title"}
  clojure4-lab.149.core104
  (:use clojure.repl
        clojure.java.javadoc
        [midje.sweet]
        [clojure.pprint :only [pprint]]))



;; This is is the inverse of Problem 92, but much easier. Given an integer smaller than 4000, return the corresponding
;; roman numeral in uppercase, adhering to the subtractive principle.

(defn roman "Roman natural"
  [n]
  (cond
   (<= 0 (- n 1000)) (str "M"  (roman (- n 1000)))
   (<= 0 (- n 900))  (str "CM" (roman (- n 900)))
   (<= 0 (- n 500))  (str "D"  (roman (- n 500)))
   (<= 0 (- n 400))  (str "CD" (roman (- n 400)))
   (<= 0 (- n 100))  (str "C"  (roman (- n 100)))
   (<= 0 (- n 90))   (str "XC" (roman (- n 90)))
   (<= 0 (- n 50))   (str "L"  (roman (- n 50)))
   (<= 0 (- n 40))   (str "XL" (roman (- n 40)))
   (<= 0 (- n 10))   (str "X"  (roman (- n 10)))
   (<= 0 (- n 9))    (str "IX" (roman (- n 9)))
   (<= 0 (- n 5))    (str "V"  (roman (- n 5)))
   (<= 0 (- n 4))    (str "IV" (roman (- n 4)))
   (<= 0 (- n 1))    (str "I"  (roman (- n 1)))))

;.;. For every disciplined effort, there is a multiple reward. -- Rohn
(fact
  (roman 1)    =>"I"
  (roman 30)   =>"XXX")

(fact
  (roman 4)    =>"IV"
  (roman 140)  =>"CXL"
  (roman 827)  =>"DCCCXXVII"
  (roman 3999) =>"MMMCMXCIX"
  (roman 48)   =>"XLVIII")
