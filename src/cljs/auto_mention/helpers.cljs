(ns auto-mention.helpers)

(defn inner-text [e]
  (-> e .-target .-textContent))
