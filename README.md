Building High-Performance Data Visualizations using GWT
=======================================================

This project contains both the slides and tutorial source used during a presentation
to the National Capital Area Google Technology Users group entitled,
"[Building High-Performance Data Visualizations using GWT][meetup]".

The presentation explored the process of designing and implementing an
interactive data visualization using [Google Web Toolkit (GWT)][gwt].
We covered browser and server technologies including DOM, Canvas, SVG,
ReST and [Scala][scala].

The presentation's tutorial compared browser graphical techniques and their
respective technologies that are compatible with GWT. The tutorial simplified
the visualization to a basic bar chart to make the technology similarities
and differences clear.

The server tutorial presented simplistic ReST interfaces and highlighted
a client REST interface using [RestyGWT][restygwt] and a server REST interface
using [RESTEasy][resteasy].

This project uses [Apache Maven][mvn] as the build tool. You can import this
project into your favorite development IDE or run `mvn verify gwt:run` from
the command-line.

**Note: I need to commit my changes back to the RaphaelGWT project in order to build
this project. Look for an update to the pom.xml file in the near future once the
changes are committed.**

You can find out more about my technical interests and projects on my
[web site][jcb].

[gwt]: http://code.google.com/webtoolkit/ "Google Web Toolkit"
[scala]: http://www.scala-lang.org/ "Scala"
[restygwt]: http://restygwt.fusesource.org/ "RestyGWT"
[resteasy]: http://www.jboss.org/resteasy "RESTEasy"
[mvn]: http://maven.apache.org/ "Apache Maven"
[jcb]: http://blog.jon.buffington.name/ "Jon Buffington"
[meetup]: http://www.meetup.com/ncagtug/events/16323595/ "GWT-SIG: Building High-Performance Data Visualizations using GWT"
