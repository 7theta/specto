;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require [specto.reporters :refer [jmx-reporter]]

            [reloaded.repl :refer [system init start stop go reset reset-all]]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [com.stuartsierra.component :as component]

            [clojure.repl :refer [apropos dir doc find-doc pst source]]
            [clojure.reflect :refer [reflect]]
            [clojure.pprint :refer [pprint]]

            [clojure.test :refer [run-tests run-all-tests]]))

(defn dev-system
  [_config]
  (component/system-map
   :reporter (jmx-reporter)))

(reloaded.repl/set-init! #(dev-system nil))
