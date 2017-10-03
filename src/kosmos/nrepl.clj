(ns kosmos.nrepl
  (:require [clojure.tools.logging :as log]
            [clojure.tools.nrepl.server :as nrepl]
            [com.stuartsierra.component :as component]
            [kosmos.util :as u])

  (:import java.net.ServerSocket))

(defn build-cider-refactor-handler [handler]
  (when handler
    (try
      (require '[refactor-nrepl.middleware])
      (apply nrepl/default-handler (cons (u/resolve-symbol :refactor-nrepl.middleware/wrap-refactor)
                                         (map resolve (deref (u/resolve-symbol :cider.nrepl/cider-middleware)))))
      (catch Exception e
        (log/debug e)
        (log/info "[refactor-nrepl.middleware] dependency not found")
        (comment "ignore: it didn't work")))))


(defrecord NreplComponent [port]
  component/Lifecycle
  (start [component]
    (log/info "Starting nREPL component.")
    (let [nrepl-conf   (merge {:bind "127.0.0.1"}
                              (try
                                (require '[cider.nrepl])
                                (let [nrepl-handler (resolve (symbol "cider.nrepl" "cider-nrepl-handler"))
                                      handler-fn (or (build-cider-refactor-handler nrepl-handler)
                                                     nrepl-handler)]
                                  {:handler handler-fn})
                                (catch Exception e
                                  (log/debug e)
                                  (log/info "[cider.nrepl] dependency not found")
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
