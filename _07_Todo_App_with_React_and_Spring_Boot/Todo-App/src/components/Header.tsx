import { NavLink } from "react-router-dom";
import { useAuthContext } from "./security/useAuthContext";
import styled from "styled-components";

const HeaderContainer = styled.header`
  background-color: #f5f5f5;
  color: #000;
  padding: 12px 0;
  border-bottom: 2px solid #ddd;
`;

const Nav = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
`;

const LeftSection = styled.div`
  display: flex;
  align-items: center;
`;

const NavbarList = styled.ul`
  list-style: none;
  display: flex;
  gap: 20px;
  margin: 0;
  padding: 0;
`;

const NavbarItem = styled.li`
  &.navbar-brand {
    font-size: 1.5em;
    font-weight: bold;
  }

  &.nav-item {
    font-size: 1em;
  }

  .navbar-link {
    text-decoration: none;
    color: #000;
    padding: 10px;
  }
`;

const RightSection = styled.div`
  display: flex;
  align-items: center;
`;

const Header = () => {
  const authContext = useAuthContext();
  const isAuthenticated = authContext.isLoggedIn;

  return (
    <HeaderContainer>
      <Nav>
        <LeftSection>
          <NavbarList>
            <NavbarItem className="navbar-brand">
              <NavLink to="#" className="navbar-link">
                Todo App
              </NavLink>
            </NavbarItem>
            {isAuthenticated && (
              <NavbarItem className="nav-item">
                <NavLink
                  to={`/home/${authContext.username}`}
                  className="navbar-link"
                >
                  Home
                </NavLink>
              </NavbarItem>
            )}
            {isAuthenticated && (
              <NavbarItem className="nav-item">
                <NavLink to="/todos" className="navbar-link">
                  Todos
                </NavLink>
              </NavbarItem>
            )}
          </NavbarList>
        </LeftSection>
        <RightSection>
          {isAuthenticated ? (
            <NavbarList>
              <NavbarItem className="nav-item">
                <NavLink
                  to="/logout"
                  className="navbar-link"
                  onClick={authContext.logout}
                >
                  Logout
                </NavLink>
              </NavbarItem>
            </NavbarList>
          ) : (
            <NavbarList>
              <NavbarItem className="nav-item">
                <NavLink to="/login" className="navbar-link">
                  Login
                </NavLink>
              </NavbarItem>
            </NavbarList>
          )}
        </RightSection>
      </Nav>
    </HeaderContainer>
  );
};

export default Header;
