import { useNavigate, useParams } from "react-router-dom";
import {
  createTodoApi,
  retrieveTodoApi,
  updateTodoApi,
} from "./api/TodoApiService";
import { useAuthContext } from "./security/useAuthContext";
import { useCallback, useEffect, useState } from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";
import styled from "styled-components";
import moment from "moment";

const TodoContainer = styled.div`
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #ffffff;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 50px;
`;

const TodoTitle = styled.h1`
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
`;

const TodoForm = styled(Form)`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const TodoFieldset = styled.fieldset`
  margin-bottom: 20px;
  width: 100%;
  padding: 20px 10px 5px 10px;
  border: none;

  label {
    font-size: 16px;
    margin-bottom: 8px;
    display: block;
  }

  input {
    width: 100%;
    padding: 12px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
  }
`;

const TodoButton = styled.button`
  width: 100%;
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

const TodoComponent = () => {
  const [description, setDescription] = useState<string>("");
  const [targetDate, setTargetDate] = useState<Date>(new Date());

  const { id } = useParams<{ id: string }>();
  const { username } = useAuthContext();

  const navigate = useNavigate();

  const retrieveTodos = useCallback(() => {
    if (Number(id) !== -1) {
      if (username) {
        retrieveTodoApi(username, Number(id))
          .then((response) => {
            setDescription(response.data.description);
            setTargetDate(response.data.targetDate);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  }, [username, id]);

  useEffect(() => {
    retrieveTodos();
  }, [username, id, retrieveTodos]);

  const handleSubmit = (values: { description: string; targetDate: Date }) => {
    console.log(values);
    if (username) {
      const todo = {
        id: Number(id),
        username: username,
        description: values.description,
        targetDate: values.targetDate,
        isDone: false,
      };

      if (Number(id) === -1) {
        createTodoApi(username, todo)
          .then(() => {
            navigate("/todos");
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        updateTodoApi(username, Number(id), todo)
          .then(() => {
            navigate("/todos");
          })
          .catch((error) => {
            console.log(error);
          });
      }
    } else {
      console.log("Username is not available");
    }
  };

  const validate = (values: { description: string; targetDate: Date }) => {
    const errors: { description?: string; targetDate?: string } = {};

    if (!values.description) {
      errors.description = "Enter a Description";
    } else if (values.description.length < 5) {
      errors.description = "Enter at least 5 Characters in Description";
    }

    if (
      values.targetDate === null ||
      values.targetDate === undefined ||
      !moment(values.targetDate).isValid()
    ) {
      errors.targetDate = "Enter a Target Date";
    }

    return errors;
  };

  return (
    <TodoContainer>
      <TodoTitle>Enter Todo Details</TodoTitle>
      <Formik
        initialValues={{ description, targetDate }}
        onSubmit={handleSubmit}
        enableReinitialize={true}
        validate={validate}
        validateOnChange={false}
        validateOnBlur={false}
      >
        {() => (
          <TodoForm>
            <ErrorMessage name="description" component="div" />
            <ErrorMessage name="targetDate" component="div" />
            <TodoFieldset>
              <label htmlFor="description">Description</label>
              <Field type="text" id="description" name="description" />
            </TodoFieldset>
            <TodoFieldset>
              <label htmlFor="targetDate">Target Date</label>
              <Field type="date" id="targetDate" name="targetDate" />
            </TodoFieldset>
            <TodoButton type="submit">Save</TodoButton>
          </TodoForm>
        )}
      </Formik>
    </TodoContainer>
  );
};

export default TodoComponent;
