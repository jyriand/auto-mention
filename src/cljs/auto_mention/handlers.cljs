(ns auto-mention.handlers
  (:require [auto-mention.helpers :as h]
            [auto-mention.parser :as parser]
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
   (let [trigger-words (parser/trigger-words \@ value)]
     (if (seq trigger-words)
       (assoc db :completions {:people ["Rich Hickey" "Alan Turing"]})
       (assoc db :completions nil)))))
