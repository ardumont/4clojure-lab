(ns ^{:doc "http://www.4clojure.com/problem/150#prob-title"}
  my-4clojure-lab.99.core82
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Word Chains
 
;; Difficulty:	Hard
;; Topics:	seqs

;; A word chain consists of a set of words ordered so that each word differs by only one letter from the words directly
;; before and after it. The one letter difference can be either an insertion, a deletion, or a substitution. Here is an
;; example word chain:
;; cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog

;; Write a function which takes a sequence of words, and returns true if they can be arranged into one continous word
;; chain, and false if they cannot.

(unfinished diff)

(defn ins "Insert in the string w the character c at the index i"
  [w i c]
  (str (subs w 0 i) c (subs w i)))

(fact "ins"
  (ins "test" 3 \x) => "tesxt"
  (ins "test" 0 \x) => "xtest")

(defn del "Given a string, remove the char at the given index"
  [w i]
  (str (subs w 0 i) (subs w (inc i))))

(fact
  (del "test" 0) => "est"
  (del "test" 1) => "tst"
  (del "test" 2) => "tet"
  (del "test" 3) => "tes")

(defn nw "List of characters"
  []
  (map char (range 97 123)))

(fact "list of characters"
  (nw) => '(\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z))

(defn oc? "Does the word w2 contains at least one char in common at the same index that w?"
  [w w2]
  (= 1 (count ((group-by identity (map #(= % %2) w w2)) false))))

(fact "exactly one char different ?"
  (oc? "hot" "hat") => true
  (oc? "bond" "james") => false)

(defn words-sub "Given a word w, gives all the possible substitution in the set sw."
  [w sw]
  (set (filter #(and (= (count %) (count w)) (oc? w %)) sw)))

(fact "words-sub"
  (words-sub "hat" #{"aaa" "hot" "hate" "anything" "sat"}) => #{"hot" "sat"}
  (provided
    (oc? "hat" "aaa") => false
    (oc? "hat" "hot") => true
    (oc? "hat" "sat") => true))

(fact "ITest"
   (words-sub "hat" #{"aaa" "hot" "hate" "anything" "sat"}) => #{"hot" "sat"})

(defn words-del "Given a word, gives all the possible words with one char removed."
  [w]
  (set (for [i (range (count w))] (del w i))))

(fact "words-del"
  (words-del "xyz") => #{"yz" "xz" "xy"})

(defn words-add "Provided a word, add a character to generate a new word in each possible position"
  [w]
  (for [n (nw)
        i (range 0 (inc (count w)))] (ins w i n)))

(fact "words-add"
  (words-add "xy") => (contains #{"axy" "bxy" "cxy"
                                  "xay" "xby" "xcy"
                                  "xya" "xyb" "xyc"} :in-any-order)
  (provided
    (nw) => [\a \b \c]))

(defn words "Compute all the possibles words from a word when removing or adding or subsituting a letter.x"
  [w sw]
  (clojure.set/intersection sw (reduce into #{} [(words-add w) (words-del w) (words-sub w sw)])))

(fact "mock - words"
  (words :w #{:w1 :w2 :w3 :w4 :w5}) => #{:w1 :w2 :w3 :w4}
  (provided
    (words-add :w) => #{:w1 :w2}
    (words-del :w) => #{:w3}
    (words-sub :w #{:w1 :w2 :w3 :w4 :w5}) => #{:w4}))

(fact "ITest - words"
  (words "hat" #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}) => #{"oat" "hot" "cat"}
  (words "oat" #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}) => #{"coat" "cat" "hat"}
  (words "coat" #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}) => #{"oat" "cat" "cot"})

(defn cp-from "Compute all the paths possibles from the word w and all the paths p"
  [w p]
  (map #(let [rp (cp-from % (dissoc p w))] (concat [w %] rp)) (p w)))

(fact "compute-path-from"
  (cp-from :a {:a [:b :d]
               :b [:c]
               :d [:c]}) => [[:a :b :c] [:a :d :c]]
  (cp-from :a {:a [:b :d]
               :b [:c]
               :d [:c :e]}) => [[:a :b :c] [:a :d :c] [:a :d :e]]
  (cp-from :a {:a [:b :d]
               :b [:c]
               :d [:c :e]
               :e [:c]}) => [[:a :b :c] [:a :d :c] [:a :d :c] [:a :d :e :c]])
merge
(defn wc? "word chains"
  [sw]
  (let [p (reduce (fn [m e] (assoc m e (words e (set (remove #{e} sw))))) {} sw)
        cp (reduce (fn [v e] (concat v (cp-from e p))) [] sw)
        fcp (filter #(= (count sw) (count %)) cp)]
    (println p)
    (println cp)
    (println fcp)
    (not= nil ((group-by count fcp) (count sw)))))

(fact "wc? true"
  (wc? #{:w1 :w2 :w3}) => true
  (provided
    (words :w1 #{:w3 :w2}) => #{:w3}
    (words :w2 #{:w3 :w1}) => #{:w1}
    (words :w3 #{:w2 :w1}) => #{:w1 :w2}
    (cp-from :w1 {:w1 #{:w3}
                  :w2 #{:w1}
                  :w3 #{:w1 :w2}}) => [[:w1 :w3 :w2] [:w1 :w3]]
    (cp-from :w2 {:w1 #{:w3}
                  :w2 #{:w1}
                  :w3 #{:w1 :w2}}) => []
    (cp-from :w3 {:w1 #{:w3}
                  :w2 #{:w1}
                  :w3 #{:w1 :w2}}) => []))

(fact "wc? false"
  (wc? #{:w1 :w2 :w3}) => false
  (provided
    (words :w1 #{:w2 :w3}) => #{:w2}
    (words :w2 #{:w3 :w1}) => #{:w1}
    (words :w3 #{:w2 :w1}) => #{:w3}
    (cp-from :w1 {:w1 #{:w2}
                  :w2 #{:w1}
                  :w3 #{:w3}}) => [[:w1 :w2] [:w1 :w2 :w3]]
    (cp-from :w2 {:w1 #{:w2}
                  :w2 #{:w1}
                  :w3 #{:w3}}) => [[:w2 :w1]]
    (cp-from :w3 {:w1 #{:w2}
                  :w2 #{:w1}
                  :w3 #{:w3}}) => [[:w3]]))

(fact
  (wc? #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}) => true)

(fact
  (wc? #{"cot" "hot" "bat" "fat"}) => false)

(fact
  (wc? #{"spout" "do" "pot" "pout" "spot" "dot"}) => true)

(fact
  (wc? #{"share" "hares" "shares" "hare" "are"}) => true)

(fact
  (wc? #{"to" "top" "stop" "tops" "toss"}) => false)

(fact
  (wc? #{"share" "hares" "hare" "are"}) => false)
