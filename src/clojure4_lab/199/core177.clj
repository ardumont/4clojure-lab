(ns clojure4-lab.199.core177
  "http://www.4clojure.com/problem/177#prob-title"
  (:use [clojure.pprint :only [pp pprint]]
        [clojure.java.javadoc]
        [midje.sweet]))

;; Balancing Brackets

;; Difficulty:	Medium
;; Topics:	parsing

;; When parsing a snippet of code it's often a good idea to do a sanity check to see if all the brackets match up.
;; Write a function that takes in a string and returns truthy if all square [ ] round ( ) and curly { } brackets are properly paired and legally nested, or returns falsey otherwise.

(defn bb [s]
  (let [map-symbols {\) \(
                     \} \{
                     \] \[}
        inc-cnt (fn [m s] (update-in m [s] + 1))]
    (loop [acc            {\( 0 \) 0
                           \{ 0 \} 0
                           \[ 0 \] 0}
           [h & t :as l]  s
           [c & r :as st] []]
      (if l
        (cond (#{\( \{ \[} h) (recur (inc-cnt acc h) t (cons h st))
              (#{\) \} \]} h) (let [sc (get acc h)
                                    so (->> h
                                            (get map-symbols)
                                            (get acc))]
                                (if (or (not= c so) (< so (inc sc))) false (recur (inc-cnt acc h) t r)))
              :else (recur acc t st))
        (->> [[\{ \}]
              [\( \)]
              [\[ \]]]
             (map (comp (fn [[o c]] (- o c))
                        (fn [[o c]] [(get acc o) (get acc c)])))
             (apply +)
             (= 0))))))

(fact (bb "This string has no brackets.") => truthy)

(fact (bb "class Test {

      public static void main(String[] args) {

        System.out.println(\"Hello world.\");

      }

    }") => truthy)

(fact (bb "(start, end]") => falsey)

(fact (bb "())") => falsey)

(fact (bb "[ { ] } ") => falsey)

(fact (bb "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))") => truthy)

(fact (bb "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))") => falsey)

(fact (bb "[") => falsey)
