import React, { useState } from 'react';
import { Form, Button, Row, Col } from 'react-bootstrap';

const LoanForm = ({ onSubmit, initialData = {} }) => {
  const [bookId, setBookId] = useState(initialData.bookId || '');
  const [userId, setUserId] = useState(initialData.userId || '');
  const [loanDate, setLoanDate] = useState(initialData.loanDate || '');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ bookId, userId, loanDate });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3" controlId="formBookId">
        <Form.Label>ID Libro</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese el ID del libro"
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formUserId">
        <Form.Label>ID Usuario</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese el ID del usuario"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formLoanDate">
        <Form.Label>Fecha de Préstamo</Form.Label>
        <Form.Control
          type="date"
          value={loanDate}
          onChange={(e) => setLoanDate(e.target.value)}
          required
        />
      </Form.Group>

      <Row>
        <Col>
          <Button variant="primary" type="submit" className="w-100">
            Guardar
          </Button>
        </Col>
        <Col>
          <Button variant="secondary" type="button" className="w-100" onClick={() => setBookId('') || setUserId('') || setLoanDate('')}>
            Limpiar
          </Button>
        </Col>
      </Row>
    </Form>
  );
};

export default LoanForm;

