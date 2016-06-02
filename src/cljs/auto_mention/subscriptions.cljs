(ns auto-mention.subscriptions
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :results-query
 (fn [db _]
   (reaction (:completions @db))))
