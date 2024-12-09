import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Home from '../pages/Home';
import BookManagement from '../pages/Admin/BookManagement';
import UserManagement from '../pages/Admin/UserManagement';
import LoanManagement from '../pages/Admin/LoanManagement';
import Dashboard from '../pages/Admin/Dashboard';
import Reports from '../pages/Admin/Reports';

const AppRoutes = ({ isAuthenticated }) => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      {isAuthenticated ? (
        <>
          <Route path="/books" element={<BookManagement />} />
          <Route path="/users" element={<UserManagement />} />
          <Route path="/loans" element={<LoanManagement />} />
          <Route path="/reports" element={<Reports />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </>
      ) : (
        <Route path="*" element={<Navigate to="/" />} />
      )}
    </Routes>
  );
};

export default AppRoutes;
