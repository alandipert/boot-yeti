# boot-yeti example project

This is a tiny factorial calculator project based on an
[example in the Yeti tutorial](http://dot.planet.ee/yeti/intro.html#public-classes).

Compile the Yeti source and generate a jar:

    boot yeti uber jar -m fac.Main

Run:

    java -jar target/project.jar 5

**Note: The Yeti compiler only seems to work on Java 7.**
