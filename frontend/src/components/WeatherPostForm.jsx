import React, { useState } from 'react';
import axios from 'axios';

const WeatherPostForm = () => {
  const [form, setForm] = useState({
    pais: '',
    ciudad: '',
    temperatura: '',
    sensacionTermica: '',
    humedad: '',
    descripcion: '',
    velocidadViento: ''
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/v1/clima', {
        pais: form.pais,
        ciudad: form.ciudad,
        temperatura: form.temperatura ? parseFloat(form.temperatura) : 0,
        sensacionTermica: form.sensacionTermica ? parseFloat(form.sensacionTermica) : 0,
        humedad: form.humedad ? parseInt(form.humedad) : 0,
        descripcion: form.descripcion || '',
        velocidadViento: form.velocidadViento ? parseFloat(form.velocidadViento) : 0
      });
      alert('Clima registrado correctamente');
      console.log(response.data);
      setForm({
        pais: '',
        ciudad: '',
        temperatura: '',
        sensacionTermica: '',
        humedad: '',
        descripcion: '',
        velocidadViento: ''
      });
    } catch (err) {
      console.error(err);
      alert('Error al registrar el clima');
    }
  };

  return (
    <div className="card">
      <h2>Agregar clima</h2>
      <form>
        {Object.keys(form).map((key) => (
          <input
            key={key}
            name={key}
            type={['temperatura','sensacionTermica','velocidadViento'].includes(key) ? 'number' : 'text'}
            placeholder={key.charAt(0).toUpperCase() + key.slice(1)}
            value={form[key]}
            onChange={handleChange}
            className="input-large"
          />
        ))}
        <button type="button" onClick={handleSubmit}>Guardar</button>
      </form>
    </div>
  );
};

export default WeatherPostForm;
