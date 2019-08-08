import React, { Component } from 'react';
import axios from "axios";

import {
    Button
} from 'reactstrap';


export class CreateAccount extends Component {


    constructor() {
        super();
        this.state = {
            firstName: "",
            lastName: "",
            prize: ""
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();

        let firstName = e.target[0].value
        let lastName = e.target[1].value

        let newAccount = {
            firstName: firstName,
            lastName: lastName
        }

        axios
            .post("http://accounts:8080/accounts/createAccount", newAccount)
            .then(response => {
                console.log(response);
                this.props.getData();
                this.setState({
                    prize: response
                })


            })
            .catch(err => { console.log(err) })
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <h1>Create Account</h1>

                <input required type="text" placeholder="First name"></input>
                <input required type="text" placeholder="Last name"></input>
                
                <Button type="submit">Submit</Button>

                <br></br>
                <p>{this.state.prize}</p>
            </form>
        )
    }
}