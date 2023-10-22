import {combineReducers} from 'redux';
import keycloak from "./keycloak/index";
import navigation from './navigation';
//import profile from "./profile/index";
import profile from "./profile";
import modal from './modal'

export default combineReducers({
    keycloak,
    navigation,
    profile,
    modal
});
