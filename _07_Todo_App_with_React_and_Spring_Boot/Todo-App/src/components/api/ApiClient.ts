import axios, { AxiosInstance } from "axios";

interface ApiClientConfig {
  baseURL: string;
}

const apiClientConfig: ApiClientConfig = {
  baseURL: "http://localhost:8080",
};

const createApiClient = (config: ApiClientConfig): AxiosInstance => {
  return axios.create(config);
};

export const apiClient = createApiClient(apiClientConfig);
