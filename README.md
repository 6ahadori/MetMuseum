# Metropolitan Museum of Art Collection App
This is an Android application that uses the Metropolitan Museum of Art Collection API to search and display museum objects. The app is built using MVI architecture, Dagger Hilt for dependency injection, Jetpack Compose for the UI, and implements an Offline First strategy to improve performance and user experience.

## Features
- Search for museum objects using keywords and display search results
- View details of a museum object, including images and descriptions
- Offline support: objects previously viewed are cached for offline access
- Dark and Light themes

## API
This app utilizes the public Metropolitan Museum of Art Collection API, which provides access to a wide range of information about the museum's collection. The API allows you to retrieve information about objects, including images, descriptions, and other relevant details. You can learn more about the API by visiting the [Metropolitan Museum of Art Collection API documentation](https://collectionapi.metmuseum.org/public/collection/).

## Dependencies
This project uses the following dependencies:
- [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection
- [Retrofit](https://square.github.io/retrofit/) for network requests
- [OkHttp](https://square.github.io/okhttp/) for HTTP client
- [Room](https://developer.android.com/jetpack/androidx/releases/room) for local database caching
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI
- [Coil](https://coil-kt.github.io/coil/) for image loading
- [Truth](https://truth.dev/) for unit testing

## Getting Started
To get started with the project, clone this repository to your local machine and open it in Android Studio. Make sure you have the latest version of Android Studio and the Android SDK installed.

### Running the App
Build and run the app using Android Studio or by running the following command from the command line:
```shell
./gradlew installDebug
```

### Running Unit Tests
You can run the unit tests for the project using Android Studio or by running the following command from the command line:
```shell
./gradlew test
```
