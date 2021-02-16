(ns boardgames.database
  (:require [clojure.java.jdbc :as sql]))

(def connection
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//localhost:3306/boardgames?autoReconnect=true&useSSL=false"
   :user "root"
   :password ""})

(defn get-all-boardgames []
  (into [] (sql/query connection ["select * from boardgame"])))

(defn display-all-boardgames []
  (for [boardgame (get-all-boardgames)]
     (println (:name boardgame) (:category boardgame) (:price boardgame) (:description boardgame))
        )
  )