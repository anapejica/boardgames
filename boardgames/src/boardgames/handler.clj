(ns boardgames.handler
(:use ring.middleware.json 
        ring.adapter.jetty)
(:require [compojure.core :refer [defroutes ANY]]
          [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
          [boardgames.core :as core]))

(defroutes routes
  core/home-routes)

(def app
  (wrap-defaults routes site-defaults))