(defproject sheet-music-archive "1.0.0-SNAPSHOT"
  :description "Web app for storing sheet music"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]
                 [amazonica "0.3.95"]
                 [ring/ring-json "0.4.0"]
                 [cheshire "5.6.3"] ; explicit cheshire dependency added to fix java.lang.NoSuchMethodError: com.fasterxml.jackson.databind.JavaType.isReferenceType()
                 [yesql "0.5.3"]
                 [org.postgresql/postgresql "9.4.1207"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]
            [lein-ring "0.11.0"]]
  :main ^:skip-aot music.web
  :ring {:handler music.web/app}
  :hooks [environ.leiningen.hooks]
  :uberjar-name "sheet-music-archive-standalone.jar"
  :profiles {:production {:env {:production true}}})
