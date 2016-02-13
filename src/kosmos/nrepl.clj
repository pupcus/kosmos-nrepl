(ns kosmos.nrepl
  (:require [clojure.tools.logging :as log]
            [clojure.tools.nrepl.server :as nrepl]
            [com.stuartsierra.component :as component])
  (:import java.net.ServerSocket))

(defrecord NreplComponent [port]
  component/Lifecycle
  (start [component]
    (log/info "Starting nREPL component.")
    (let [nrepl-conf   (merge {:bind "127.0.0.1"}
                              (try
                                (require '[cider.nrepl])
                                {:handler (resolve (symbol "cider.nrepl" "cider-nrepl-handler"))}
                                (catch Exception e
                                  {})))
          start-server (fn [conf]
                         (->> nrepl-conf
                              (merge conf)
                              seq flatten
                              (apply nrepl/start-server)))
          server (try
                   (start-server component)
                   (catch java.net.BindException e
                     (log/warn (str "The port " port
                                    " was unavailable, nREPL will try to choose an open port to bind to."))
                     (start-server (dissoc component :port))))
          ss (:server-socket server)
          host (.getInetAddress ^ServerSocket ss)
          port (.getLocalPort ^ServerSocket ss)]
      (log/info "nREPL started and available at" (str host ":" port))
      (-> component
          (assoc :conn server)
          (assoc :port port))))
  (stop [component]
    (log/info "Stopping nREPL component.")
    (let [server (:conn component)]
      (nrepl/stop-server server)
      (dissoc component :conn))))
