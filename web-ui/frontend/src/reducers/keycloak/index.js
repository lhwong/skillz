import Keycloak from 'keycloak-js';
import {SET_AUTHENTICATED} from "./types.js";

const kc = Keycloak('/keycloak.json');
const initialstate = {
    keycloak: kc,
    authenticated: false
}

export default function keycloakReducer(state = initialstate, action) {
    switch(action.type) {
        case SET_AUTHENTICATED:
            return Object.assign({}, state, {
                authenticated: action.payload
            });
        default:
            return state;
    }
}
