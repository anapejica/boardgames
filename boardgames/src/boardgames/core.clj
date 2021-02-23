(ns boardgames.core

(:require [compojure.core :refer [defroutes GET POST]]
          [boardgames.database :as db]
          [boardgames.view :as view]
          [clojure.string :as string]))

(defn show-all-boardgames []
  (view/index-page (db/get-all-boardgames)))

(defn show-update-boardgame [id]
  (view/show-update-form (db/get-boardgame id)))

(defn edit-boardgame [id name category numberofsold availability]
    (when-not (string/blank? id)
    (db/update-boardgame id name category numberofsold availability)
    (show-all-boardgames))
 )

(defroutes home-routes
    (GET "/" [](show-all-boardgames))
    (GET "/edit/:id" [id] (show-update-boardgame id))
    (POST "/edit"  [id name category numberofsold availability] (edit-boardgame id name category numberofsold availability))
    )