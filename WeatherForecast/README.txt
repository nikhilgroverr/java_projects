# Weather Forecast Application

A simple Java-based weather forecast application that retrieves and displays weather information for selected cities using the OpenWeatherMap API.

## Features
- Displays current temperature, humidity, and weather condition for a selected city.
- Uses OpenWeatherMap API to fetch weather data.
- User-friendly graphical interface created using `Swing`.
- Allows the user to choose from a list of Indian cities.
- Displays the weather details in a clear and concise format.

## Prerequisites
- Java (version 8 or above)
- Internet connection for API calls
- OpenWeatherMap API key (sign up at https://openweathermap.org/ to get your API key)


Set up your API Key
Go to OpenWeatherMap.
Sign up and get your API key.
Replace the API_KEY constant in the code with your own API key:
java
"private static final String API_KEY = "your-api-key-here";"

Compile and Run.

Usage
Select a city from the dropdown list and click on "Get Weather".
The temperature, humidity, and weather condition for the selected city will be displayed.
The data is fetched from the OpenWeatherMap API and displayed in the UI.
Code Structure
WeatherForecast.java: The main class for the weather application.
It uses Swing components for the graphical user interface (GUI).
The weather data is fetched using HTTP requests to the OpenWeatherMap API.
The data is parsed from JSON format and displayed in the GUI.

