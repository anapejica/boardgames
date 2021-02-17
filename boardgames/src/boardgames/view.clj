(ns boardgames.view

(:require 
    [boardgames.database :as db]
    [hiccup.page :refer :all]))

(defn set-availability [availability]
  (if (= availability true) "Yes" "No"))

(defn common [& body]
  (html5
    [:head
     [:title "Board games"]]
    [:body
     [:div 
      body 
      ]
     ]))

(defn show-all-boardgames []
  [:table 
   [:thead
    [:tr
     [:th "Game"]
     [:th "Price"]
     [:th "Category"]
     [:th "Number of sold"]
     [:th "Available"]]]
   (into [:tbody ]
         (for [boardgame (db/get-all-boardgames)]
           [:tr
            [:td (:name boardgame)]
            [:td (:price boardgame) "  RSD"]
            [:td (:category boardgame )]
            [:td (:numberofsold boardgame)]
            [:td (set-availability (:availablity boardgame))]
            ]))])

(defn index-page [boardgames]
  (common ""
            [:h1 "Board games "]
            [:h2 "List of Board Games"]
            [:br]
            (show-all-boardgames)))


