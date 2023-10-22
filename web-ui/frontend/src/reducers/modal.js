import {
    SHOW_MODAL,
    HIDE_MODAL
} from '../actions/modal';

const initialState = {
    modalType: null,
    modalProps: {
      open: false
    }
  }
  
  export default (state = initialState, action) => {
    switch (action.type) {
      case SHOW_MODAL:
        return {
          modalProps: action.modalProps,
          modalType: action.modalType,
          type: action.type
        }
      case HIDE_MODAL:
        return initialState
      default:
        return state
    }
  }