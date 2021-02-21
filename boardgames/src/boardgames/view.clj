(ns boardgames.view

(:require 
    [boardgames.database :as db]
    [boardgames.layout :as layout]
    [hiccup.page :refer :all]
    [hiccup.core :refer [h]]))

(defn set-availability [availability]
  (if (= availability true) "Yes" "No"))

(defn show-all-boardgames []
  
  [:table {:class "table table-striped  table-bordered"}
   [:thead
    [:tr
     [:th "Game"]
     [:th "Price"]
     [:th "Category"]
     [:th "Number of sold"]
     [:th "Available"]
     [:th "Edit"]
     [:th "Delete"]
     ]]
   (into [:tbody ]
         (for [boardgame (db/get-all-boardgames)]
           [:tr
            [:td (:name boardgame)]
            [:td (:price boardgame) "  RSD"]
            [:td (:category boardgame )]
            [:td (:numberofsold boardgame)]
            [:td (set-availability (:availablity boardgame))]
            [:td {:style "border-bottom: 0px"} [:a {:class "btn btn-primary" :href (str "/edit/" (h (:id boardgame)))} "Edit"]]
            [:td {:style "border-bottom: 0px"} [:a {:class "btn btn-danger" :href (str "/delete/" (h (:id boardgame)))} "Delete"]]
            [:td {:style "visibility:hidden"} (:id boardgame)]
            ]))])

(defn index-page [boardgames]
  (layout/root ""
  			[:br]	
            [:h2 {:class "title-second"} "List of Board Games"]
            [:br]
            (show-all-boardgames)))


