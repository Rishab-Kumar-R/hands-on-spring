import { AxiosResponse } from "axios";
import { apiClient } from "./ApiClient";

interface Todo {
  id: number;
  username: string;
  description: string;
  targetDate: Date;
  isDone: boolean;
}

export const retrieveAllTodosApi = (
  username: string
): Promise<AxiosResponse<Todo[]>> => {
  return apiClient.get(`/users/${username}/todos`);
};

export const deleteTodoApi = (
  username: string,
  id: number
): Promise<AxiosResponse<void>> => {
  return apiClient.delete(`/users/${username}/todos/${id}`);
};

export const retrieveTodoApi = (
  username: string,
  id: number
): Promise<AxiosResponse<Todo>> => {
  return apiClient.get(`/users/${username}/todos/${id}`);
};

export const updateTodoApi = (
  username: string,
  id: number,
  todo: Todo
): Promise<AxiosResponse<void>> => {
  return apiClient.put(`/users/${username}/todos/${id}`, todo);
};

export const createTodoApi = (
  username: string,
  todo: Todo
): Promise<AxiosResponse<void>> => {
  return apiClient.post(`/users/${username}/todos`, todo);
};
