# mindvalley-coding-challenge
This repository contains an Android app coding challenge to recreate the channel view that is on the Mindvalley app.

## Used technology stack and libraries

* Language: Kotlin
* Architecture: MVVM (Model-View-ViewModel) + Clean Architecture (Data, Domain and Presentation Layer)
* Domain Layer Abstraction: Repository, Use Case
* UI: Jetpack Compose
* Network Requests: Retrofit2, OkHttp3
* Local Database: Room Persistence
* Dependency Injection: Dagger - Hilt
* Asynchronous Tasks: Kotlin Coroutine + Flow
* JSON Representation: GSON
* Image caching: Coil Image
* Pull to Refresh: Swipe Refresh
* Flow Layout
* Unit and UI Testing: JUnit, Mockito
* Logger: Timber

### What parts of the test did you find challenging and why?

- The most challenging part was to design the UI with jetpack compose and write test cases for it. I developed some small side projects with basic UI with Jetpack Compose before, but I do not have any professional experience in building user interfaces with Jetpack Compose. As I am not fluent in  Jetpack Compose I had to learn a lot of stuff on the fly and implement that in this project. In the end, had a great experience and enjoyed it a lot.

### What feature would you like to add in the future to improve the project?

* Sometimes scrolling the screen feels laggy. This needs to be fixed and optimized.
* All error cases need to be handled properly and show a proper error message to the user for each possible case.
* A search bar at the top of the screen to search for course or series contents. The search field should hide while scrolling down and should show while scrolling up.
* Tap and hold on any content block can show a video trailer, short video, or any  type of preview in a popup dialog to give the user a glimpse of the course/series contents before going to the details screen
* A splash screen would be great with minimal animations