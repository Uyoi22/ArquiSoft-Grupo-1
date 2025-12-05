import React, { useState } from 'react';
import axios from 'axios';

const WeatherForm = () => {
  const [pais, setPais] = useState('');
  const [ciudad, setCiudad] = useState('');
  const [resultado, setResultado] = useState(null);

  const handleSearch = async () => {
    if (!pais || !ciudad) {
      alert('Por favor ingresa país y ciudad');
      return;
    }
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/clima?pais=${pais}&ciudad=${ciudad}`);
      setResultado(response.data);
    } catch (error) {
      console.error(error);
      alert('Error al consultar el clima');
    }
  };

  return (
    <div className="card">
      <h2>Buscar clima</h2>
      <form>
        <input
          type="text"
          placeholder="País"
          value={pais}
          onChange={(e) => setPais(e.target.value)}
          className="input-large"
        />
        <input
          type="text"
          placeholder="Ciudad"
          value={ciudad}
          onChange={(e) => setCiudad(e.target.value)}
          className="input-large"
        />
        <button type="button" onClick={handleSearch}>Buscar</button>
      </form>

      {resultado && (
        <div className="card" style={{ marginTop: '20px' }}>
          <h3>Resultado</h3>
          <pre>{JSON.stringify(resultado, null, 2)}</pre>
        </div>
      )}
    </div>
  );
};

export default WeatherForm;
