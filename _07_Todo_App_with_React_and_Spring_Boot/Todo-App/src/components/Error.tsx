import { Link, useLocation } from "react-router-dom";

const Error = () => {
  const location = useLocation();

  const errorStyle: React.CSSProperties = {
    color: "#333",
    fontSize: "24px",
    fontWeight: "bold",
    textAlign: "center",
    padding: "40px",
    background: "#f5f5f5",
    border: "2px solid #ddd",
    borderRadius: "10px",
    width: "70%",
    maxWidth: "400px",
    minHeight: "200px",
    margin: "auto",
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  };

  return (
    <div style={errorStyle}>
      <p>
        Oops! The page at <strong>"{location.pathname}"</strong> doesn't exist.
      </p>
      <p>
        Return to <Link to="/">home</Link> or contact our support team at{" "}
        <Link to="mailto:abcd-efgh-ijkl">abcd-efgh-ijkl</Link>.
      </p>
    </div>
  );
};

export default Error;
