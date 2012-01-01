(ns ^{:doc "http://www.4clojure.com/problem/128#prob-title"}
  my-4clojure-lab.core-128
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 128  ----------" (java.util.Date.))

;; A standard American deck of playing cards has four suits - spades,
;; hearts, diamonds, and clubs - and thirteen cards in each suit. Two
;; is the lowest rank, followed by other integers up to ten; then the
;; jack, queen, king, and ace.

;; It's convenient for humans to represent these cards as suit/rank
;; pairs, such as H5 or DQ: the heart five and diamond queen
;; respectively. But these forms are not convenient for programmers,
;; so to write a card game you need some way to parse an input string
;; into meaningful components. For purposes of determining rank, we
;; will define the cards to be valued from 0 (the two) to 12 (the ace)

;; Write a function which converts (for example) the string "SJ" into
;; a map of {:suit :spade, :rank 9}. A ten will always be represented
;; with the single character "T", rather than the two characters "10".

;; Rank Card     Suite
;; 0    two      S spades
;; 1    three    H hearts
;; 2    four     D diamonds
;; 3    five     C clubs
;; 4    six
;; 5    seven
;; 6    eight
;; 7    nine
;; 8    ten
;; 9    jack
;; T    queen
;; 11   king
;; 12   ace

(defn card "Playing card"
  [[s r]]
  (conj {:suit ({\S :spade \D :diamond \H :heart \C :club} s)}
        {:rank ({\2 0 \3 1 \4 2 \5 3 \6 4 \7 5 \8 6 \9 7
                  \T 8 \J 9 \Q 10 \K 11 \A 12} r)}))

(fact
  (card "DQ") => {:suit :diamond :rank 10}
  (card "H5") => {:suit :heart :rank 3}
  (card "CA") => {:suit :club :rank 12}
  (map (comp :rank card str)
       '[S2 S3 S4 S5 S6 S7
         S8 S9 ST SJ SQ SK SA]) => (range 13))

(println "--------- END 128  ----------" (java.util.Date.))
