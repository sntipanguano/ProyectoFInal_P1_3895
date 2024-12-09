import React from 'react';

const Home = () => {
  const token = localStorage.getItem('token');

  if (!token) {
    return (
      <div className="container mt-5 text-center">
        <h1>Bienvenido a la Biblioteca</h1>
        <p>Inicie sesión para acceder a las funciones de gestión.</p>
      </div>
    );
  }

  return (
    <div className="container mt-5 text-center">
      <h1>Bienvenido a la Biblioteca</h1>
      <p>Utilice el menú de navegación para acceder a las opciones de gestión.</p>
    </div>
  );
};

export default Home;

