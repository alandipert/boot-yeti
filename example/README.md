# boot-yeti example project

This is a tiny factorial calculator project written in [Yeti] that
uses the [Boot] build tool.  It's based on an
[example in the Yeti tutorial](http://dot.planet.ee/yeti/intro.html#public-classes).

## Continuously Compile, Run

    boot watch yeti run -m fac.Main -a 5

## Create a Jar

    boot yeti uber jar -m fac.Main target

## Run the Jar

    java -jar target/project.jar 5

[Boot]: http://boot-clj.com/
[Yeti]: http://mth.github.io/yeti/
