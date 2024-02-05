import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Welcome from "./Welcome";
import Landing from "./Landing";
import Error from "./Error";
import Header from "./Header";
import Login from "./Login";
import ListTodos from "./ListTodos";
import Logout from "./Logout";
import AuthProvider from "./security/AuthContext";
import Footer from "./Footer";
import { useAuthContext } from "./security/useAuthContext";
import { ReactNode } from "react";
import TodoComponent from "./TodoComponent";

const AuthenticatedRoute = ({ children }: { children: ReactNode }) => {
  const authContext = useAuthContext();
  const isAuthenticated = authContext.isLoggedIn;
  return isAuthenticated ? children : <Navigate to="/login" />;
};

const TodoApp = () => {
  return (
    <div className="todo-app">
      <AuthProvider>
        <BrowserRouter>
          <Header />
          <Routes>
            <Route path="/" element={<Landing />} />
            <Route path="/login" element={<Login />} />
            <Route
              path="/home/:username"
              element={
                <AuthenticatedRoute>
                  <Welcome />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/todos"
              element={
                <AuthenticatedRoute>
                  <ListTodos />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/todos/:id"
              element={
                <AuthenticatedRoute>
                  <TodoComponent />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/logout"
              element={
                <AuthenticatedRoute>
                  <Logout />
                </AuthenticatedRoute>
              }
            />
            <Route path="*" element={<Error />} />
          </Routes>
        </BrowserRouter>
        <Footer />
      </AuthProvider>
    </div>
  );
};

export default TodoApp;
