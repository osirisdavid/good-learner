import React from 'react';
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Layout from "./pages/Layout";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import DashboardTeacher from "./pages/DashboardTeacher";
import Commissions from './pages/Commissions';
import MyCommissions from './pages/MyCommissions';
import MyQualification from './pages/MyQualification';
import MyStudents from './pages/MyStudents';
import { RoutesWithNotFound } from './helpers';
import { Roles } from './models';
import AuthGuard from './helpers/authGuard';
import RoleGuard from './helpers/roleGuard';
import UsuarioInfo from './pages/UsuarioInfo';
import Profesores from './pages/Profesores';
import Tutores from './pages/Tutores';
import Estudiantes from './pages/Estudiantes';
import AddUser from './pages/AddUser';
import { PublicRoutes, PrivateRoutes } from './routes';

function App() {

  return (
    <BrowserRouter>
      <RoutesWithNotFound>
        <Route path={`/${PublicRoutes.LOGIN}`} element={<Login />} />
        {/* Rutas Privadas */}
        <Route element={<AuthGuard privateValidation={true} />}>
          <Route path={`/`} element={<Layout />}>
              <Route path={PrivateRoutes.COMMISSION} element={<Commissions />} />
              <Route path={PrivateRoutes.ADDUSER} element={<AddUser />} />
              <Route path={PrivateRoutes.STUDENTS} element={<Estudiantes />} />
              <Route path={PrivateRoutes.TEACHER} element={<Profesores />} />
              <Route path={PrivateRoutes.TUTOR} element={<Tutores />} />
              <Route path={`${PrivateRoutes.SINGLEUSERINFO}/:id`} element={<UsuarioInfo />} />
              <Route path={PrivateRoutes.DASHBOARD_STUDENT_TUTOR} element={<h1>Dashboard Student | tutor</h1>} />

              {/* Rutas Especificas */}
              <Route element={<RoleGuard rol={Roles.ADMIN} />}>
                <Route path={PrivateRoutes.DASHBOARD} element={<Dashboard />} />
              </Route>
            <Route element={<RoleGuard rol={Roles.TEACHER} />}>
              <Route path={PrivateRoutes.MY_COMMISSIONS} element={<MyCommissions />} />
            </Route>
            <Route element={<RoleGuard rol={Roles.TEACHER} />}>
              <Route path={PrivateRoutes.DASHBOARD_TEACHER} element={<DashboardTeacher />} />
            </Route>
            <Route element={<RoleGuard rol={Roles.STUDENT} />}>
              <Route path={PrivateRoutes.MY_QUALIFICATIONS} element={<MyQualification />} />
            </Route>
            <Route element={<RoleGuard rol={Roles.TUTOR} />}>
              <Route path={PrivateRoutes.MY_STUDENTS} element={<MyStudents />} />
            </Route>
          </Route>
          </Route>
      </RoutesWithNotFound >
    </BrowserRouter >
  );
}

export default App;
