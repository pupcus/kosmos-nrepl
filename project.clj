(defproject kosmos/kosmos-nrepl "0.0.1-SNAPSHOT"

  :description "simple nrepl server component"

  :url "https://bitbucket.org/pupcus/kosmos-nrepl"

  :scm {:url "git@bitbucket.org:bitbucket/kosmos-nrepl"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/tools.nrepl "0.2.12"]]

  :deploy-repositories [["snapshots"
                         {:url "https://clojars.org/repo"
                          :creds false}]
                        ["releases"
                         {:url "https://clojars.org/repo"
                          :creds false}]])

