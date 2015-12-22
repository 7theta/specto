;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns specto.reporters
  (:require [metrics.reporters.jmx :as jmx]
            [com.stuartsierra.component :as component]))

;;; Types

(defrecord JMXReporter [options]
  component/Lifecycle
  (start [component]
    (if-not (:jmx-reporter component)
      (let [reporter (jmx/reporter options)]
        (jmx/start reporter)
        (assoc component :jmx-reporter reporter))
      component))
  (stop [component]
    (if-let [reporter (:jmx-reporter component)]
      (do (jmx/stop reporter)
          (dissoc component :jmx-reporter))
      component))

  java.io.Closeable
  (close [component] (.stop component)))

 ;;; Public

(defn jmx-reporter
  ([] (jmx-reporter nil))
  ([options] (JMXReporter. (or options {}))))
