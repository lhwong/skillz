import React from 'react'
import { connect } from 'react-redux'
import ReactModal from 'react-modal'

import { default as modalTypes } from './components/Modals'

const MODAL_TYPES = {
  'profile': modalTypes.profileModal
}


class ModalContainer extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      modalIsOpen: props.modalProps.open
    }
    this.closeModal = this.closeModal.bind(this)
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.modalProps.open !== this.props.modalProps.open) {
      this.setState({
        modalIsOpen: nextProps.modalProps.open
      })
    }
  }

  closeModal() {
    this.props.hideModal()
  }

  render() {
    
    if (!this.props.modalType) {
      return null
    }
    const SpecifiedModal = MODAL_TYPES[this.props.modalType]

    return (
      <div>
        <ReactModal
          isOpen={this.state.modalIsOpen}
          onRequestClose={this.closeModal}
          contentLabel="Example Modal"
          ariaHideApp={false}
          overlayClassName="modal fade show"
          bodyOpenClassName="modal-open"
          className="modal-dialog modal-dialog-centered"
        >

          <SpecifiedModal
            closeModal={this.closeModal}
            {...this.props.modalProps}
          />
          
        </ReactModal>
      </div>
    )
  }
}

const mapStateToProps = state => ({
    ...state.modal
  })

  /*const mapStateToProps = (state) => {
    console.log(state);
    return {
        modalProps: state.profile.modalProps,
        modalType: state.profile.modalType
    };
  }*/
  

export default connect(mapStateToProps, null)(ModalContainer)