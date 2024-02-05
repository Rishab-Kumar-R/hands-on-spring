import { apiClient } from "./ApiClient";

export const executeBasicAuthService = async (token: string) => {
  const response = await apiClient.get("/basic-auth", {
    headers: {
      authorization: token,
    },
  });
  return response;
};

export const executeJWTAuthService = async (
  username: string,
  password: string
) => {
  const response = await apiClient.post("/authenticate", {
    username,
    password,
  });
  return response;
};
