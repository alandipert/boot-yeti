(ns alandipert.boot-yeti.util)

(def safe-security-manager
  (delay (proxy [SecurityManager] []
           (checkPermission [_])
           (checkExit [_] (throw (SecurityException.))))))

(defmacro without-exiting
  "Evaluates body in a context where System/exit doesn't work.
  Returns result of evaluating body, or nil if code in body dared to try."
  [& body]
  `(let [old-sm# (System/getSecurityManager)]
     (System/setSecurityManager ^SecurityManager @safe-security-manager)
     (try ~@body
          (catch SecurityException e#)
          (finally (System/setSecurityManager old-sm#)))))
