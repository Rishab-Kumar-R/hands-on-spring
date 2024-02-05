import { useCallback, useEffect, useState } from "react";
import { deleteTodoApi, retrieveAllTodosApi } from "./api/TodoApiService";
import { useAuthContext } from "./security/useAuthContext";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

type Todo = {
  id: number;
  description: string;
  isDone: boolean;
  targetDate: Date;
};

const TodosWrapper = styled.div`
  margin: 20px;
`;

const TodosTable = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
`;

const TableHeader = styled.th`
  border: 1px solid #ddd;
  padding: 8px;
  background-color: #f2f2f2;
`;

const TableData = styled.td`
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
`;

const EvenRow = styled.tr`
  background-color: #f9f9f9;
`;

const Button = styled.button`
  background-color: #007bff;
  color: white;
  border: none;
  font-size: 16px;
  padding: 12px;
  cursor: pointer;
  border-radius: 5px;
  margin-top: 20px;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #0056b3;
  }
`;

const ListTodos = () => {
  const { username } = useAuthContext();
  const navigate = useNavigate();

  const [todos, setTodos] = useState<Todo[]>([]);
  const [message, setMessage] = useState<string>("");

  const refreshTodos = useCallback(() => {
    if (username) {
      retrieveAllTodosApi(username)
        .then((response) => {
          setTodos(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [username]);

  const deleteTodo = (id: number) => {
    if (username) {
      deleteTodoApi(username, id)
        .then(() => {
          setMessage(`Delete of todo ${id} successful`);
          setTimeout(() => setMessage(""), 5000);
          refreshTodos();
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const updateTodo = (id: number) => {
    console.log("update " + id);
    navigate(`/todos/${id}`);
  };

  const addNewTodo = () => {
    navigate("/todos/-1");
  };

  useEffect(() => {
    refreshTodos();
  }, [refreshTodos]);

  return (
    <TodosWrapper>
      <h1
        style={{
          textAlign: "center",
          marginBottom: "20px",
        }}
      >
        Your Todos
      </h1>
      {message && (
        <div
          style={{
            color: "red",
            fontWeight: "bold",
            fontSize: "20px",
            textAlign: "center",
            backgroundColor: "#fae0e4",
            padding: "10px",
            borderRadius: "5px",
          }}
        >
          {message}
        </div>
      )}
      <TodosTable>
        <thead>
          <tr>
            <TableHeader>Description</TableHeader>
            <TableHeader>Is Completed?</TableHeader>
            <TableHeader>Target Date</TableHeader>
            <TableHeader>Delete</TableHeader>
            <TableHeader>Update</TableHeader>
          </tr>
        </thead>
        <tbody>
          {todos.map((todo, index) => (
            <EvenRow
              key={todo.id}
              className={index % 2 === 0 ? "even-row" : ""}
            >
              <TableData>{todo.description}</TableData>
              <TableData>{todo.isDone ? "Yes" : "No"}</TableData>
              <TableData>
                {todo.targetDate ? todo.targetDate.toLocaleString() : ""}
              </TableData>
              <TableData>
                <button
                  style={{
                    backgroundColor: "red",
                    color: "white",
                    border: "none",
                    padding: "10px 20px",
                    cursor: "pointer",
                    borderRadius: "5px",
                  }}
                  onClick={() => deleteTodo(todo.id)}
                >
                  Delete
                </button>
              </TableData>
              <TableData>
                <button
                  style={{
                    backgroundColor: "green",
                    color: "white",
                    border: "none",
                    padding: "10px 20px",
                    cursor: "pointer",
                    borderRadius: "5px",
                  }}
                  onClick={() => updateTodo(todo.id)}
                >
                  Update
                </button>
              </TableData>
            </EvenRow>
          ))}
        </tbody>
      </TodosTable>
      <Button onClick={addNewTodo}>Add New Todo</Button>
    </TodosWrapper>
  );
};

export default ListTodos;
