(ns ^{:doc "http://www.4clojure.com/problem/67#prob-title"}
  my-4clojure-lab.core-67
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 67  ----------" (java.util.Date.))

(defn prime? "Is the number a prime?"
  [n primes-seq]
  (not (some #(zero? (rem n %)) (take (Math/floor (Math/sqrt n)) primes-seq))))

(fact
  (prime? 11 [2 3 5]) => true
  (prime? 12 [2 3 5]) => false
  (prime? 67 [2 3 5 7 11 13 17]) => true)

(defn lazy-primes "A lazy seq of primes, using the 'n is prime if not divisible by an int > sqrt n' optimization"
  ([]  (lazy-primes 2 []))
  ([n prevs]
     (lazy-seq
      (cons
       n
       (lazy-primes
        (loop [curr (inc n)]
          (if (prime? curr prevs)
            curr
            (recur (inc curr))))
        (conj prevs n))))))

(fact
  (take 5 (lazy-primes))   => [2 3 5 7 11]
  (take 10 (lazy-primes))  => [2 3 5 7 11 13 17 19 23 29]
  (take 15 (lazy-primes))  => [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47]
  (take 100 (lazy-primes)) => [ 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199 211 223 227 229 233 239 241 251 257 263 269 271 277 281 283 293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409 419 421 431 433 439 443 449 457 461 463 467 479 487 491 499 503 509 521 523 541])

;; Write a function which returns the first x number of prime numbers.

(defn primes "A lazy seq of primes, using the 'n is prime if not divisible by an int > sqrt n' optimization"
  [i]
  (letfn [(p? [n ps] (not (some #(zero? (rem n %)) (take (Math/floor (Math/sqrt n)) ps))))
          (lp
            ([] (lp 2 []))
            ([n pv]
               (lazy-seq
                (cons
                 n
                 (lp
                  (loop [c (inc n)]
                    (if (p? c pv)
                      c
                      (recur (inc c))))
                  (conj pv n))))))]
    (take i (lp))))

(fact
  (primes 2) => [2 3]
  (primes 5) => [2 3 5 7 11]
  (last (primes 100)) => 541)

(println "--------- END 67  ----------" (java.util.Date.))
