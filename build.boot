(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"resources"}
 :dependencies '[[org.clojure/clojure "1.8.0"]         ;; add CLJ
                 [org.clojure/clojurescript "1.8.40"]
                 [adzerk/boot-cljs "1.7.228-1"]
                 [pandeiro/boot-http "0.7.3"]
                 [adzerk/boot-reload "0.4.6"]
                 [adzerk/boot-cljs-repl "0.3.0"]
                 [com.cemerick/piggieback "0.2.1"  :scope "test"]
                 [weasel                  "0.7.0"  :scope "test"]
                 [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT"]
                 [adzerk/boot-test            "1.0.6"]
                 [reagent "0.6.0-alpha"]
                 [reagent-utils "0.1.7"]
                 [re-frame "0.7.0"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[crisptrutski.boot-cljs-test :refer [test-cljs]]
         '[adzerk.boot-test :refer :all])

(deftask dev []
  (comp
   (watch)
   (reload :on-jsload 'auto-mention.core/mount-root)
   (cljs-repl)
   (cljs)
   (serve :dir "target")
   (target :dir #{"target"})))
