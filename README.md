# boot-yeti [![Build Status][travis-badge]][travis-build]

[![Clojars Project][clojars-badge]][clojars-page]

A [Boot] task for compiling [Yeti] programs.

Run a Yeti REPL with [JLine] support:

    boot -d alandipert/boot-yeti yeti -r

See the example project's [README](example/README.md) and
[build.boot](example/build.boot) for more details.

**Note: The Yeti compiler only seems to work on Java 7.**

[clojars-badge]: http://clojars.org/alandipert/boot-yeti/latest-version.svg?cache=2
[clojars-page]: http://clojars.org/alandipert/boot-yeti
[travis-badge]: https://travis-ci.org/alandipert/boot-yeti.svg?branch=master
[travis-build]: https://travis-ci.org/alandipert/boot-yeti
[Boot]: http://boot-clj.com/
[Yeti]: http://mth.github.io/yeti/
[JLine]: http://jline.sourceforge.net/
