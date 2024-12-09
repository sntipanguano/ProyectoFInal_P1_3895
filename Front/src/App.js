import React, { useState } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import AppNavbar from './components/Shared/Navbar';
import AppRoutes from './routes/AppRoutes';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token')); // Inicializa con el token

  const handleLogin = () => {
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    setIsAuthenticated(false);
    localStorage.removeItem('token');
  };

  return (
    <Router>
      <AppNavbar onLogin={handleLogin} onLogout={handleLogout} isAuthenticated={isAuthenticated} />
      <AppRoutes isAuthenticated={isAuthenticated} />
    </Router>
  );
};

export default App;
