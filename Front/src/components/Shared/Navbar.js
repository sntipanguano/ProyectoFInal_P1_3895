import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import '../../styles/global.css';

const AppNavbar = ({ onLogin, onLogout, isAuthenticated }) => {
  const [showLoginModal, setShowLoginModal] = useState(false);

  const handleLoginSubmit = (e) => {
    e.preventDefault();
    localStorage.setItem('token', 'fake-token'); // Simular autenticación
    setShowLoginModal(false); // Cerrar modal
    onLogin(); // Llama a la función de login del componente padre
  };

  return (
    <>
      <nav className="navbar custom-navbar">
        <div className="container d-flex justify-content-between align-items-center">
          <a className="navbar-brand" href="/">Biblioteca</a>
          <ul className="d-flex list-unstyled mb-0">
            {isAuthenticated && (
              <>
                <li><a href="/books" className="nav-link">Gestión de Libros</a></li>
                <li><a href="/users" className="nav-link">Gestión de Usuarios</a></li>
                <li><a href="/loans" className="nav-link">Gestión de Préstamos</a></li>
                <li><a href="/reports" className="nav-link">Reportes</a></li>
                <li><a href="/dashboard" className="nav-link">Dashboard</a></li>
              </>
            )}
          </ul>
          {isAuthenticated ? (
            <button className="btn btn-outline-danger ms-3" onClick={onLogout}>
              Cerrar Sesión
            </button>
          ) : (
            <button
              className="btn btn-outline-primary ms-3"
              onClick={() => setShowLoginModal(true)}
            >
              Iniciar Sesión
            </button>
          )}
        </div>
      </nav>

      {/* Modal de Login */}
      <Modal
        show={showLoginModal}
        onHide={() => setShowLoginModal(false)}
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>Iniciar Sesión</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleLoginSubmit}>
            <Form.Group controlId="formBasicEmail" className="mb-3">
              <Form.Label>Correo Electrónico</Form.Label>
              <Form.Control type="email" placeholder="Ingrese su correo" required />
            </Form.Group>
            <Form.Group controlId="formBasicPassword" className="mb-3">
              <Form.Label>Contraseña</Form.Label>
              <Form.Control type="password" placeholder="Ingrese su contraseña" required />
            </Form.Group>
            <Button variant="primary" type="submit" className="w-100">
              Iniciar Sesión
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default AppNavbar;





