import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useAuthContext } from "./security/useAuthContext";
import styled from "styled-components";

const LoginContainer = styled.div`
  max-width: 500px;
  margin: 0 auto;
  padding: 30px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 100px;
`;

const LoginTitle = styled.h1`
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
`;

const FormField = styled.div`
  margin-bottom: 20px;

  label {
    font-size: 16px;
    margin-bottom: 8px;
  }

  input {
    padding: 12px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
`;

const LoginButton = styled.button`
  padding: 12px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #0056b3;
  }
`;

const LoginComponent = () => {
  const navigate = useNavigate();
  const authContext = useAuthContext();

  const [username, setUsername] = useState("user");
  const [password, setPassword] = useState("");

  const changeUsernameHandler = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setUsername(event.target.value);
  };

  const changePasswordHandler = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setPassword(event.target.value);
  };

  const submitHandler = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const isAuthenticated = authContext.authenticate(username, password);
    if (await isAuthenticated) {
      navigate(`/home/${username}`);
      toast.success("Login successful! Welcome to your Todo App.", {
        autoClose: 5000,
      });
    } else {
      authContext.logout();
      toast.error("Incorrect username or password. Please try again.", {
        autoClose: 5000,
      });
    }
  };

  return (
    <LoginContainer>
      <LoginTitle>Login</LoginTitle>
      <LoginForm onSubmit={submitHandler}>
        <FormField>
          <label htmlFor="username" style={{ marginRight: "25px" }}>
            Username
          </label>
          <input
            type="text"
            id="username"
            name="username"
            value={username}
            onChange={changeUsernameHandler}
            aria-label="Username"
            style={{ width: "60%" }}
          />
        </FormField>

        <FormField>
          <label htmlFor="password" style={{ marginRight: "30px" }}>
            Password
          </label>
          <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={changePasswordHandler}
            aria-label="Password"
            style={{ width: "60%" }}
          />
        </FormField>

        <LoginButton type="submit">Login</LoginButton>
      </LoginForm>
    </LoginContainer>
  );
};

export default LoginComponent;
