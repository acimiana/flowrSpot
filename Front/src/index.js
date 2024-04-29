import React from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, Navigate, BrowserRouter as Router, Routes } from 'react-router-dom';
import Home from './components/Home';
import NotFound from './components/NotFound';
import { Container, Nav, Navbar, Button } from 'react-bootstrap';
import Login from './components/authorization/Login';
import { logout } from './services/auth';
import FlowersTable from './components/entity/FlowersTable';
import SightingsTable from './components/entity/SightingsTable';
import UserTable from './components/entity/UserTable';

const App = () => {

    // U zavisnosti od toga da li postoji jwt u local storage-u (da li je korisnik ulogovan)
    // vracamo nazad drugaciju Home stranicu koja prikazuje drugacije stvari u nav bar-u
    if(window.localStorage["jwt"]){
      return (
          <>
              <Router>
                  <Navbar expand bg="dark" variant="dark">
                      <Navbar.Brand as={Link} to="/">
                          Positive tech
                      </Navbar.Brand>
                      <Nav>
                      <Button onClick={logout}>Logout</Button>
                      </Nav>
              </Navbar>
              <Container style={{paddingTop:"10px"}}>
              <Routes>
                      <Route path="/" element={<Home />} />
                      <Route path="/login" element={<Navigate replace to = "/xxx" />} />
                      <Route path="*" element={<NotFound />} />
                  </Routes>
              </Container>
              </Router>
          </>
      );
    } else {
      return(
       <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/login">
                        Login
                    </Nav.Link>
                    <Nav.Link as={Link} to="/sightings">
                        Sightings
                    </Nav.Link>
                    <Nav.Link as={Link} to="/users">
                        Users
                    </Nav.Link>
                    </Nav>
            </Navbar>
            <Container style={{paddingTop:"10px"}}>
            <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/flowers" element={<FlowersTable />} />
                    <Route path="/sightings" element={<SightingsTable />} />
                    <Route path="/users" element={<UserTable />} />
                    <Route path="*" element={<Navigate replace to = "/login" />} />
                </Routes>
            </Container>
            </Router>
        </>
        );
    }

};


const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App />,
);
