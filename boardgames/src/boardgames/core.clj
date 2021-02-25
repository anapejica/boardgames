(ns boardgames.core

(:require [compojure.core :refer [defroutes GET POST]]
          [boardgames.database :as db]
          [boardgames.view :as view]
          [clojure.string :as string]))

(defn show-all-boardgames []
  (view/index-page (db/get-all-boardgames)))

(defn show-update-boardgame [id]
  (view/show-update-form (db/get-boardgame id)))

(defn edit-boardgame [id name category numberofsold price availability]
    (when-not (string/blank? id)
    (db/update-boardgame id name category numberofsold price availability)
    (show-all-boardgames))
 )

(defn show-add-form []
  (view/show-add-form))

(defn create-new-boardgame [name category numberofsold price availability]
  (db/insert-boardgame name price category numberofsold availability)
  (show-all-boardgames))

(defroutes home-routes
    (GET "/" [](show-all-boardgames))
    (GET "/edit/:id" [id] (show-update-boardgame id))
    (POST "/edit"  [id name category numberofsold price availability] (edit-boardgame id name category numberofsold price availability))
    (GET "/add"  [] (show-add-form))
    (POST "/create-boardgame"  [name category numberofsold price availability] (create-new-boardgame name category numberofsold price availability))
    )