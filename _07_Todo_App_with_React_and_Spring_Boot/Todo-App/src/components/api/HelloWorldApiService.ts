import { AxiosResponse } from "axios";
import { apiClient } from "./ApiClient";

interface HelloWorldBeanResponse {
  message: string;
}

export const retrieveHelloWorldBean =
  async (): Promise<HelloWorldBeanResponse> => {
    const response: AxiosResponse<HelloWorldBeanResponse> =
      await apiClient.get<HelloWorldBeanResponse>("/hello-bean");
    return response.data;
  };

export const retrieveHelloWorldBeanPathVariable = async (
  name: string
): Promise<HelloWorldBeanResponse> => {
  try {
    const response: AxiosResponse<HelloWorldBeanResponse> =
      await apiClient.get<HelloWorldBeanResponse>(
        `/hello-bean/path-variable/${name}`
      );
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};
