(ns polimorfismo.core
  (:require [clojure.string :as s]))

;;; Polimorfismo baseado na aridade da função
(defn hello
  ([] "Hello World!")
  ([name] (str "Hello, " name))
  ([name & more] (str "Hello to this group: "
                      (s/join ", " (concat [name] more)))))



;; Multimétodos
;; Maneira flexível de associar uma função com um conjunto
;; de entradas.
;; É um tipo de polimorfismo onde a função de dispatch
;; é definida pelo próprio programador.
;; (defmulti name distpatch-fn)

(def animal {:nome "Nome do Animal"
             :dieta [:carnivora
                     :herbivora]})


(declare dieta-herbivora
         dieta-carnivora
         dieta-desconhecida)

(defn dieta1
  [animal]
  (cond
    (= :herbivoro (:dieta animal))
    (dieta-herbivora animal)

    (= :carnivoro (:dieta animal))
    (dieta-carnivora animal)

    :else
    (dieta-desconhecida animal)))


(defn dieta2
  [animal]
  (condp = (:dieta animal)
    :herbivora (dieta-herbivoro animal)
    :carnivora (dieta-carnivoro animal)
    (dieta-desconhecida animal)))

(defn dieta3
  [animal]
  (case (:dieta animal)
    :herbivora (dieta-herbivora animal)
    :carnivora (dieta-carnivora animal)
    ;;;default
    (dieta-desconhecida animal)))



(defmulti dieta (fn [x] (:dieta x)))

(defmethod dieta :herbivora
  [animal]
  (str (:nome animal) " come vegetais."))

(defmethod dieta :carnivora
  [animal]
  (str (:nome animal) " come carne."))

(defmethod dieta :default
  [_]
  "Não sei o que come.")


(defn testa-dieta
  []
  (let [oogway {:nome "Oogway" :dieta :herbivora}
        simba {:nome "Simba" :dieta :carnivora}
        picapau {:nome "Woody" :dieta :onivora}]
    (do
      (println (dieta oogway))
      (println (dieta simba))
      (println (dieta picapau)))))



(letfn [(testa-dieta []
          (let [oogway {:nome "Oogway" :dieta :herbivora}
                simba {:nome "Simba" :dieta :carnivora}
                picapau {:nome "Woody" :dieta :onivora}]
            (do
              (println (dieta oogway))
              (println (dieta simba))
              (println (dieta picapau)))))]
  (testa-dieta))

(println ::vlad)
(alias 'core 'polimorfismo.core)
(println ::core/vlad)
