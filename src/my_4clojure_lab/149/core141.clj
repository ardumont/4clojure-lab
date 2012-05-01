(ns ^{:doc "http://www.4clojure.com/problem/141#prob-title"}
  my-4clojure-lab.149.core141
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

;; Tricky card games
 
;; Difficulty:	Medium
;; Topics:	game cards

;; In trick-taking card games such as bridge, spades, or hearts, cards are played in groups known as "tricks" - each
;; player plays a single card, in order; the first player is said to "lead" to the trick. After all players have played,
;; one card is said to have "won" the trick. How the winner is determined will vary by game, but generally the winner is
;; the highest card played in the suit that was led. Sometimes (again varying by game), a particular suit will be
;; designated "trump", meaning that its cards are more powerful than any others: if there is a trump suit, and any
;; trumps are played, then the highest trump wins regardless of what was led.

;; Your goal is to devise a function that can determine which of a number of cards has won a trick. You should accept a
;; trump suit, and return a function winner. Winner will be called on a sequence of cards, and should return the one
;; which wins the trick. Cards will be represented in the format returned by Problem 128, Recognize Playing Cards: a
;; hash-map of :suit and a numeric :rank. Cards with a larger rank are stronger.

(defn trump ""
  [s]
  (fn [v]
    (let [s (if s s (:suit (nth v 0)))
          o (filter #(= s (:suit %)) v)
          m  (reduce #(max % (:rank %2)) 0 o)]
      (nth (filter #(= m (:rank %)) o) 0))))

(fact
  (let [notrump (trump nil)]
    (notrump [{:suit :club :rank 4}
              {:suit :club :rank 9}]) => {:suit :club :rank 9} 
    (notrump [{:suit :spade :rank 2}
              {:suit :club :rank 10}]) => {:suit :spade :rank 2}))

(fact
  ((trump :club) [{:suit :spade :rank 2}
                  {:suit :club :rank 10}]) => {:suit :club :rank 10})

(fact
  ((trump :heart) [{:suit :heart :rank 6}
                   {:suit :heart :rank 8}
                   {:suit :diamond :rank 10}
                   {:suit :heart :rank 4}]) =>  {:suit :heart :rank 8})