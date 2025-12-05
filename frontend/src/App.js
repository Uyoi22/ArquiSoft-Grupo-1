import React, { useState } from 'react';
import './App.css';
import WeatherForm from './components/WeatherForm';
import WeatherPostForm from './components/WeatherPostForm';
import WeatherDisplay from './components/WeatherDisplay';

function App() {
  const [weatherData, setWeatherData] = useState(null);

  return (
    <div className="App">
      <h1>Clima de Ciudades</h1>
      <WeatherForm setWeatherData={setWeatherData} />
      <WeatherPostForm />
      {weatherData && <WeatherDisplay data={weatherData} />}
    </div>
  );
}

export default App;
