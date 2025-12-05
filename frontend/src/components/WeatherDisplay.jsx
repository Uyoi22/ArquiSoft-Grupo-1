import React from 'react';

const WeatherDisplay = ({ data }) => {
  return (
    <div>
      <h3>Datos del clima</h3>
      <pre>{JSON.stringify(data, null, 2)}</pre>

      {data._links && (
        <div>
          <h4>Acciones:</h4>
          {Object.entries(data._links).map(([key, link]) => (
            <div key={key}>
              <a href={link.href}>{key}</a>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default WeatherDisplay;
