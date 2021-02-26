(ns boardgames.core

(:require [compojure.core :refer [defroutes GET POST]]
          [boardgames.database :as db]
          [boardgames.view :as view]
          [clojure.string :as string]
          [ring.util.response :as ring]))

(defn show-all-boardgames []
  (view/index-page))

(defn show-update-boardgame [id]
  (view/show-update-form (db/get-boardgame id)))

(defn edit-boardgame [id name category numberofsold price availability]
    (when-not (string/blank? id)
    (db/update-boardgame id name category numberofsold price availability)
     (ring/redirect "/"))
 )

(defn show-add-form []
  (view/show-add-form))

(defn create-new-boardgame [name category numberofsold price availability]
  (db/insert-boardgame name price category numberofsold availability)
  (ring/redirect "/"))

(defn delete-boardgame [id]
  (when-not (string/blank? id)
    (db/delete-boardgame id))
  (ring/redirect "/"))

(defn show-all-by-category [category]
  (view/all-boardgames-by-category category))

(defroutes home-routes
    (GET "/" [](show-all-boardgames))
    (GET "/edit/:id" [id] (show-update-boardgame id))
    (POST "/edit"  [id name category numberofsold price availability] (edit-boardgame id name category numberofsold price availability))
    (GET "/add"  [] (show-add-form))
    (POST "/create-boardgame"  [name category numberofsold price availability] (create-new-boardgame name category numberofsold price availability))
    (GET "/delete/:id" [id]  (delete-boardgame id))
    (GET "/:category" [category]  (show-all-by-category category))
    )