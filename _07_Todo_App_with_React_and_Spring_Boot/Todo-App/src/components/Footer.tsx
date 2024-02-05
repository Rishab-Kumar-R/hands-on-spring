const Footer = () => {
  const currentYear = new Date().getFullYear();

  return (
    <footer
      style={{
        backgroundColor: "#f0f0f0",
        padding: "10px",
        textAlign: "center",
        position: "fixed",
        left: "0",
        bottom: "0",
        height: "60px",
        width: "100%",
      }}
    >
      <span style={{ color: "#777" }}>
        All Lefts Reversed {currentYear} - You know, just to keep things
        interesting! @Test
      </span>
    </footer>
  );
};

export default Footer;
