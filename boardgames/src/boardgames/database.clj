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

(defn update-boardgame[id name category numberofsold price availability]
  (sql/update! connection :boardgame {:id id :name name :category (set-category category) :numberofsold numberofsold :price price :availability (set-availability availability)} ["id = ?" id]))

(defn get-boardgame [id]
  (into [] (sql/query connection ["select * from boardgame where id = ?" id])))

(defn find-by-category [category]
  (into [] (sql/query connection ["select * from boardgame where category = ?" category])))

(defn insert-boardgame [name price category numberofsold availability]
  (sql/insert! connection :boardgame [:name :price :category :numberofsold :availability] [name price (set-category category) numberofsold (set-availability availability)]))
  
 (defn delete-boardgame [id]
 (sql/delete! connection :boardgame
            ["id = ?" id]))