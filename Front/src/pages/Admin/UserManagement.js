import React, { useState, useEffect } from 'react';
import ReactPaginate from 'react-paginate';
import { Container, Row, Col, Table, Button, Modal, Form } from 'react-bootstrap';
import UserForm from '../../components/Users/UserForm';

const UserManagement = () => {
  // Datos quemados para pruebas
  const [users, setUsers] = useState([
    {
      id: 1,
      nombre: 'Juan Pérez',
      correo: 'juan.perez@example.com',
      tipousuario: 'estudiante',
      estadousuario: 'activo',
      fecharegistro: '2023-12-01T10:30:00',
    },
    {
      id: 2,
      nombre: 'María Gómez',
      correo: 'maria.gomez@example.com',
      tipousuario: 'profesor',
      estadousuario: 'activo',
      fecharegistro: '2023-12-02T14:20:00',
    },
    {
      id: 3,
      nombre: 'Carlos Ramírez',
      correo: 'carlos.ramirez@example.com',
      tipousuario: 'estudiante',
      estadousuario: 'suspendido',
      fecharegistro: '2023-12-03T08:15:00',
    },
  ]);

  const [searchTerm, setSearchTerm] = useState('');
  const [filterDate, setFilterDate] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [editingUser, setEditingUser] = useState(null);
  const [suspendingUser, setSuspendingUser] = useState(null);
  const [suspendReason, setSuspendReason] = useState('');
  const [showModal, setShowModal] = useState(false);
  const usersPerPage = 10;

  // Función para buscar usuarios
  const filteredUsers = users.filter((user) => {
    const searchMatch =
      user.id.toString().includes(searchTerm) ||
      user.nombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.correo.toLowerCase().includes(searchTerm.toLowerCase());

    const dateMatch = filterDate
      ? user.fecharegistro.startsWith(filterDate)
      : true;

    return searchMatch && dateMatch;
  });

  // Datos de la página actual
  const currentUsers = filteredUsers.slice(
    currentPage * usersPerPage,
    (currentPage + 1) * usersPerPage
  );

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const handleAddUser = (newUser) => {
    setUsers((prevUsers) => [...prevUsers, { ...newUser, id: Date.now() }]);
    setShowModal(false);
  };

  const handleEditUser = (updatedUser) => {
    setUsers((prevUsers) =>
      prevUsers.map((user) =>
        user.id === editingUser.id ? { ...editingUser, ...updatedUser } : user
      )
    );
    setEditingUser(null);
    setShowModal(false);
  };

  const handleSuspendUser = () => {
    setUsers((prevUsers) =>
      prevUsers.map((user) =>
        user.id === suspendingUser.id
          ? { ...user, estadousuario: 'suspendido', razon: suspendReason }
          : user
      )
    );
    setSuspendingUser(null);
    setSuspendReason('');
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setEditingUser(null);
  };

  const handleOpenModal = (user = null) => {
    setEditingUser(user);
    setShowModal(true);
  };

  return (
    <Container className="mt-5">
      <h1 className="text-center mb-4">Gestión de Usuarios</h1>

      {/* Barra de búsqueda */}
      <Row className="mb-4">
        <Col md={6}>
          <Form.Control
            type="text"
            placeholder="Buscar por ID, Nombre o Correo"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </Col>
        <Col md={4}>
          <Form.Control
            type="date"
            value={filterDate}
            onChange={(e) => setFilterDate(e.target.value)}
          />
        </Col>
        <Col md={2}>
          <Button variant="primary" onClick={() => handleOpenModal()}>
            Registrar Usuario
          </Button>
        </Col>
      </Row>

      {/* Tabla de usuarios */}
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Tipo de Usuario</th>
            <th>Estado</th>
            <th>Fecha de Registro</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {currentUsers.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.nombre}</td>
              <td>{user.correo}</td>
              <td>{user.tipousuario}</td>
              <td>{user.estadousuario}</td>
              <td>{new Date(user.fecharegistro).toLocaleDateString()}</td>
              <td>
                <Button
                  variant="warning"
                  className="me-2"
                  onClick={() => handleOpenModal(user)}
                >
                  Editar
                </Button>
                <Button
                  variant="danger"
                  onClick={() => setSuspendingUser(user)}
                >
                  Suspender
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* Paginación */}
      <ReactPaginate
        previousLabel={'Anterior'}
        nextLabel={'Siguiente'}
        breakLabel={'...'}
        pageCount={Math.ceil(filteredUsers.length / usersPerPage)}
        onPageChange={handlePageClick}
        containerClassName={'pagination justify-content-center mt-3'}
        activeClassName={'active'}
        pageClassName={'page-item'}
        pageLinkClassName={'page-link'}
        previousClassName={'page-item'}
        previousLinkClassName={'page-link'}
        nextClassName={'page-item'}
        nextLinkClassName={'page-link'}
        breakClassName={'page-item'}
        breakLinkClassName={'page-link'}
      />

      {/* Modal para agregar/editar usuarios */}
      <Modal show={showModal} onHide={handleCloseModal} centered>
        <Modal.Header closeButton>
          <Modal.Title>{editingUser ? 'Editar Usuario' : 'Registrar Usuario'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <UserForm
            initialData={editingUser || {}}
            onSubmit={editingUser ? handleEditUser : handleAddUser}
          />
        </Modal.Body>
      </Modal>

      {/* Modal para suspender usuarios */}
      <Modal
        show={!!suspendingUser}
        onHide={() => setSuspendingUser(null)}
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>Suspender Usuario</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Razón para la suspensión</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                value={suspendReason}
                onChange={(e) => setSuspendReason(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={handleSuspendUser}>
            Confirmar
          </Button>
          <Button variant="secondary" onClick={() => setSuspendingUser(null)}>
            Cancelar
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default UserManagement;

