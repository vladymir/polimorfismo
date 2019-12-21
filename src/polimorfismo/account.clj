(ns polimorfismo.account
  (:require [clojure.string :as s]))
(alias 'conta 'polimorfismo.account)


(defrecord Conta [id tag saldo])

(defrecord Pessoa [nome idade])


(def conta1 (->Conta 1 ::Corrente 1000M))



(def conta2 (Conta. 2 ::Poupanca 20000M))

(def conta3 (map->Conta {:id 3
                         :tag ::Corrente
                         :saldo 10000M}))


(defmulti juros-conta :tag)

(defmethod juros-conta ::Corrente [_] 0M)
(defmethod juros-conta ::Poupanca [_] 0.5M)
(defmethod juros-conta ::ContaDigital [_] 1)

(juros-conta (->Conta 3 ::ContaDigital 30000))

(defmulti nivel-conta :tag)



(defmethod nivel-conta ::Corrente
  [conta]
  (if (>= (:saldo conta) 5000)
    ::conta/Premium
    ::conta/Basica))

(defmethod nivel-conta ::Poupanca
  [conta]
  (if (>= (:saldo conta) 10000)
    ::conta/Premium
    ::conta/Basica))

(println (nivel-conta conta1))
(println (nivel-conta conta2))
(println (nivel-conta conta3))


;;; abordagem errada
(defmulti taxa-de-servico nivel-conta)

(defmethod taxa-de-servico ::Basica
  [conta]
  (if (= (:tag conta) ::Corrente) 25 10))

(defmethod taxa-de-servico ::Premium
  [_]
  0)

;; O if na linha 44 é justamente o que pretendemos
;; evitar quando usamos multimetodos
;; Fazer dispatch em 2 propriedades

(defmulti taxa-administracao
  (fn [conta] [(nivel-conta conta)
              (:tag conta)]))
[nivel tipo]

(defmethod taxa-administracao [::Basica ::Corrente]
  [conta]
  25)

(defmethod taxa-administracao [::Basica ::Poupanca]
  [conta]
  10)

(defmethod taxa-administracao [::Premium ::Corrente]
  [conta]
  0)

(defmethod taxa-administracao [::Premium ::Poupanca]
  [conta]
  0)

(println (taxa-administracao conta1))


(derive ::Corrente ::Conta)
(derive ::Poupanca ::Conta)
(derive ::Eletronica ::Conta)


(defmethod taxa-administracao [::Premium ::Conta]
  [conta]
  0)



(println (taxa-administracao conta2))
