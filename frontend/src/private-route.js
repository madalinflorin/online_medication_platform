import React from 'react';
import { Route, Redirect } from 'react-router-dom';

import AuthService from "./services/auth.service";

const PrivateRoute = ({component: Component,role, ...rest}) => {
    const user = AuthService.getCurrentUser();

    return (

        // Show the component only when the user is logged in
        // Otherwise, redirect the user to /signin page
        <Route {...rest} render={props => (
            user.roles.includes(role) ?
                <Component {...props} />
                : <Redirect to="/home" />
        )} />
    );
};

export default PrivateRoute;