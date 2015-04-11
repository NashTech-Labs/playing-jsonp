# playing-jsonp

Basic example to integrate JSONP mechanism in Play Application with the help of play-jsonp-filter module

###[play-jsonp-filter](https://github.com/julienrf/play-jsonp-filter)
This filter enables JSONP on your existing API: any resource that returns a JSON content will return a JavaScript fragment if there is an additional `callback` parameter in the query string.

For example, if the resource `/foo` gives the following JSON result: `{"foo": "bar"}`, the resource `/foo?callback=f` will give the following JavaScript result: `f({"foo": "bar"});`.

![alt tag](/public/images/preloader.gif)

- Used [play-jsonp-filter](https://github.com/julienrf/play-jsonp-filter) module to integrate JSONP mechanism
- Embedded JS & CSS libraries with [WebJars](http://www.webjars.org/).
- Integrating with a CSS framework (Twitter Bootstrap)
- Bootswatch-Readable with Twitter Bootstrap to improve the look and feel of the application

-----------------------------------------------------------------------
###Dependency
-----------------------------------------------------------------------
The play-jsonp-filter module is distributed using Maven Central so it can be easily added as a library dependency in your Play Application's SBT build scripts, as follows:

```
"org.julienrf"		%% "play-jsonp-filter" 		% "1.2"
```

-----------------------------------------------------------------------
###Usage
-----------------------------------------------------------------------
Add the `julienrf.play.jsonp.Jsonp` filter to your `Global` object:

```scala
import play.api.mvc.WithFilters
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import julienrf.play.jsonp.Jsonp

object Global extends WithFilters(new Jsonp)
```

-----------------------------------------------------------------------
###Build Instructions
-----------------------------------------------------------------------
* To run the Play Framework, you need JDK 6 or later
* Install Typesafe Activator if you do not have it already. You can get it from [here](http://www.playframework.com/download) 
* Execute `./activator clean compile` to build the product
* Execute `./activator run` to execute the product
* playing-jsonp should now be accessible at localhost:9000

-----------------------------------------------------------------------
###References
-----------------------------------------------------------------------
* [Play Framework](http://www.playframework.com/)
* [play-jsonp-filter](https://github.com/julienrf/play-jsonp-filter)
* [Bootstrap](http://getbootstrap.com/css/)
* [Bootswatch-Readable](http://bootswatch.com/readable/)
* [WebJars](http://www.webjars.org/)

