(ns polimorfismo.storage)


(defprotocol IStorage
  (mget [this bucket key])
  (put [this bucket key value])
  (delete [this bucket key])
  (close [this]))
