(ns boardgames.view

(:require 
    [boardgames.database :as db]
    [boardgames.layout :as layout]
    [hiccup.page :refer :all]
    [hiccup.core :refer [h]]
    [hiccup.form :as form]
    [ring.util.anti-forgery :as anti-forgery]))

(defn set-availability [availability]
  (if (= availability true) "Yes" "No"))

(defn set-category [category]
   (if (= category 1) "Family Game"
   (if (= category 2) "Role-Playing Game"
   (if (= category 3) "Card Game"
   (if (= category 4) "Strategy Game"
   (if (= category 5) "Other"))))))

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
            [:td (set-category (:category boardgame))]
            [:td (:numberofsold boardgame)]
            [:td (set-availability (:availability boardgame))]
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

(defn update-form [boardgame]
  [:br]
  [:div 
   [:h2 {:class "text-center"} "Edit Board Game"]
    (map 
      (fn [boardgame]
     (form/form-to [:post "/edit"]
                 (anti-forgery/anti-forgery-field)
                 (form/hidden-field "id" (:id boardgame))
                 [:div {:class "form-group"}
                  (form/label "name" "Name")
                  (form/text-field {:class "form-control"} "name" (:name boardgame) )]
                 [:div {:class "form-group"}
                  (form/label "category" "Category")
                  (form/drop-down {:class "form-control"} "category" ["Family Game","Role-Playing Game","Card Game", "Strategy Game", "Other"](set-category (:category boardgame)))]
                 [:div {:class "form-group"}
                  (form/label "numberofsold" "Sold number")
                  (form/text-field {:class "form-control"} "numberofsold" (:numberofsold boardgame) )]
                 [:div {:class "form-group"}
                  (form/label "availability" "Available")
                  (form/check-box {:class "form-control check-box-style" :style "height: 30px; width: 30px"} "availability" (:availability boardgame))]
                 (form/submit-button {:class "btn btn-primary pull-right" }  "Edit Board Game")
                 [:br])) boardgame)]
 )

(defn show-update-form [boardgame]
  (layout/root ""
            (update-form boardgame)))
