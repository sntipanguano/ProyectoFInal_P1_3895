import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

const UserForm = ({ initialData = {}, onSubmit }) => {
  const [name, setName] = useState(initialData.name || '');
  const [email, setEmail] =useState(initialData.email || '');
  const [type, setType] = useState(initialData.type || 'Estudiante');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ name, email, type }); // Enviar datos al componente padre
    setName(''); // Limpiar campos
    setEmail('');
    setType('Estudiante');
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="formName" className="mb-3">
        <Form.Label>Nombre</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingresa el nombre"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </Form.Group>
      <Form.Group controlId="formEmail" className="mb-3">
        <Form.Label>Correo</Form.Label>
        <Form.Control
          type="email"
          placeholder="Ingresa el correo electrónico"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </Form.Group>
      <Form.Group controlId="formType" className="mb-3">
        <Form.Label>Tipo de Usuario</Form.Label>
        <Form.Select
          value={type}
          onChange={(e) => setType(e.target.value)}
          required
        >
          <option value="Estudiante">Estudiante</option>
          <option value="Profesor">Profesor</option>
          <option value="Administrador">Administrador</option>
        </Form.Select>
      </Form.Group>
      <Button variant="primary" type="submit">
        Guardar
      </Button>
    </Form>
  );
};

export default UserForm;


