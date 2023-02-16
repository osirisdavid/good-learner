import React, { useState } from 'react';
import ButtonMain from './UI/ButtonMain';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { createUser } from '../app/states/user';
import { PrivateRoutes, Roles, User } from '../models';
import { loginService } from '../services/loginService';
const user = {
  id: 0,
  name: 'roberto',
  username: 'robert',
  rol_id:'admin'
}
const LoginForm = () => {
  const dispatch = useDispatch()
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const [forgotPassword, setForgotPassword] = useState(false);
  const navigate = useNavigate();

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = () => {
    localStorage.setItem("token", "test-token");
    loginService(formData.email,formData.password)
    console.log(formData);
    console.log(localStorage.getItem("token"))
    dispatch(createUser( user ));
    navigate(`/${PrivateRoutes.DASHBOARD}`, { replace: true }); 
    
  };

  const handleResetPassword = () => {
    console.log(formData.email);
  };

  return (
    <Form className="d-flex flex-column px-4 py-5 bg-white rounded">
      {!forgotPassword ? (
        <>
          <Form.Group className="mb-2" controlId="formBasicEmail">
            <Form.Label></Form.Label>
            <Form.Control
              type="email"
              placeholder="Ingresá tu email"
              name="email"
              onChange={handleOnChange}
              style={{ height: '4rem' }}
            />
          </Form.Group>
          <Form.Group className="mb-2" controlId="formBasicPassword">
            <Form.Label></Form.Label>
            <Form.Control
              type="password"
              placeholder="Ingresá tu contraseña"
              name="password"
              onChange={handleOnChange}
              style={{ height: '4rem' }}
            />
          </Form.Group>
          <Form.Group className="mb-2" controlId="formBasicCheckbox">
            <Button variant="disabled" onClick={() => setForgotPassword(true)}>
              ¿Olvidaste tu contraseña?
            </Button>
          </Form.Group>
          <ButtonMain
            text={'INICIAR SESIÓN'}
            size="lg"
            onClick={handleSubmit}
          />
        </>
      ) : (
        <>
          <Form.Group className="mb-2" controlId="formBasicEmail">
            <p style={{ margin: 0 }}>
              Te enviaremos a tu correo electrónico las instrucciones sobre como
              restablecer tu contraseña.
            </p>
            <Form.Label></Form.Label>
            <Form.Control
              type="email"
              placeholder="Ingresá tu email"
              name="email"
              onChange={handleOnChange}
              style={{ height: '4rem' }}
            />
          </Form.Group>

          <ButtonMain
            text={'RESTABLECER CONTRASEÑA'}
            size="lg"
            onClick={handleResetPassword}
            className="mt-2"
          />
          <Form.Group className="mt-2" controlId="formBasicCheckbox">
            <Button variant="disabled" onClick={() => setForgotPassword(false)}>
              ¿Deseas iniciar sesión?
            </Button>
          </Form.Group>
        </>
      )}
    </Form>
  );
};
export default LoginForm;
