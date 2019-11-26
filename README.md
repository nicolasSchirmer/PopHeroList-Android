# PopHeroList
List of top 3 heroes based on their popularity.

<img src="pophero_screenshot.png" alt="pophero_screenshot" style="zoom: 33%;" />

This is an app that is truly modular, with instrumentation/unit test in each module that test apply and doesn't have hardcoded parameters.

### [APK](base-hml-debug.apk)

## How it works

The app searches on the bitbucket api to discover which years are available inside the project repository. Then saves in memory the list of years and request the json through the bitbucket api as well.

### Architecture
This solution is kind overkill for this project, but I was hoping to show a good and scalable solution.

<img src="overkill.jpeg" alt="overkill" style="zoom: 33%;" />

This app have multiple internal libraries that help build a more consistent architecture and also have modules (dynamic features) to isolate each feature as needed. This modular approach is great to large scale projects and teams due to it's easy of adding and deleting things.

#### - App Archtecture

<img src="uml/arch.jpg" alt="arch" style="zoom: 33%;" />

#### - Gradle scheme

<img src="uml/gradle.jpg" alt="gradle_scheme" style="zoom: 33%;" />

#### - NetworkChecker

Check if there really is internet connection reaching the device and also tell what kind of connection it has.

<img src="uml/network_checker.jpg" alt="netcheck" style="zoom: 33%;" />

#### - HttpRequester

Layer of HTTP requests context free that works with generic types.

<img src="uml/http_requester.jpg" alt="httpreq" style="zoom: 33%;" />

#### - WebApi

Layer to call services and filter (if needed) the server response.

<img src="uml/web_api.jpg" alt="webapi" style="zoom: 33%;" />

#### - ResponseObjects

All "pure" objects that is used in the API's.

<img src="uml/response_objects.jpg" alt="respobj" style="zoom: 33%;" /> 

#### - Widgets

Layer of custom Views context free.

<img src="uml/widgets.jpg" alt="widgets" style="zoom: 33%;" />

#### - CoreUI

Has all common resources, assets, styles, colours and so on.

(No classes to UML)

#### - Base

The base module that connects all the libraries above with the feature modules.

<img src="uml/base.jpg" alt="base" style="zoom: 33%;" />

#### - PopList

Is the hero popularity feature module (dynamic module) that contains the logic to display the list of heroes.

<img src="uml/pop_list.jpg" alt="poplist" style="zoom: 33%;" />



### Third party libraries

#### [Fresco](https://frescolib.org/)

This lib contains an excellent caching, memory managment for bitmaps and resizing. It's very easy to work with image download.

#### [Retrofit](https://square.github.io/retrofit/)

This lib has outstanding capabilities, due to it's easy of use, reliability and modularity. Can be bundled with the [Retrofit Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) to automatic transform json to non annotated classes using [Gson](https://github.com/google/gson). Under the hood this library uses the [OkHttp](https://square.github.io/okhttp/), that is a very powerfull http dns basedlibrary.

#### [OkHttpLogInterceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

An easy way to all API requests.

#### [Gson](https://github.com/google/gson)

Used on the ResponseObjects to add serialization annotation capabilities and the ability to transform any class/list into a Json without the need to add annotations to the class.
