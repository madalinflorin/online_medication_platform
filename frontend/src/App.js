import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import PersonContainer from "./person/person-container"
import PersonContainer2 from "./person/person-container-2"
import PersonContainer1 from "./person/person-container1"
import DoctorContainer from "./person/doctor-container"
import CaregiverContainer from "./person/caregiver-container"
import MedicationContainer from "./person/medication-container"
import PrivateRoute from "./private-route"
import UserContainer from "./person/user-container"
import MedicalPlansContainer from "./person/medical-plans-container";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      showCaregiverBoard: false,
      showDoctorBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showCaregiverBoard: user.roles.includes("ROLE_CAREGIVER"),
        showDoctorBoard: user.roles.includes("ROLE_DOCTOR"),
      });
    }
  }

  logOut() {
    AuthService.logout();
  }

  render() {
    const { currentUser, showCaregiverBoard, showDoctorBoard } = this.state;

    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            Medical Platform
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home
              </Link>
            </li>

            {showCaregiverBoard && (
              <li className="nav-item">
                <Link to={"/caregiver"} className="nav-link">
                  Caregiver Board
                </Link>
              </li>
            )}

            {showDoctorBoard && (
              <li className="nav-item">
                <Link to={"/doctor"} className="nav-link">
                  Doctor Board
                </Link>
              </li>
            )}


            {currentUser && !showCaregiverBoard && !showDoctorBoard && (
              <li className="nav-item">
                <Link to={"/patient"} className="nav-link">
                  User
                </Link>
              </li>
            )}


          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  {currentUser.username}
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/home"]} component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            <PrivateRoute component={UserContainer} role="ROLE_PATIENT" path="/patient" exact />
            <PrivateRoute component={PersonContainer2} role="ROLE_CAREGIVER" path="/caregiver" exact />
            <PrivateRoute component={DoctorContainer} role="ROLE_DOCTOR" path="/doctor" exact />
            <PrivateRoute component={PersonContainer} role="ROLE_DOCTOR" path="/doctor/list/pacients" exact />
            <PrivateRoute component={CaregiverContainer} role="ROLE_DOCTOR" path="/doctor/list/caregivers" exact />
            <PrivateRoute component={MedicationContainer} role="ROLE_DOCTOR" path="/doctor/list/medications" exact />
            <PrivateRoute component={PersonContainer1} role="ROLE_DOCTOR" path="/doctor/list/pacient" exact />
            <PrivateRoute component={MedicalPlansContainer} role="ROLE_DOCTOR" path="/doctor/list/medical_plans" exact />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
