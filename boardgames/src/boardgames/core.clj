(ns boardgames.core

(:require [compojure.core :refer [defroutes GET]]
          [boardgames.database :as db]
          [boardgames.view :as view]))

(defn show-all-boardgames []
  (view/index-page (db/get-all-boardgames)))

(defroutes home-routes
    (GET "/" [](show-all-boardgames))
    )