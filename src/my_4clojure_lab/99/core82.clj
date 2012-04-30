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

(defn neighbors "Given a graph g and a node n, return the node n's neighbors."
  [g n]
  (g n))

(fact
  (neighbors {:a [:b :c] :b [] :c []} :a) => [:b :c])

(defn lazy-walk
  "Return a lazy sequence of the nodes of a graph starting a node n.
  Optionally, provide a set of visited notes (v) and a collection of nodes to visit (ns)."
  ([g n]
     (lazy-walk g [n] #{}))
  ([g ns v]
     (lazy-seq (let [s (seq (drop-while v ns))
                     n (first s)
                     ns (rest s)]
                 (when s
                   (cons n (lazy-walk g (concat (g n) ns) (conj v n))))))))

(fact "lazy-walk"
 (lazy-walk {:a [:d :b]
             :b [:c]} :a) => [:a :d :b :c]
 (lazy-walk {:a [:d :b]
             :b [:c]
             :d [:e :c]} :a) => [:a :d :e :c :b])

;; algo:
;; (cp-from :a {:a [:b :d]
;;              :b [:c]
;;              :d [:c :e]
;;              :e [:c]})

;; not-visited stack [] already visited nodes #{}

;; [:a]    #{}            []
;; [:b :d] #{:a}          []
;; [:c :d] #{:a :b}       []

;; [:d]    #{:a :b :c}    []    :c has no children -> we will pop the stack,
;;                              the node goes into the vector of paths,
;;                              if the node owns children yet to be visited, the node stays in the stack of visited nodes
;;         #{:a :b} =>    [:c]       :c no children
;;         #{:a}    =>    [:c :b]    :b no more children left to visit
;;         #{:a}    =>    [:c :b :a] :a still owns children to visit (:d)
;; [:d]    #{:a}          [[:c :b :a]] (just to be clear, not a round, just a summary of the state)

;; [:c :e] #{:a :d}       [[:c :b :a]]

;; [:e]    #{:a :d :c}    [[:c :b :a]]  :c has no children...
;;         #{:a :d}       [:c]
;;         #{:a :d}       [:c :d]    :d still owns children to be visited
;;         #{:a :d}       [:c :d :a]
;; [:e]    #{:a :d}       [[:c :b :a] [:c :d :a]]

;; [:c]    #{:a :d :e}    [[:c :b :a] [:c :d :a]]
;; []      #{:a :d :e :c} [[:c :b :a] [:c :d :a]]

;; [[:c :b :a] [:c :d :a] [:c :e :d :a]] -> all paths possibles from the tree.

(defn cp-from "Compute all the possible paths from the word w and all the paths p"
  [p w]
  (loop [nv [w] v #{} m p rs []]
    (if (empty? nv)
      (concat rs (into [] v))
      (let [n (peek nv)]
        (if n
          (let [c (m n)
                ns (pop nv)
                nnv (conj v n)]
            (if c
              (recur (into ns c) nnv (dissoc m n) rs)
              (let [fv (set (filter #(some (set (p %)) ns) nnv))]
                (recur ns fv m (conj rs (seq nnv))))))
          rs)))))

(fact "compute-path-from"
  (cp-from {:a [:b :d]
            :b [:c]
            :d [:c]} :a) => '((:a :c :d) (:a :c :b)))

(fact
  (cp-from {:a [:b :d]
            :b [:c]
            :d [:c :e]} :a) => '((:a :d :e) (:a :c :d) (:a :c :b)))

(fact
  (cp-from {:a [:b :d]
            :b [:c]
            :d [:c :e]
            :e [:c]} :a) => '((:a :c :d :e) (:a :c :d :e) (:a :c :b)))

(fact
  (cp-from {"cot" ["hot"]
            "hot" ["cot"]} "cot") => [["cot" "hot"]])

(defn wc? "word chains"
  [sw]
  (let [p (reduce (fn [m e] (assoc m e (words e (set (remove #{e} sw))))) {} sw)
        cp (mapcat #(cp-from p %) sw)
        fcp (filter #(= (count sw) (count %)) cp)]
    (not (empty? fcp))))

(fact "wc? true"
  (wc? #{:w1 :w2 :w3}) => true
  (provided
    (words :w1 #{:w3 :w2}) => #{:w3}
    (words :w2 #{:w3 :w1}) => #{:w1}
    (words :w3 #{:w2 :w1}) => #{:w1 :w2}
    (cp-from {:w1 #{:w3}
              :w2 #{:w1}
              :w3 #{:w1 :w2}} :w1) => [[:w1 :w3 :w2] [:w1 :w3]]
    (cp-from {:w1 #{:w3}
              :w2 #{:w1}
              :w3 #{:w1 :w2}} :w2) => []
    (cp-from {:w1 #{:w3}
              :w2 #{:w1}
              :w3 #{:w1 :w2}} :w3) => []))

(fact "wc? false"
  (wc? #{:w1 :w2 :w3}) => false
  (provided
    (words :w1 #{:w2 :w3}) => #{:w2}
    (words :w2 #{:w3 :w1}) => #{:w1}
    (words :w3 #{:w2 :w1}) => #{:w3}
    (cp-from {:w1 #{:w2}
              :w2 #{:w1}
              :w3 #{:w3}} :w1) => [[:w1 :w2] [:w1 :w2]]
    (cp-from {:w1 #{:w2}
              :w2 #{:w1}
              :w3 #{:w3}} :w2) => [[:w2 :w1]]
    (cp-from {:w1 #{:w2}
              :w2 #{:w1}
              :w3 #{:w3}} :w3) => [[:w3]]))

;.;. The biggest reward for a thing well done is to have done it. -- Voltaire
(fact
 (wc? #{"cot" "hot"}) => true)

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
