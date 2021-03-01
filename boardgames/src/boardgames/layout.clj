(ns boardgames.layout
 (:require 
           [hiccup.page :refer :all]
           [hiccup.core :refer :all]
           [hiccup.bootstrap.page :refer :all]
           ))

(defn navbar []
  [:nav {:class "navbar navbar-expand-lg navbar-light bg-light"}
   [:a {:class "navbar-brand" :href "/list"} "Board Games"]
   [:button {:type "button" :class "navbar-toggler" :data-toggle "collapse"
             :data-target "#navbarNavDropdown" :aria-expanded "false" :aria-controls "navbarNavDropdown"
             :aria-label "Toggle navigation"}
    [:span {:class "navbar-toggler-icon"}]]
   	[:div {:class "collapse navbar-collapse" :id "navbarNavDropdown"}
    [:ul {:class "navbar-nav"}
     [:li {:class "nav-item dropdown"}
      [:a {:class "nav-link dropdown-toggle" :href "/list" :id "navbarDropdownMenuLink" :data-toggle "dropdown" :aria-hashpopup "true" :aria-expanded "false"} "Board Games"]
      [:div {:class "dropdown-menu" :aria-labelledby "navbarDropdownMenuLink"}
       [:a {:class "dropdown-item" :href "/add"} "Add New Board Game"]
       [:a {:class "dropdown-item" :href "/list"} "List of Board Games"]
       [:div {:class "dropdown-divider"}]
       [:a {:class "dropdown-item" :href "/family"} "Family Games"]
       [:a {:class "dropdown-item" :href "/roleplay"} "Role-Play Games"]
       [:a {:class "dropdown-item" :href "/card"} "Card Games"]
       [:a {:class "dropdown-item" :href "/strategy"} "Strategy Games"]
       [:a {:class "dropdown-item" :href "/other"} "Other"]
       ]
      ]
	]
    
    ]
    [:a {:class "navbar-brand" :href "/"} "Logout"]])

(defn footer []
  [:footer {:style "position: fixed; bottom: 0; width: 100%;  height: 60px; background-color: #f8f9fa; color: black; text-align: center"}
   [:div {:class "text-center p-3"}
    [:p "© 2021 Copyright: Ana Pejica"]
    ]
  ]
 )

(defn root[& body]
  (html5
    [:head
     [:title "Board games"]
     (navbar)
     (include-js "https://code.jquery.com/jquery-3.2.1.slim.min.js")
     (include-js "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js")
     (include-js "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js")
     (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css")
     (include-js "/js/validation.js")
     ]
    [:body
     [:div {:class "container"}
      body 
      ]
     [:br]
     [:br]
     (footer)] ))