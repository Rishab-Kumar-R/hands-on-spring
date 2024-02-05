import { useContext } from "react";
import { AuthContext, AuthContextType } from "./AuthContext";

export const useAuthContext = (): AuthContextType => useContext(AuthContext);
