import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import styled from "styled-components";
import { retrieveHelloWorldBeanPathVariable } from "./api/HelloWorldApiService";

interface Param {
  username: string;
  [key: string]: string | undefined;
}

const WelcomeContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
`;

const WelcomeText = styled.div`
  font-size: 30px;
  color: #333;
  text-align: center;
  margin-bottom: 20px;
`;

const AdditionalContent = styled.div`
  font-size: 16px;
  color: #666;
  text-align: center;
  margin-bottom: 20px;
`;

const WelcomeLink = styled(Link)`
  color: #007bff;
  text-decoration: none;
`;

interface ErrorWithMessage {
  message: string;
}

const Welcome = () => {
  const { username } = useParams<Param>();
  const [message, setMessage] = useState<string | null>(null);

  const fetchHelloWorldBean = async () => {
    try {
      const response = await retrieveHelloWorldBeanPathVariable("There");
      if (response && typeof response.message === "string") {
        setMessage(response.message);
      } else {
        console.error("Unexpected response structure:", response);
      }
    } catch (error: unknown) {
      if (typeof error === "object" && error !== null) {
        const { message } = error as ErrorWithMessage;
        setMessage(message);
      } else {
        console.error("Unexpected error structure:", error);
      }
    } finally {
      console.log("fetchHelloWorldBeanPathVariable done");
    }
  };

  return (
    <WelcomeContainer>
      <WelcomeText>
        Welcome {username}! Manage your todos{" "}
        <WelcomeLink to="/todos">here</WelcomeLink>.
      </WelcomeText>
      <AdditionalContent>
        Start your day right by organizing your tasks and boosting your
        productivity with our Todo App. Track your progress, set deadlines, and
        stay on top of your to-do list. Let's make each day more productive!
      </AdditionalContent>

      <button
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          backgroundColor: "#007bff",
          color: "#fff",
          border: "none",
          cursor: "pointer",
        }}
        onClick={fetchHelloWorldBean}
      >
        Call to REST API
      </button>

      <div style={{ marginTop: "20px" }}>{message}</div>
    </WelcomeContainer>
  );
};

export default Welcome;
