(ns boardgames.database
  (:require [clojure.java.jdbc :as sql]))

(def connection
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//localhost:3306/boardgames?autoReconnect=true&useSSL=false"
   :user "root"
   :password ""})

 (defn set-availability [availability]
   (if (= availability "true") 1 0))
 
 (defn set-category [category]
   (if (= category "Family Game") 1
   (if (= category "Role-Playing Game") 2
   (if (= category "Card Game") 3
   (if (= category "Strategy Game") 4
   (if (= category "Other") 5))))))
 
(defn get-all-boardgames []
  (into [] (sql/query connection ["select * from boardgame"])))

(defn display-all-boardgames []
  (for [boardgame (get-all-boardgames)]
     (println (:name boardgame) (:category boardgame) (:price boardgame))
        )
  )
(defn update-boardgame[id name category numberofsold availability]
  (sql/update! connection :boardgame {:id id :name name :category (set-category category) :numberofsold numberofsold :availability (set-availability availability)} ["id = ?" id]))

(defn get-boardgame [id]
  (into [] (sql/query connection ["select * from boardgame where id = ?" id])))

(defn insert-boardgame [name price category numberofsold availability]
  (sql/insert! connection :boardgame [:name :price :category :numberofsold :availability] [name price category numberofsold availability]))
  
  