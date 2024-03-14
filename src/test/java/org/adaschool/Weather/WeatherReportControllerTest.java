package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherReportControllerTest {

  @Mock
  private WeatherReportService weatherReportService;

 @InjectMocks
 private WeatherReportController weatherReportController;

 @BeforeEach
 public void setUp(){
  MockitoAnnotations.openMocks(this);
 }

 @Test
 public void get_weather_report_test() {
     //Arrange
     double latitud = 3.333;
     double longitud = 1.111;
     WeatherReport report = new WeatherReport();
     report.setHumidity(25);
     report.setTemperature(27);
     Mockito.when(weatherReportService.getWeatherReport(latitud, longitud)).thenReturn(report);
     //Act
     WeatherReport weatherReport = weatherReportController.getWeatherReport(latitud, longitud);
     //Assert
     assertEquals(report, weatherReport);

 }


}
