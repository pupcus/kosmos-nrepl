(defproject kosmos/kosmos-nrepl "0.0.2"

  :description "simple nrepl server component"

  :url "https://bitbucket.org/pupcus/kosmos-nrepl"

  :scm {:url "git@bitbucket.org:pupcus/kosmos-nrepl"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.6"]
                 [org.clojure/tools.nrepl "0.2.12"]]

  :profiles {:dev  {:resource-paths ["dev-resources"]
                    :dependencies [[org.clojure/clojure "1.8.0"]
                                   [org.slf4j/slf4j-log4j12 "1.7.5"]]}}

  :deploy-repositories [["snapshots"
                         {:url "https://clojars.org/repo"
                          :creds :gpg}]
                        ["releases"
                         {:url "https://clojars.org/repo"
                          :creds :gpg}]])

