import React, { useState, useEffect } from 'react';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import * as XLSX from 'xlsx';
import { Bar, Pie } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ArcElement } from 'chart.js';
import { Table, Button, Form } from 'react-bootstrap';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ArcElement);

const Reports = () => {
  const [inventory, setInventory] = useState([]);
  const [activeLoans, setActiveLoans] = useState([]);
  const [userHistory, setUserHistory] = useState([]);
  const [selectedUserId, setSelectedUserId] = useState('');
  const [topBooks, setTopBooks] = useState([]);
  const [activeUsers, setActiveUsers] = useState([]);

  // Datos quemados para pruebas
  useEffect(() => {
    setInventory([
      { id: 1, title: 'Cien Años de Soledad', author: 'Gabriel García Márquez', category: 'Ficción', available: 5 },
      { id: 2, title: 'Breve historia del tiempo', author: 'Stephen Hawking', category: 'Ciencia', available: 3 },
    ]);

    setActiveLoans([
      { id: 1, user: { name: 'Juan Pérez' }, book: { title: 'Cien Años de Soledad' }, loanDate: '2024-12-01', dueDate: '2024-12-10' },
      { id: 2, user: { name: 'María López' }, book: { title: 'Breve historia del tiempo' }, loanDate: '2024-12-02', dueDate: '2024-12-12' },
    ]);

    setUserHistory([
      { id: 1, user: { name: 'Juan Pérez' }, book: { title: 'Cien Años de Soledad' }, loanDate: '2024-11-20', returnDate: '2024-11-30', fine: 0 },
      { id: 2, user: { name: 'María López' }, book: { title: 'Breve historia del tiempo' }, loanDate: '2024-11-25', returnDate: '2024-12-01', fine: 5 },
    ]);

    setTopBooks([
      { title: 'Cien Años de Soledad', loans: 15 },
      { title: 'Breve historia del tiempo', loans: 10 },
    ]);

    setActiveUsers([
      { name: 'Juan Pérez', loans: 8 },
      { name: 'María López', loans: 5 },
    ]);
  }, []);

  const generatePDF = (title, data) => {
    const doc = new jsPDF();
    doc.text(title, 10, 10);
    doc.autoTable({
      head: [Object.keys(data[0])],
      body: data.map((row) => Object.values(row)),
    });
    doc.save(`${title}.pdf`);
  };

  const exportToExcel = (title, data) => {
    const ws = XLSX.utils.json_to_sheet(data);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, title);
    XLSX.writeFile(wb, `${title}.xlsx`);
  };

  const topBooksData = {
    labels: topBooks.map((book) => book.title),
    datasets: [
      {
        label: 'Cantidad de préstamos',
        data: topBooks.map((book) => book.loans),
        backgroundColor: 'rgba(75, 192, 192, 0.6)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1,
      },
    ],
  };

  const activeUsersData = {
    labels: activeUsers.map((user) => user.name),
    datasets: [
      {
        label: 'Préstamos realizados',
        data: activeUsers.map((user) => user.loans),
        backgroundColor: ['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)'],
        borderColor: ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'],
        borderWidth: 1,
      },
    ],
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Reportes y Estadísticas</h1>

      <h2>Inventario Actual</h2>
      <Table striped bordered hover className="mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Autor</th>
            <th>Categoría</th>
            <th>Disponibles</th>
          </tr>
        </thead>
        <tbody>
          {inventory.map((book) => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.title}</td>
              <td>{book.author}</td>
              <td>{book.category}</td>
              <td>{book.available}</td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Button onClick={() => generatePDF('Inventario Actual', inventory)} className="btn btn-primary me-2">
        Exportar a PDF
      </Button>
      <Button onClick={() => exportToExcel('Inventario Actual', inventory)} className="btn btn-success">
        Exportar a Excel
      </Button>

      <h2>Préstamos Activos</h2>
      <Table striped bordered hover className="mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Libro</th>
            <th>Fecha de Préstamo</th>
            <th>Fecha de Devolución</th>
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
            </tr>
          ))}
        </tbody>
      </Table>

      <h2>Estadísticas Visuales</h2>
      <div className="row">
        <div className="col-md-6">
          <h3>Libros Más Prestados</h3>
          <Bar data={topBooksData} />
        </div>
        <div className="col-md-6">
          <h3>Usuarios Más Activos</h3>
          <Pie data={activeUsersData} />
        </div>
      </div>
    </div>
  );
};

export default Reports;
