# WeatherContextContributor

Don't forget to set the property
```
com.liferay.demo.openweathermap.apikey=xyz
```

How to use?
- drop the jar in the deploy folder
- create a fragment
- in the html you can call the WeatherContextContributor with `${weather.getWeatherInfo()}`
- make sure your users have a primary address configured in their profile