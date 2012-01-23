(ns ^{:doc "http://www.4clojure.com/problem/92#prob-title"}
  my-4clojure-lab.core-92
  (:use clojure.repl
        clojure.java.javadoc
        [midje.sweet]
        [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 92  ----------" (java.util.Date.))

;; Roman numerals are easy to recognize, but not everyone knows all the rules necessary to work with them. Write a
;; function to parse a Roman-numeral string and return the number it represents.

;; You can assume that the input will be well-formed, in upper-case, and follow the subtractive principle. You don't
;; need to handle any numbers greater than MMMCMXCIX (3999), the largest number representable with ordinary letters.

(defn roman "Roman natural"
  [s]
  (let [m (zipmap "IVXLCDM" [1 5 10 50 100 500 1000])
        [f & r] (reverse s)]
    ((reduce
      (fn [{:keys [s l]} e]
        (if (< (m e) (m l))
          {:s (- s (m e)) :l e}
          {:s (+ s (m e)) :l e}))
      {:s (m f) :l f}
      r) :s)))

;.;. {M 1000, D 500, C 100, L 50, X 10, V 5, I 1} [V (I X)]
;.;. s 5 l V e I
;.;. s 4 l I e X
(fact 
  (roman "XIV") => 14)

(fact
 (roman "DCCCXXVII") => 827
 (roman "MMMCMXCIX") => 3999
 (roman "XLVIII")  => 48)

(println "--------- END 92  ----------" (java.util.Date.))
