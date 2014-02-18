# Dieter React

Compile your jsx files with dieter.

## Usage

In your project.clj file

    [org.clojars.scpike.dieter/react "0.1.0"]

Insert it into your ring middleware stack

```clojure
(use '[dieter :only [asset-pipeline]])
(require 'dieter.asset.react)

(-> app
    (asset-pipeline config-options))
```

Your .jsx files will now be compiled with react. Remember to include
the JSX pragma!

    /** @jsx React.DOM */

## License

Copyright (C) 2014 Stephen Pike

Distributed under the Eclipse Public License, the same as Clojure.
