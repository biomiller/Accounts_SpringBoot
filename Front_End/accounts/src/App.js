import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import axios from "axios";

import { CreateAccount } from "./components/CreateAccount.js"
import {AccountList } from "./components/"

export class App extends Component {

  constructor() {
    super();
    this.state = {
      data: []
    }
  }

  getData = () => {
    axios
      .get("/accounts/account/all")
      .then(response => {
        this.setState({
          data: response.data.reverse()
        });
      });
  };

  componentDidMount = () => {
    this.getData()
  };


  render() {
    return (
      <Router>
        <Route exact path={`/`} render={() => <AccountList getData={this.getData} />} />
        <Route exact path={`/createAccount`} render={() => <CreateAccount getData={this.getData} />} />

      </Router>

    );
  };
};

export default App;
