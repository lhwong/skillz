export const FETCH_PROFILE_REQUEST = 'FETCH_PROFILE_REQUEST';
export const FETCH_PROFILE_SUCCESS = 'FETCH_PROFILE_SUCCESS';
export const FETCH_PROFILE_FAILURE = 'FETCH_PROFILE_FAILURE';
//export const SHOW_MODAL = 'SHOW_MODAL';
//export const HIDE_MODAL = 'HIDE_MODAL';

function requestFetchProfile() {
    return {
        type: FETCH_PROFILE_REQUEST,
        isFetching: true
    }
}
function fetchProfileSuccess(profile) {
    return {
        type: FETCH_PROFILE_SUCCESS,
        isFetching: false,
        profile
    }
}
function fetchProfileError(message) {
    return {
        type: FETCH_PROFILE_FAILURE,
        isFetching: false,
        message,
    };
}

export function fetchProfile() {
    return dispatch => {
        dispatch(requestFetchProfile())
        return fetch(`/resource/`)
            .then(response => response.json().then(profile => ({
                profile,
                response
            })),
            )
            .then(({ profile, response }) => {
                if (!response) {
                    // If there was a problem, we want to
                    // dispatch the error condition
                    dispatch(fetchProfileError(profile));
                    return Promise.reject(profile);
                }
                // Dispatch the success action
                dispatch(fetchProfileSuccess(profile));
                return Promise.resolve(profile);
            })
            .catch(error => {
                throw (error);
            });
    }
}

/*export function showModal(modalProps, modalType) {
    return dispatch => {
        dispatch({
            type: SHOW_MODAL,
            modalProps,
            modalType
        })
    }
}


export function hideModal() {
    return dispatch => {
        dispatch({
            type: HIDE_MODAL
        })
    }
}*/