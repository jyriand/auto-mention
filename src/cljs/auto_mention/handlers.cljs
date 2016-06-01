(ns auto-mention.handlers
  (:require [auto-mention.helpers :as h]
            [re-frame.core :refer [register-handler dispatch]]
            [clojure.string :as s]))

(register-handler
 :initialize
 (fn [db [_ value]]
   (merge db value)))

(register-handler
 :text-change
 (fn [db [_ value]]
   (let [text (h/inner-text value)]
     (assoc db :text text)
     (dispatch [:parse-text text])
     db)))

(register-handler
 :parse-text
 (fn [db [_ value]]
   (if (s/includes? value "@")
     (js/console.log "Match")
     (js/console.log "No match"))
   db))
