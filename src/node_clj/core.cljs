(ns node-test.core
  (:require [cljs.nodejs :as node]))

(def express (node/require "express"))

(def app (new express))

(defn not-found! [req res]
  (.send res "This is not a valid route"))


(defn greet
  [req res]
  (.send res "Good Afternoon"))

(defn try-status! [req res]
  (.send res (.status 400) "Bad Request"))


(defn -main
  []
  (.all app "*" (not-found!))
  (.get app "/" ) (greet)
  (.get app "/tryStatus" (try-status!) )
  (.listen app
           3000
           (fn []
             (js/console.log "Listenting http://localhost:3000"))))



(set! *main-cli-fn* -main)
