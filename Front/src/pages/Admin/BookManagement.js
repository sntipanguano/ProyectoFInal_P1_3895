import React, { useState, useEffect } from 'react';
import BookForm from '../../components/Books/BookForm';
import ReactPaginate from 'react-paginate';
import { Container, Row, Col, Table, Button, Form, Modal } from 'react-bootstrap';

const BookManagement = () => {
  const [books, setBooks] = useState([
    {
      id: 1,
      titulo: 'Cien Años de Soledad',
      autor: 'Gabriel García Márquez',
      categoria: 'Ficción',
      isbn: '9780060883287',
      cantidad: 5,
      disponibles: 3,
      fecharegistro: '2023-12-01',
    },
    {
      id: 2,
      titulo: 'Breve historia del tiempo',
      autor: 'Stephen Hawking',
      categoria: 'Ciencia',
      isbn: '9780553380163',
      cantidad: 3,
      disponibles: 0,
      fecharegistro: '2023-11-25',
    },
    {
      id: 3,
      titulo: 'El Principito',
      autor: 'Antoine de Saint-Exupéry',
      categoria: 'Ficción',
      isbn: '9780156012195',
      cantidad: 10,
      disponibles: 8,
      fecharegistro: '2023-10-10',
    },
  ]);

  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('');
  const [dateFilter, setDateFilter] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const booksPerPage = 5;
  const [editingBook, setEditingBook] = useState(null);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    // Simulación de datos quemados para pruebas
    setBooks([
      {
        id: 1,
        titulo: 'Cien Años de Soledad',
        autor: 'Gabriel García Márquez',
        categoria: 'Ficción',
        isbn: '9780060883287',
        cantidad: 5,
        disponibles: 3,
        fecharegistro: '2023-12-01',
      },
      {
        id: 2,
        titulo: 'Breve historia del tiempo',
        autor: 'Stephen Hawking',
        categoria: 'Ciencia',
        isbn: '9780553380163',
        cantidad: 3,
        disponibles: 0,
        fecharegistro: '2023-11-25',
      },
      {
        id: 3,
        titulo: 'El Principito',
        autor: 'Antoine de Saint-Exupéry',
        categoria: 'Ficción',
        isbn: '9780156012195',
        cantidad: 10,
        disponibles: 8,
        fecharegistro: '2023-10-10',
      },
    ]);
  };

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const handleAddBook = (newBook) => {
    setBooks((prevBooks) => [
      ...prevBooks,
      { ...newBook, id: prevBooks.length + 1, fecharegistro: new Date().toISOString().split('T')[0] },
    ]);
    setShowModal(false);
  };

  const handleEditBook = (updatedBook) => {
    setBooks((prevBooks) =>
      prevBooks.map((book) => (book.id === editingBook.id ? updatedBook : book))
    );
    setEditingBook(null);
    setShowModal(false);
  };

  const handleDeleteBook = (bookId) => {
    if (window.confirm('¿Estás seguro de eliminar este libro?')) {
      setBooks((prevBooks) => prevBooks.filter((book) => book.id !== bookId));
    }
  };

  const handleOpenModal = (book = null) => {
    setEditingBook(book);
    setShowModal(true);
  };

  const filteredBooks = books.filter((book) => {
    const search = searchTerm.toLowerCase();

    // Convertir ID a string para asegurar comparación con searchTerm
    const idMatch = book.id.toString().includes(search);

    // Comparar título e ISBN también
    const titleMatch = book.titulo.toLowerCase().includes(search);
    const isbnMatch = book.isbn.includes(search);

    const matchCategory = selectedCategory ? book.categoria === selectedCategory : true;
    const matchDate = dateFilter ? book.fecharegistro.startsWith(dateFilter) : true;

    return (idMatch || titleMatch || isbnMatch) && matchCategory && matchDate;
  });

  const currentBooks = filteredBooks.slice(
    currentPage * booksPerPage,
    (currentPage + 1) * booksPerPage
  );

  const handleCloseModal = () => {
    setShowModal(false);
    setEditingBook(null);
  };

  return (
    <Container className="mt-5">
      <h1 className="text-center mb-4">Gestión de Libros</h1>

      {/* Filtros */}
      <Row className="mb-4">
        <Col md={3}>
          <Form.Control
            type="text"
            placeholder="Buscar por ID, título o ISBN"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </Col>
        <Col md={3}>
          <Form.Select onChange={(e) => setSelectedCategory(e.target.value)}>
            <option value="">Todas las categorías</option>
            <option value="Ficción">Ficción</option>
            <option value="Ciencia">Ciencia</option>
            <option value="Historia">Historia</option>
          </Form.Select>
        </Col>
        <Col md={3}>
          <Form.Control
            type="date"
            placeholder="Filtrar por fecha de registro"
            value={dateFilter}
            onChange={(e) => setDateFilter(e.target.value)}
          />
        </Col>
      </Row>

      {/* Botón para abrir el modal */}
      <Button variant="primary" onClick={() => handleOpenModal()} className="mb-3">
        Registrar Nuevo Libro
      </Button>

      {/* Tabla de libros */}
      <div className="table-responsive">
        <Table striped bordered hover className="mt-3">
          <thead>
            <tr>
              <th>ID</th>
              <th>Título</th>
              <th>Autor</th>
              <th>Categoría</th>
              <th>ISBN</th>
              <th>Cantidad</th>
              <th>Disponibles</th>
              <th>Fecha de Registro</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {currentBooks.map((book) => (
              <tr key={book.id}>
                <td>{book.id}</td>
                <td>{book.titulo}</td>
                <td>{book.autor}</td>
                <td>{book.categoria}</td>
                <td>{book.isbn}</td>
                <td>{book.cantidad}</td>
                <td>{book.disponibles}</td>
                <td>{book.fecharegistro}</td>
                <td>
                  <Button
                    variant="warning"
                    size="sm"
                    className="me-2"
                    onClick={() => handleOpenModal(book)}
                  >
                    Editar
                  </Button>
                  <Button variant="danger" size="sm" onClick={() => handleDeleteBook(book.id)}>
                    Eliminar
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>

      {/* Paginación */}
      <ReactPaginate
        previousLabel={'Anterior'}
        nextLabel={'Siguiente'}
        breakLabel={'...'}
        pageCount={Math.ceil(filteredBooks.length / booksPerPage)}
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

      {/* Modal para agregar/editar libros */}
      <Modal show={showModal} onHide={handleCloseModal} centered>
        <Modal.Header closeButton>
          <Modal.Title>{editingBook ? 'Editar Libro' : 'Registrar Nuevo Libro'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <BookForm
            initialData={editingBook || {}}
            onSubmit={editingBook ? handleEditBook : handleAddBook}
          />
        </Modal.Body>
      </Modal>
    </Container>
  );
};

export default BookManagement;





