import {
    FETCH_PROFILE_REQUEST,
    FETCH_PROFILE_SUCCESS,
    FETCH_PROFILE_FAILURE
} from '../actions/profile';



export default (state = {
    isFetching: false,
    modalType: 'profile',
    modalProps: {
        open: false
    }
}, action = {}) => {
    switch (action.type) {
        case FETCH_PROFILE_REQUEST:
            return Object.assign({}, state, {
                isFetching: true,
            });
        case FETCH_PROFILE_SUCCESS:
            return Object.assign({}, state, {
                isFetching: false,
                data: action.profile,
            });
        case FETCH_PROFILE_FAILURE:
            return Object.assign({}, state, {
                isFetching: false,
                message: 'Something wrong happened. Please come back later',
            });
        /*case SHOW_MODAL:
            return Object.assign({}, state, {
                modalProps: action.modalProps,
                modalType: action.modalType,
            });
        case HIDE_MODAL:
            return Object.assign({}, state, {
                modalType: 'profile',
                modalProps: {
                    open: false
                }
            });*/    
        default:
            return state;
    }
}
