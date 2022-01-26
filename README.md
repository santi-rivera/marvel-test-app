# Marvel API Test Application

This is a test app showcasing the Marvel API

# What does the app do?

The app loads the list of all Marvel Characters from the Marvel API and displays them. If you click
on one of the characters, you get redirected to a new Fragment with a character description.

# What architecture does the app use?

MVVM (Model View ViewModel). It also uses SOLID principles.

The app is divided in three layer (app, domain and data).

| Layer | Description |
| --- | --- |
| Data | Handles the API calls and the raw API data |
| Domain | Receives requests from App and turns the raw API data into usable models |
| App | The app itself. It asks Domain for data and displays it, as well as handling user interaction |

# Marvel API

The app uses the Marvel API in order to show all the displayed data.

The used methods are:

- [/v1/public/characters](https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0)
- [/v1/public/characters/{characterId}](https://developer.marvel.com/docs#!/public/getCharacterIndividual_get_1)

## Used libraries

- [Retrofit](https://square.github.io/retrofit/) (API Consumption)
- [OkHttp](https://square.github.io/okhttp/) (Manipulation of Retrofit queries)
- [Hilt](https://dagger.dev/hilt/) (Dependency Injection)
- [Glide](https://github.com/bumptech/glide) (Image loading tool)

## Screenshots

- List

![List](/screenshots/list.jpg)

- Detail

![Detail](/screenshots/detail.jpg)
