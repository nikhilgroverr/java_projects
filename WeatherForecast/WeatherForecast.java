import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class WeatherForecast {

    private static final String API_KEY = "49723997c134f5d1c8f6d42564f777a8"; // Replace with your OpenWeatherMap API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    private static final String[] CITIES = {
        "Mumbai", "Delhi", "Bengaluru", "Chennai", "Hyderabad", 
        "Kolkata", "Ahmedabad", "Pune", "Jaipur", "Surat", 
        "Lucknow", "Kanpur", "Nagpur", "Indore", "Patna", 
        "Vadodara", "Ghaziabad", "Ludhiana", "Agra", "Chandigarh"
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weather Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel cityLabel = new JLabel("Select City: ");
        JComboBox<String> cityComboBox = new JComboBox<>(CITIES);
        JButton fetchButton = new JButton("Get Weather");

        JLabel temperatureLabel = new JLabel("Temperature: ");
        JLabel temperatureValue = new JLabel("");
        JLabel humidityLabel = new JLabel("Humidity: ");
        JLabel humidityValue = new JLabel("");
        JLabel conditionLabel = new JLabel("Condition: ");
        JLabel conditionValue = new JLabel("");

        panel.add(cityLabel);
        panel.add(cityComboBox);
        panel.add(fetchButton);
        panel.add(new JLabel());
        panel.add(temperatureLabel);
        panel.add(temperatureValue);
        panel.add(humidityLabel);
        panel.add(humidityValue);
        panel.add(conditionLabel);
        panel.add(conditionValue);

        frame.add(panel, BorderLayout.CENTER);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) cityComboBox.getSelectedItem();
                if (selectedCity != null && !selectedCity.isEmpty()) {
                    fetchWeather(selectedCity, temperatureValue, humidityValue, conditionValue);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a valid city.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void fetchWeather(String city, JLabel temperatureLabel, JLabel humidityLabel, JLabel conditionLabel) {
        try {
            String urlString = String.format(API_URL, city, API_KEY);
            System.out.println("Request URL: " + urlString);  // Debugging the request URL
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + statusCode);  // Debugging HTTP response

            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader inStream = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(inStream);

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                inStream.close();

                System.out.println("API Response: " + response.toString());  // Debugging the response

                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.getInt("cod") == 200) {
                    JSONObject mainData = jsonResponse.getJSONObject("main");
                    JSONObject weatherData = jsonResponse.getJSONArray("weather").getJSONObject(0);

                    double temp = mainData.getDouble("temp");
                    int humidity = mainData.getInt("humidity");
                    String condition = weatherData.getString("description");

                    System.out.println("Temperature: " + temp);  // Debugging temperature value
                    System.out.println("Humidity: " + humidity);  // Debugging humidity value
                    System.out.println("Condition: " + condition);  // Debugging condition value

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            temperatureLabel.setText(String.format("%.2f °C", temp));
                            humidityLabel.setText(humidity + "%");
                            conditionLabel.setText(condition.substring(0, 1).toUpperCase() + condition.substring(1));
                        }
                    });

                } else {
                    String errorMessage = jsonResponse.optString("message", "Error fetching weather data.");
                    JOptionPane.showMessageDialog(null, errorMessage);
                }
            } else {
                JOptionPane.showMessageDialog(null, "HTTP Error: " + statusCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Network error: " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error parsing weather data.");
        }
    }
}
