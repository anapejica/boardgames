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

(defn category-id [category]
   (if (= category "family") 1
   (if (= category "roleplay") 2
   (if (= category "card") 3
   (if (= category "strategy") 4
   (if (= category "other") 5))))))
 
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
            [:td {:style "border-bottom: 0px"} [:a {:onclick "return confirm('Are you sure you want to delete this item?')" :class "btn btn-danger" :href (str "/delete/" (h (:id boardgame)))} "Delete"]]
            [:td {:style "visibility:hidden"} (:id boardgame)]
            ]))])

(defn index-page []
  (layout/root ""
  			[:br]	
            [:h2 {:class "title-second"} "List of Board Games"]
            [:br]
            (show-all-boardgames)))

(defn show-all-by-category [category]
  
  [:table {:class "table table-striped  table-bordered"}
   [:thead
    [:tr
     [:th "Game"]
     [:th "Price"]
     [:th "Category"]
     [:th "Number of sold"]
     [:th "Available"]
     ]]
   (into [:tbody ]
         (for [boardgame (db/find-by-category (category-id category))]
           [:tr
            [:td (:name boardgame)]
            [:td (:price boardgame) "  RSD"]
            [:td (set-category (:category boardgame))]
            [:td (:numberofsold boardgame)]
            [:td (set-availability (:availability boardgame))]
            ]))])


(defn all-boardgames-by-category [category]
  (def temp category)
  (layout/root ""
  			[:br]	
            [:h2 {:class "title-second"} (set-category (category-id temp))"s"]
            [:br]
            (show-all-by-category category)))


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
                  (form/label "price" "Price")
                  (form/text-field {:class "form-control"} "price" (:price boardgame) )]
                 [:div {:class "form-group"}
                  (form/label "availability" "Available")
                  (form/check-box {:class "form-control check-box-style" :style "height: 30px; width: 30px"} "availability" (:availability boardgame))]
                 (form/submit-button {:class "btn btn-primary pull-right" :onclick "return validate()" }  "Edit Board Game")
                 [:br])) boardgame)]
 )

(defn add-form []
  [:br]
  [:div 
   [:h2 {:class "text-center"} "Add New Board Game"]
    
     (form/form-to [:post "/create-boardgame"]
                 (anti-forgery/anti-forgery-field)
                 [:div {:class "form-group"}
                  (form/label "name" "Name")
                  (form/text-field {:class "form-control"} "name")]
                 [:div {:class "form-group"}
                  (form/label "category" "Category")
                  (form/drop-down {:class "form-control"} "category" ["Family Game","Role-Playing Game","Card Game", "Strategy Game", "Other"])]
                 [:div {:class "form-group"}
                  (form/label "numberofsold" "Sold number")
                  (form/text-field {:class "form-control"} "numberofsold")]
                 [:div {:class "form-group"}
                  (form/label "price" "Price")
                  (form/text-field {:class "form-control"} "price")]
                 [:div {:class "form-group"}
                  (form/label "availability" "Available")
                  (form/check-box {:class "form-control check-box-style" :style "height: 30px; width: 30px"} "availability")]
                 (form/submit-button {:onclick "return validate()" :class "btn btn-primary pull-right"  }  "Save")
                 [:br])]
 )
(defn show-add-form []
  (layout/root ""
               (add-form)))
(defn show-update-form [boardgame]
  (layout/root ""
            (update-form boardgame)))
