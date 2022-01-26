# Marvel API Test Application

This is a test app showcasing the Marvel API

# What does the app do?

The app loads the list of all Marvel Characters from the Marvel API and displays them. If you click
on one of the characters, you get redirected to a new Fragment with a character description.

# What architecture does the app use?

MVVM (Model View ViewModel). It also uses SOLID principles.

The app is divided in three layers (app, domain and data).

| Layer | Description |
| --- | --- |
| Data | Handles the API calls and the raw API data |
| Domain | Receives requests from App and turns the raw API data into usable models |
| App | The app itself. It asks Domain for data and displays it, as well as handling user interaction |

The Data layer contains two different data sources: the local Room database and network calls. When new data is requested, it's introduced to the database, which notifies the UI via Flow.

The Room database gets reset on startup to avoid displaying outdated data. 

The Domain layer contains the use cases themselves. They ask the Data layer for raw data, convert them to the model used by the Database and insert them, and create the flow between the Database and the UI.

# Marvel API

The app uses the Marvel API in order to show all the displayed data.

The used methods are:

- [/v1/public/characters](https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0)

## Used libraries

- [Retrofit](https://square.github.io/retrofit/) (API Consumption)
- [OkHttp](https://square.github.io/okhttp/) (Manipulation of Retrofit queries)
- [Hilt](https://dagger.dev/hilt/) (Dependency Injection)
- [Glide](https://github.com/bumptech/glide) (Image loading tool)
- [Flows](https://developer.android.com/kotlin/flow) (Communication between layers)
- [Room](https://developer.android.com/training/data-storage/room) (Local database)

## Testing

- JUnit
- Kotlin Coroutines Test  
- Mockito

## Use of Flavors

The app uses two different flavors, Blue and Red, that change the UI of the app and certain attributes like the database name.

## Screenshots

![List](/screenshots/list.jpg=135x281)
![Detail](/screenshots/detail.jpg)


