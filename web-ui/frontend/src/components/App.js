import React from 'react';
import { Route, Switch, withRouter, Redirect } from "react-router";
import { connect } from "react-redux";


import NavigationBar from '../components/NavigationBar'
//import './css/App.css';

import '../styles/theme.scss';
import LayoutComponent from '../components/Layout';

import { setAuthenticated } from "../actions/keycloakActions";
import { doShowProfile } from "../actions/profileActions";

const PrivateRoute = ({ dispatch, component, props, ...rest }) => {
    //console.log(props.keycloak.authenticated);
    if (!props.keycloak.authenticated) {
        //dispatch(logoutUser());
        
        return (<Redirect to="/home" />)
    } else {
        return ( // eslint-disable-line
            <Route {...rest} render={props => (React.createElement(component, props))} />
        );
    }
};

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            isAuthenticated: false,
            isAuthenticating: true
        };
    }
    //Todo: Move this to action
    checkSSO() {
        return this.props.keycloak.init({
            onLoad: 'check-sso',
            silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html',
            promiseType: 'native'
        });/*.then(authenticated => {
            console.log(authenticated ? 'authenticated' : 'not authenticated');
            //this.setState({ keycloak: keycloak, isAuthenticated: authenticated })
            this.setState({ isAuthenticated: authenticated })
        }).catch(function() {
            alert('failed to initialize');
        });*/

    }

    userHasAuthenticated = authenticated => {
        this.setState({ isAuthenticated: authenticated });
    }

    async componentDidMount() {


        try {
            const authenticated = await this.checkSSO();
            //console.log(authenticated ? 'authenticated' : 'not authenticated');
            this.userHasAuthenticated(authenticated);

        } catch (e) {
            alert('failed to initialize');
        }

        this.setState({ isAuthenticating: false });
    }

    render() {
        const childProps = this.props;

        return (
            !this.state.isAuthenticating &&
            <div id="app">


                <Switch>
                    <Route path="/" exact render={() => <Redirect to="/app/main" />} />
                    <Route path="/app" exact render={() => <Redirect to="/app/main" />} />
                    <PrivateRoute
                        path='/app'
                        dispatch={this.props.dispatch}
                        component={LayoutComponent}
                        props={childProps}
                    />
                    <Route exact path='/home' component={NavigationBar} />

                </Switch>


            </div>
        );
    }
};

const mapStateToProps = (state) => {
    return {
        keycloak: state.keycloak.keycloak,
        showProfile: state.profile.showProfile
    };
}

export default withRouter(connect(mapStateToProps, { setAuthenticated, doShowProfile })(App));