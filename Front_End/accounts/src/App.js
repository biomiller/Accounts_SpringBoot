import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import axios from "axios";

import { CreateAccount } from "./components/CreateAccount.js"
import { AccountList } from "./components/AccountList.js"
import {TopNavbar } from "./components/TopNavbar.js" 

export class App extends Component {

  constructor() {
    super();
    this.state = {
      data: []
    }
  }

  getData = () => {
    axios
      .get("http://localhost:8080/account/all")
      // .get("/accounts/account/all")
      .then(response => {
        console.log(response.data);
        this.setState({
          data: response.data
        });
      });
  };

  componentDidMount = () => {
    this.getData()
  };


  render() {
    return (
      <div>

        <Router>
        <TopNavbar />
          <Route exact path={`/`} render={() => <AccountList getData={this.getData} data={this.state.data} />} />
          <Route exact path={`/createAccount`} render={() => <CreateAccount getData={this.getData} />} />

        </Router>
      </div>
    );
  };
};

export default App;
