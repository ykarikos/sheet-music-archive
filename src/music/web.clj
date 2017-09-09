(ns music.web
  (:require [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [amazonica.aws.s3 :as s3]
            [music.db :as db]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-response]]))

(defn sign-s3 [file-name file-type]
  (let [signed-url (s3/generate-presigned-url
                      {:bucket-name (env :bucket-name)
                       :key file-name
                       :content-type file-type
                       :expires 60
                       :method "PUT"})
        url (str "https://" (env :bucket-name) ".s3.amazonaws.com/" file-name)]
    {:body {:signedRequest (str signed-url)
            :url url}}))

(defroutes app-routes
  (GET "/" []
       (slurp (io/resource "index.html")))
  (wrap-json-response
    (GET "/api/sign-s3" [file-name file-type]
        (sign-s3 file-name file-type)))
  (wrap-json-response
    (GET "/api/search/composers" [composer]
        (db/search-by-composer composer)))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(def app (wrap-params app-routes))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 3000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
