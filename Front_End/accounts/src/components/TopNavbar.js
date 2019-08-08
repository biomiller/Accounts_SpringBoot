import React from 'react';

import {
    Navbar,
    NavbarBrand,
    NavItem,
    Nav,
    Button
} from 'reactstrap';

import { Link } from "react-router-dom";

export function TopNavbar(props) {
    return (
        <div>
            <Navbar color="light" light expand="md">
                <Link to={`/`}><NavbarBrand>React Assessment</NavbarBrand></Link>
                <NavbarBrand>{props.loggedIn}</NavbarBrand>
                <Nav>
                    <NavItem>
                        <Link to={`/`}><Button>Home</Button></Link>
                        <Link to={`/createAccount`}><Button>Create Account</Button></Link>
                    </NavItem>
                </Nav>
            </Navbar>
        </div>
    );
}

