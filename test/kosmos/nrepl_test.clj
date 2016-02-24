(ns kosmos.nrepl-test
  (:require [clojure.test :refer :all]
            [clojure.tools.nrepl :as repl]
            [kosmos :refer [map->system start! stop! system]]
            [kosmos.nrepl :refer :all]))

(def config {
             :nrepl
             {:kosmos/init :kosmos.nrepl/NreplComponent
              :port        9005
              }
             })

(defn setup-db-server [f]
  (start! (map->system config))
  (f)
  (stop!))

(use-fixtures :once setup-db-server)

(deftest test-nrepl-component
  (let [port (get-in system [:nrepl :port])
        answer (with-open [conn (repl/connect :port port)]
                 (-> (repl/client conn 1000)    ; message receive timeout required
                     (repl/message {:op "eval" :code "(+ 1 1)"})
                     repl/response-values))]
    (is (= answer [2]))))


