import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';
import { Container, Row, Col, Card } from 'react-bootstrap';

// Registrar componentes de Chart.js
ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const Dashboard = () => {
  const [totalBooks, setTotalBooks] = useState(0);
  const [totalUsers, setTotalUsers] = useState(0);
  const [activeLoans, setActiveLoans] = useState(0);
  const [availableBooks, setAvailableBooks] = useState(0);
  const [mostBorrowedBooks, setMostBorrowedBooks] = useState([]);
  const [mostActiveUsers, setMostActiveUsers] = useState([]);

  useEffect(() => {
    fetchSummaryData();
    fetchMostBorrowedBooks();
    fetchMostActiveUsers();
  }, []);

  const fetchSummaryData = async () => {
    try {
      const booksResponse = await axios.get('/api/books');
      const usersResponse = await axios.get('/api/users');
      const loansResponse = await axios.get('/api/loans/active');

      setTotalBooks(booksResponse.data.length);
      setTotalUsers(usersResponse.data.length);
      setActiveLoans(loansResponse.data.length);
      setAvailableBooks(booksResponse.data.filter((book) => book.available).length);
    } catch (error) {
      console.error('Error al obtener datos del resumen:', error);
    }
  };

  const fetchMostBorrowedBooks = async () => {
    try {
      const response = await axios.get('/api/stats/most-borrowed-books');
      setMostBorrowedBooks(response.data);
    } catch (error) {
      console.error('Error al obtener libros más prestados:', error);
    }
  };

  const fetchMostActiveUsers = async () => {
    try {
      const response = await axios.get('/api/stats/most-active-users');
      setMostActiveUsers(response.data);
    } catch (error) {
      console.error('Error al obtener usuarios más activos:', error);
    }
  };

  const booksData = {
    labels: mostBorrowedBooks.map((book) => book.title),
    datasets: [
      {
        label: 'Cantidad de Préstamos',
        data: mostBorrowedBooks.map((book) => book.count),
        backgroundColor: 'rgba(75, 192, 192, 0.6)',
      },
    ],
  };

  const usersData = {
    labels: mostActiveUsers.map((user) => user.name),
    datasets: [
      {
        label: 'Cantidad de Préstamos',
        data: mostActiveUsers.map((user) => user.count),
        backgroundColor: 'rgba(153, 102, 255, 0.6)',
      },
    ],
  };

  return (
    <Container className="mt-5">
      <h1 className="text-center mb-4">Dashboard</h1>

      {/* Resumen General */}
      <Row className="mb-4">
        <Col md={3}>
          <Card bg="primary" text="white" className="mb-3 text-center">
            <Card.Body>
              <Card.Title>Total de Libros</Card.Title>
              <Card.Text>{totalBooks}</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col md={3}>
          <Card bg="success" text="white" className="mb-3 text-center">
            <Card.Body>
              <Card.Title>Total de Usuarios</Card.Title>
              <Card.Text>{totalUsers}</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col md={3}>
          <Card bg="warning" text="white" className="mb-3 text-center">
            <Card.Body>
              <Card.Title>Préstamos Activos</Card.Title>
              <Card.Text>{activeLoans}</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col md={3}>
          <Card bg="info" text="white" className="mb-3 text-center">
            <Card.Body>
              <Card.Title>Libros Disponibles</Card.Title>
              <Card.Text>{availableBooks}</Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      {/* Gráficos */}
      <Row className="mb-4">
        <Col md={12}>
          <h2>Libros Más Prestados</h2>
          <Bar data={booksData} />
        </Col>
      </Row>
      <Row>
        <Col md={12}>
          <h2>Usuarios Más Activos</h2>
          <Bar data={usersData} />
        </Col>
      </Row>
    </Container>
  );
};

export default Dashboard;

