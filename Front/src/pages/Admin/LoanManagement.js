import React, { useState, useEffect } from 'react';
import LoanForm from '../../components/Loans/LoanForm';
import { Container, Row, Col, Table, Button, Form, Modal } from 'react-bootstrap';

const LoanManagement = () => {
  const [activeLoans, setActiveLoans] = useState([
    {
      id: 1,
      user: { id: 1, name: 'Juan Pérez' },
      book: { id: 1, title: 'Cien Años de Soledad' },
      loanDate: '2023-11-01',
      dueDate: '2023-11-10',
      status: 'activo',
    },
    {
      id: 2,
      user: { id: 2, name: 'María García' },
      book: { id: 2, title: 'Breve historia del tiempo' },
      loanDate: '2023-11-05',
      dueDate: '2023-11-15',
      status: 'activo',
    },
  ]);

  const [loanHistory, setLoanHistory] = useState([
    {
      id: 1,
      user: { id: 3, name: 'Carlos López' },
      book: { id: 3, title: 'El Principito' },
      loanDate: '2023-10-01',
      returnDate: '2023-10-11',
      fine: 10.0,
      status: 'devuelto',
    },
    {
      id: 2,
      user: { id: 4, name: 'Ana Martínez' },
      book: { id: 4, title: 'La Odisea' },
      loanDate: '2023-09-20',
      returnDate: '2023-09-25',
      fine: 0.0,
      status: 'devuelto',
    },
  ]);

  const [filterUserName, setFilterUserName] = useState('');
  const [filterLoanId, setFilterLoanId] = useState('');
  const [filteredHistory, setFilteredHistory] = useState(loanHistory); // Estado para el historial filtrado
  const [editingLoan, setEditingLoan] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [showReturnModal, setShowReturnModal] = useState(false);
  const [selectedLoan, setSelectedLoan] = useState(null);

  useEffect(() => {
    // Actualizar historial cuando se borren los filtros
    if (!filterUserName && !filterLoanId) {
      setFilteredHistory(loanHistory);
    }
  }, [filterUserName, filterLoanId, loanHistory]);

  const fetchActiveLoans = () => {
    // Datos quemados ya cargados en activeLoans.
  };

  const handleFilterLoanHistory = () => {
    const filtered = loanHistory.filter((loan) => {
      const matchUserName = filterUserName
        ? loan.user.name.toLowerCase().includes(filterUserName.toLowerCase())
        : true;
      const matchLoanId = filterLoanId
        ? loan.id.toString() === filterLoanId
        : true;
      return matchUserName && matchLoanId;
    });
    setFilteredHistory(filtered);
  };

  const handleAddLoan = (loanData) => {
    setActiveLoans((prevLoans) => [
      ...prevLoans,
      {
        id: prevLoans.length + 1,
        user: { id: loanData.userId, name: `Usuario ${loanData.userId}` },
        book: { id: loanData.bookId, title: `Libro ${loanData.bookId}` },
        loanDate: loanData.loanDate,
        dueDate: loanData.dueDate,
        status: 'activo',
      },
    ]);
    setShowModal(false);
  };

  const handleReturnBook = (loanId) => {
    const loan = activeLoans.find((l) => l.id === loanId);
    if (!loan) return;

    const dueDate = new Date(loan.dueDate);
    const returnDate = new Date();
    let fine = 0;

    if (returnDate > dueDate) {
      const daysLate = Math.ceil((returnDate - dueDate) / (1000 * 60 * 60 * 24));
      fine = daysLate * 5; // $5 por día de retraso
    }

    // Mover préstamo a historial
    setLoanHistory((prevHistory) => [
      ...prevHistory,
      {
        ...loan,
        returnDate: returnDate.toISOString().split('T')[0],
        fine,
        status: 'devuelto',
      },
    ]);

    // Remover de préstamos activos
    setActiveLoans((prevLoans) => prevLoans.filter((l) => l.id !== loanId));
    alert('Devolución registrada.');
  };

  const handleOpenModal = () => {
    setEditingLoan({});
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setEditingLoan(null);
    setShowModal(false);
  };

  const handleShowReturnModal = (loan) => {
    setSelectedLoan(loan);
    setShowReturnModal(true);
  };

  const handleConfirmReturn = () => {
    if (selectedLoan) {
      handleReturnBook(selectedLoan.id);
    }
    setShowReturnModal(false);
  };

  return (
    <Container className="mt-5">
      <h1 className="text-center mb-4">Gestión de Préstamos</h1>

      <Button variant="primary" onClick={handleOpenModal} className="mb-3">
        Registrar Préstamo
      </Button>

      <h2>Préstamos Activos</h2>
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>ID Préstamo</th>
            <th>Usuario</th>
            <th>Libro</th>
            <th>Fecha de Préstamo</th>
            <th>Fecha de Devolución</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {activeLoans.map((loan) => (
            <tr key={loan.id}>
              <td>{loan.id}</td>
              <td>{loan.user.name}</td>
              <td>{loan.book.title}</td>
              <td>{loan.loanDate}</td>
              <td>{loan.dueDate}</td>
              <td>
                <Button
                  variant="success"
                  onClick={() => handleShowReturnModal(loan)}
                >
                  Devolver
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <h2>Historial de Préstamos</h2>
      <Row className="mb-3">
        <Col md={6}>
          <Form.Control
            type="text"
            placeholder="Filtrar por nombre de usuario"
            value={filterUserName}
            onChange={(e) => setFilterUserName(e.target.value)}
          />
        </Col>
        <Col md={6}>
          <Form.Control
            type="text"
            placeholder="Filtrar por ID de préstamo"
            value={filterLoanId}
            onChange={(e) => setFilterLoanId(e.target.value)}
          />
        </Col>
      </Row>
      <Button variant="info" onClick={handleFilterLoanHistory} className="mb-3">
        Buscar
      </Button>

      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>ID Préstamo</th>
            <th>Usuario</th>
            <th>Libro</th>
            <th>Fecha de Préstamo</th>
            <th>Fecha de Devolución</th>
            <th>Multa</th>
          </tr>
        </thead>
        <tbody>
          {filteredHistory.map((loan) => (
            <tr key={loan.id}>
              <td>{loan.id}</td>
              <td>{loan.user.name}</td>
              <td>{loan.book.title}</td>
              <td>{loan.loanDate}</td>
              <td>{loan.returnDate || 'Sin devolver'}</td>
              <td>{loan.fine}</td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={showModal} onHide={handleCloseModal} centered>
        <Modal.Header closeButton>
          <Modal.Title>Registrar Préstamo</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <LoanForm initialData={editingLoan || {}} onSubmit={handleAddLoan} />
        </Modal.Body>
      </Modal>

      <Modal show={showReturnModal} onHide={() => setShowReturnModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmar Devolución</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>
            ¿Está seguro de registrar la devolución del préstamo con ID{' '}
            <strong>{selectedLoan?.id}</strong>?
          </p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowReturnModal(false)}>
            Cancelar
          </Button>
          <Button variant="primary" onClick={handleConfirmReturn}>
            Confirmar
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default LoanManagement;






