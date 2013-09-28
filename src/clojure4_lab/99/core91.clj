(ns ^{:doc "http://www.4clojure.com/problem/91#prob-title"}
  clojure4-lab.99.core91
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Given a graph, determine whether the graph is connected. A connected graph is such that a path exists between any two given nodes.
;; -Your function must return true if the graph is connected and false otherwise.
;; -You will be given a set of tuples representing the edges of a graph. Each member of a tuple being a vertex/node in the graph.
;; -Each edge is undirected (can be traversed either direction).

;; Top Down TDD

(unfinished )

(defn graph
  [s]
  (reduce (fn [m [f s]]
            (assoc m
              f (conj (m f []) s)
              s (conj (m s []) f))) {} s))

(fact
  (graph #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}) => {1 [2 3], 6 [5 4], 5 [4 6], 4 [5 6], 3 [2 1], 2 [3 1]})

(defn from
  [s m]
  (set
   (filter
    (comp not nil?)
    (flatten
     (take (+ 1 (count m))
           (iterate #(flatten (map m %)) s))))))

(fact "from"
  (from [:a] {:a [:b :c] :b [:e :f :a] :e [:g]}) => #{:b :e :g :f :a :c}
  (from [:a] {:a [:b :c] :b [:e :f :a]}) => #{:b :e :f :a :c})

(defn connected?
  [s]
  (let [m (graph s)]
    (= (count (from [(nth (keys m) 0)] m)) (count (set (flatten (vals m)))))))

(fact "Connected graph"
  (connected? :set) => true
  (provided
    (graph :set) => {:a [:b :c] :b [:c :a]}
    (from [:a] {:a [:b :c] :b [:c :a]}) => [:a :b :c]))

(fact "Not connected"
  (connected? :set) => false
  (provided
    (graph :set) => {:a [:b :c] :b [:a]}
    (from [:a] {:a [:b :c] :b [:a]}) => [:a :b]))

(fact "IT tests"
  (connected? #{[:a :a]}) => true
  (connected? #{[:a :b]}) => true
  (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6 ] [6 4]}) => false
  (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}) => true
  (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}) => false
  (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}) => true)

;; End Top Down TDD

;; 4clojure

(defn connected?
  [s]
  (let [g (fn [s]
            (reduce (fn [m [f s]]
                      (assoc m
                        f (conj (m f []) s)
                        s (conj (m s []) f))) {} s))
        f (fn [s m]
            (set
             (filter
              (comp not nil?)
              (flatten
               (take (+ 1 (count m))
                     (iterate #(flatten (map m %)) s))))))]
    (let [m (g s)]
      (= (count (f [(nth (keys m) 0)] m)) (count (set (flatten (vals m))))))))

(fact
  (connected? #{[:a :a]}) => true
  (connected? #{[:a :b]}) => true
  (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6 ] [6 4]}) => false
  (connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}) => true
  (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}) => false
  (connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}) => true)
