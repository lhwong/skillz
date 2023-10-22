import React, {Component} from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import s from './Dashboard.module.scss';

class Dashboard extends Component {
  /* eslint-disable */
  static propTypes = {
    posts: PropTypes.any,
    isFetching: PropTypes.bool,
    dispatch: PropTypes.func.isRequired,
  };
  /* eslint-enable */

  static defaultProps = {
    posts: [],
    isFetching: false,
  };

  state = {
    isDropdownOpened: false
  };

  componentDidMount() {
    
  }

  formatDate = (str) => {
    return str.replace(/,.*$/,"");
  }

  toggleDropdown = () => {
    this.setState(prevState => ({
      isDropdownOpened: !prevState.isDropdownOpened,
    }));
  }

  render() {
    return (
      <div className={s.root}>
        
      </div>
    );
  }
}

function mapStateToProps(state) {
  return {
    isFetching: state.profile.isFetching,
    /*posts: state.posts.posts,*/
  };
}

export default connect(mapStateToProps)(Dashboard);
