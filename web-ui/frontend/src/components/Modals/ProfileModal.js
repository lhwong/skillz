import React from 'react'

const ProfileModal = ({ closeModal, title, message }) => {
  return (
      
    <div className="modal-content">
      <div className="modal-header">
        <h5
          className="modal-title"
        >{title}</h5>
        <button type="button" className="close" aria-label="Close" onClick={closeModal}>
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div className="modal-body">
        <p>{message}</p>
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-primary" onClick={closeModal}>Close</button>
      </div>
    </div>
  )
}

export default ProfileModal