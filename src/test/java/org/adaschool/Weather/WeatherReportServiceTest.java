package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Equals;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import static org.adaschool.Weather.service.WeatherReportService.API_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherReportServiceTest {
    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void  get_weather_report_test(){
        //Arrange
        double latitud = 3.333;
        double longitud = 1.111;
        final String API_KEY = "eacf9ae20b291a81bf8d9c3bce0f823f";
        final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

        String url = API_URL + "?lat=" + latitud + "&lon=" + longitud + "&appid=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
        WeatherReport report = new WeatherReport();
        WeatherApiResponse.Main main = response.getMain();
        report.setTemperature(main.getTemperature());
        report.setHumidity(main.getHumidity());
        Mockito.when(weatherReportService.getWeatherReport(latitud, longitud)).thenReturn(report);
        //Act
        WeatherReport weatherReport = weatherReportService.getWeatherReport(latitud, longitud);
        //Assert
        assertEquals(report, weatherReport);
    }

}
