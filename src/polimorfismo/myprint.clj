(ns polimorfismo.myprint)

(defn my-print-vector
  [obj]
  (.write *out* "[")
  (.write *out* (clojure.string/join " " obj))
  (.write *out* "]"))



(defn my-print
  [obj]
  (cond
    (nil? obj)
    (.write *out* "nil")

    (string? obj)
    (.write *out* obj)

    (vector? obj)
    (my-print-vector obj)))


(def conta1)

(def conta2
  {:id 2
  :tipo :poupanca
  :saldo 3000})

(defmulti taxa-de-servico :tipo)

(defmethod taxa-de-servico :corrente
  [_]
  25)

(defmethod taxa-de-servico :poupanca
  [_]
  0)

(defmethod taxa-de-servico :conta-digital
  [_]
  5)
(taxa-de-servico conta2)











(defmulti my-print class)

(defmulti f-poli

  (fn [a b] (:type a))

  )

(defmethod f-poli :corrente
  [a b]
  (+ b 1))

(defmethod f-poli :poupanca
  [a b]
  (* b 1.1))

(defmethod my-print Number
  [obj]
  (.write *out* (str obj)))



(defmethod my-print clojure.lang.IPersistentVector
  [obj]
  (.write *out* "[")
  (.write *out* (clojure.string/join " " obj))
  (.write *out* "]"))

(defmethod my-print java.lang.String
  [obj]
  (.write *out* obj))

(defmethod my-print nil
  [obj]
  (.write *out* "nil"))

(defmethod my-print :default
  [obj]
  (.write *out* "Não sei o que fazer."))





(defn my-println
  [obj]
  (my-print obj)
  (my-print "\n"))

(my-println 1)
(my-println nil)
(my-println [1 2 3])
(my-println {})
