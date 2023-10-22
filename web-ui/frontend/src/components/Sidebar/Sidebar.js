import React from 'react';
import {connect} from 'react-redux';
import {withRouter, Link} from 'react-router-dom';

import Icon from '../Icon';
import LinksGroup from './LinksGroup/LinksGroup';

import s from './Sidebar.module.scss';



const Sidebar = () => (
  <nav className={s.sidenav}>
    {/*<header className={s.logo}>
      <Link to="/app/main">
        <img src={logo} alt="Logo" width="100%" height="100%"/>
      </Link>
    </header>*/}
    
      <LinksGroup
        header="Profile"
        headerLink="/app/profile"
        glyph="profile"
      />
      <LinksGroup
        header="Jobs"
        headerLink="/app/jobs"
        glyph="typography"
      />
    
    {/*<nav className={s.sidenav}>
        <a href="index.html" className={s.sidenavLink}>
            
            <img src={profile} alt="" width="21" height="21" class="mb-1"/>
            Profile
        </a>
        <a href="jobs.html" className={s.sidenavLink}>
            <img src="img/suitcase-icon.svg" alt="" width="27" height="25" class="mb-1"/>
            Jobs
        </a>
    </nav>*/}
  </nav>
  
);

function mapStateToProps(store) {
  return {
    sidebarOpened: store.navigation.sidebarOpened,
    sidebarStatic: store.navigation.sidebarStatic,
  };
}

export default withRouter(connect(mapStateToProps)(Sidebar));
