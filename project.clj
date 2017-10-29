(defproject kosmos/kosmos-nrepl "0.0.5-SNAPSHOT"

  :description "simple nrepl server component"

  :url "https://bitbucket.org/pupcus/kosmos-nrepl"

  :scm {:url "git@bitbucket.org:pupcus/kosmos-nrepl"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.7"]
                 [org.clojure/tools.nrepl "0.2.13"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies [[cider/cider-nrepl "0.15.1"]
                                  [refactor-nrepl "2.3.1"]
                                  [org.clojure/clojure "1.8.0"]
                                  [org.slf4j/slf4j-log4j12 "1.7.25"]]}}

  :deploy-repositories [["snapshots"
                         {:url "https://clojars.org/repo"
                          :sign-releases false
                          :creds :gpg}]
                        ["releases"
                         {:url "https://clojars.org/repo"
                          :sign-releases false
                          :creds :gpg}]]

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :global-vars {*warn-on-reflection* true
                *assert* false})

