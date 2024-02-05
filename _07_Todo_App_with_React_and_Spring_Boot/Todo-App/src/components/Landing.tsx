import styled from "styled-components";

const LandingContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
  text-align: center;
  padding: 20px;
`;

const LandingTitle = styled.h1`
  font-size: 36px;
  margin-bottom: 20px;
  border-bottom: 5px solid #007bff;
`;

const LandingText = styled.p`
  font-size: 18px;
  margin-bottom: 50px;
`;

const FeaturesTitle = styled.h2`
  font-size: 24px;
  margin-bottom: 10px;
  border-bottom: 3px solid #007bff;
`;

const FeaturesList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;

  li {
    font-size: 16px;
    margin-bottom: 8px;
  }
`;

const Landing = () => {
  return (
    <LandingContainer>
      <LandingTitle>Welcome to Your Todo App</LandingTitle>
      <LandingText>
        Start managing your tasks and stay organized with our simple and
        effective Todo App.
      </LandingText>

      <FeaturesTitle>Features</FeaturesTitle>
      <FeaturesList>
        <li>Manage your tasks</li>
        <li>Stay organized</li>
        <li>Simple and effective</li>
      </FeaturesList>
    </LandingContainer>
  );
};

export default Landing;
