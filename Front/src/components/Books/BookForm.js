import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

const BookForm = ({ onSubmit, initialData = {} }) => {
  const [title, setTitle] = useState(initialData.title || '');
  const [author, setAuthor] = useState(initialData.author || '');
  const [isbn, setIsbn] = useState(initialData.isbn || '');
  const [category, setCategory] = useState(initialData.category || '');
  const [quantity, setQuantity] = useState(initialData.quantity || '');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ title, author, isbn, category, quantity });

    // Limpiar los campos del formulario
    setTitle('');
    setAuthor('');
    setIsbn('');
    setCategory('');
    setQuantity('');
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="formTitle" className="mb-3">
        <Form.Label>Título</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese el título del libro"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formAuthor" className="mb-3">
        <Form.Label>Autor</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese el autor del libro"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formISBN" className="mb-3">
        <Form.Label>ISBN</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese el ISBN del libro"
          value={isbn}
          onChange={(e) => setIsbn(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formCategory" className="mb-3">
        <Form.Label>Categoría</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese la categoría del libro"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formQuantity" className="mb-3">
        <Form.Label>Cantidad en Inventario</Form.Label>
        <Form.Control
          type="number"
          placeholder="Ingrese la cantidad en inventario"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          required
        />
      </Form.Group>

      <Button variant="primary" type="submit">
        Guardar
      </Button>
    </Form>
  );
};

export default BookForm;

