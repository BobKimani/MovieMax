# MovieMax - Android Movie Application

MovieMax is an Android application developed using Jetpack Compose that provides a smooth, modern UI for displaying the latest and upcoming movies using the TMDB API. The app features onboarding screens, a home screen with a list of movies, and a detailed view of each movie.

## Features

- **Onboarding Screens**: A set of three onboarding screens that introduce the app's features.
- **Home Screen**: Displays a list of the latest and upcoming movies fetched from the TMDB API.
- **Movie Detail Screen**: View detailed information about a selected movie, including plot, cast, and more.
- **Smooth Navigation**: Seamless transitions between different screens using Jetpack Navigation.
- **API Integration**: Fetches movie data from the TMDB API using Retrofit.

## Prerequisites

Before running the project, ensure you have the following:

- **Android Studio**: The latest version of Android Studio installed.
- **TMDB API Key**: Create an account at [The Movie Database (TMDB)](https://www.themoviedb.org/) and generate an API key.
- **Android Device or Emulator**: Either a physical Android device or an emulator to run the app.

## Setup Instructions

1. **Clone the Repository**
    ```bash
    https://github.com/BobKimani/MovieMax.git
    cd MovieMax
    ```

2. **Add Your TMDB API Key**
    - Open the project in Android Studio.
    - Locate the `RetrofitInstance.kt` file.
    - Replace `"YOUR_API_KEY"` with your actual TMDB API key:
    ```kotlin
    private const val BASE_URL = "https://api.themoviedb.org/3/"  
    private const val API_KEY = "YOUR_API_KEY"
    ```

3. **Run the App**
    - Connect your Android device or start an emulator.
    - Click "Run" in Android Studio or use the shortcut `Shift + F10`.
    - The app will build and launch on your device or emulator.

## Dependencies

This project uses the following dependencies:

- **Jetpack Compose**: For building the app's UI.
- **Retrofit**: For making API calls to the TMDB API.
- **Gson**: For JSON parsing.
- **OkHttp**: For network requests.
- **Coroutines**: For handling asynchronous tasks.

You can find a complete list of dependencies in the `build.gradle` file.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeatureName`).
3. Commit your changes (`git commit -m 'Add a feature'`).
4. Push your branch (`git push origin feature/YourFeatureName`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments

- **The Movie Database (TMDB)** for providing the API that supplies the movie data.
- **Jetpack Compose** for making UI development smoother and easier.
- **Retrofit** for simplifying the process of making network requests.

Enjoy the app! 🎬🍿
