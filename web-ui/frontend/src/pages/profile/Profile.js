import React, { PureComponent } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import cx from 'classnames';
import {
  Row,
  Col,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  ButtonGroup,
} from 'reactstrap';

import photo from '../../images/photo.jpg';
import { fetchProfile } from '../../actions/profile';
import { showModal, hideModal } from '../../actions/modal';
import s from './Profile.module.scss';
import ModalRoot from '../../ModalRoot';

import pencil from '../../images/pencil-icon.svg';
import camera from '../../images/camera-icon.svg';


const MESSAGE = "A redux modal component."



class Profile extends React.Component {
  /* eslint-disable */
  static propTypes = {
    profile: PropTypes.any,
    isFetching: PropTypes.bool,
    //dispatch: PropTypes.func.isRequired,
  };
  /* eslint-enable */
  
  // eslint-disable-next-line 
  constructor(props, context) {
    super(props, context);
    this.state = {isEditing: false};
    
    this.closeModal = this.closeModal.bind(this);
    this.openModal = this.openModal.bind(this);


  }

  

  closeModal() {
    this.props.hideModal()
  }

  openModal() {
    this.props.showModal({
      open: true,
      title: 'Edit Profile',
      message: MESSAGE,
      closeModal: this.closeModal
    }, 'profile');

    /*this.props.dispatch(showModal({
      open: true,
      title: 'Alert Modal',
      message: MESSAGE,
      closeModal: this.closeModal
    }, 'alert'));*/
  }


  componentDidMount() {
    
    //this.props.dispatch(fetchProfile());
    this.props.fetchProfile();        
    
  }

  render() {

    const { keycloak, profile } = this.props;
    

    return (
      <div className={s.root}>
        {/*<div>
          <div className="pull-right mt-n-xs">
            <Button color="info" id="show-info-message">Profile 10% Completed</Button>
          </div>
          <div className="mt-0 mb-3">
            <span className="thumb-sm float-left mr">
              <img className={cx('rounded-circle mr-sm', s.profilePhoto)} src={photo} alt="..." />
            </span>
            <div>
              <h3>{keycloak.tokenParsed['family_name']} {keycloak.tokenParsed['given_name']}</h3>              
                <p className={s.location}>{profile ? profile.content : ''}</p>
                <button onClick={this.openModal}>edit</button>
                
            </div>

          </div>
        </div>*/}

        <section className={s.profile}>
            <div className={s.container}>
                <div class="d-flex justify-content-between flex-wrap">
                    <div class="start d-flex align-items-center mb-3">
                        <div className={`avatarContainer position-relative`}>
                            <img className={cx('rounded-circle mr-sm', s.profilePhoto)} src={photo} alt="..." width="96" height="96"/>
                            <button className={s.cameraBtn}>
                                <img src={camera} alt="Camera icon"/>
                            </button>
                        </div>
                        <div class="ml-4 user-info position-relative">
                            <h1>{keycloak.tokenParsed['family_name']} {keycloak.tokenParsed['given_name']}</h1>
                            <p class="black mb-0">Kuala Lumpur, Malaysia</p>
                            <button className={s.editBtn}>
                                <img src={pencil} alt="Edit icon"/>
                            </button>
                        </div>
                    </div>
                    <div class="end">
                        <div className={cx('progress', s.progress)}>
                            <div className={cx('progress-bar', s.progressBar)}></div>
                            <p class="mb-0">Profile <span className={s.bold}>14% </span> Completed</p>
                        </div>
                    </div>
                </div>
                <div className={s.bio}>
                    <p>Self taught software developer with 1 year of experience. Very interested in highly concurrent/realtime systems, cloud computing/serverless architecture and open standards. Currently studying data analytics and machine learning.</p>
                    <button className={s.editBtn}>
                        <img src={pencil} alt="Edit icon"/>
                    </button>
                </div>
            </div>
        </section>


        <ModalRoot hideModal={this.props.hideModal}/>
    </div>
    
    )
  }
}


const mapStateToProps = (state) => {
  
  return {
    keycloak: state.keycloak.keycloak,
    profile: state.profile.data
  };
}


const mapDispatchToProps = dispatch => ({
  hideModal: () => dispatch(hideModal()),
  showModal: (modalProps, modalType) => {
    dispatch(showModal({ modalProps, modalType }))
  },
  fetchProfile: () => 
    dispatch(fetchProfile())
  
})





export default connect(mapStateToProps, mapDispatchToProps)(Profile);
