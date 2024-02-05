import { ReactNode, createContext, useEffect, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { executeJWTAuthService } from "../api/AuthenticationApiService";

export interface AuthContextType {
  username: string | null;
  isLoggedIn: boolean;
  token: string | null;
  authenticate: (username: string, password: string) => Promise<boolean>;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextType>({
  username: null,
  isLoggedIn: false,
  token: null,
  authenticate: async (): Promise<boolean> => false,
  logout: () => {},
});

interface AuthProviderProps {
  children: ReactNode;
}

const AuthProvider = ({ children }: AuthProviderProps) => {
  const [isAuthenticated, setIsAuthenticated] = useState<AuthContextType>({
    username: null,
    isLoggedIn: false,
    token: null,
    authenticate: async (
      username: string,
      password: string
    ): Promise<boolean> => {
      try {
        const response = await executeJWTAuthService(username, password);

        if (response.status === 200) {
          const jwtToken = "Bearer " + response.data.token;

          setIsAuthenticated((prevState) => ({
            ...prevState,
            username: username,
            isLoggedIn: true,
            token: jwtToken,
          }));

          apiClient.interceptors.request.use((config) => {
            console.log("interceptor");
            config.headers.Authorization = jwtToken;
            return config;
          });
          return true;
        } else {
          return false;
        }
      } catch (error) {
        console.error("Authentication error:", error);
        setIsAuthenticated((prevState) => ({
          ...prevState,
          username: null,
          isLoggedIn: false,
          token: null,
        }));
        return false;
      }

      // const token = "Basic " + window.btoa(username + ":" + password);
      // try {
      //   const response = await executeBasicAuthService(token);
      //   console.log(response);
      //   if (response.status === 200) {
      //     setIsAuthenticated((prevState) => ({
      //       ...prevState,
      //       username: username,
      //       isLoggedIn: true,
      //       token: token,
      //     }));

      //     apiClient.interceptors.request.use((config) => {
      //       console.log("interceptor");
      //       config.headers.Authorization = token;
      //       return config;
      //     });

      //     return true;
      //   } else {
      //     return false;
      //   }
      // } catch (error) {
      //   setIsAuthenticated((prevState) => ({
      //     ...prevState,
      //     username: null,
      //     isLoggedIn: false,
      //     token: null,
      //   }));
      //   return false;
      // }
    },
    logout: () => {
      setIsAuthenticated((prevState) => ({
        ...prevState,
        username: null,
        isLoggedIn: false,
        token: null,
      }));
    },
  });

  useEffect(() => {
    return () => {
      apiClient.interceptors.request.eject(
        apiClient.interceptors.request.use((config) => {
          console.log("Removing interceptor");
          delete config.headers.Authorization;
          return config;
        })
      );
    };
  }, []);

  return (
    <AuthContext.Provider value={isAuthenticated}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
