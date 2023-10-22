import React from 'react';
import cx from 'classnames';
import { Switch, Route, withRouter } from 'react-router';

import s from './Layout.module.scss';
import Header from '../Header';
import Footer from '../Footer';
import Sidebar from '../Sidebar';

// Dashboard component is loaded directly as an example of server side rendering
import Dashboard from '../../pages/dashboard'
import Profile from '../../pages/profile'

class Layout extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      sidebarOpen: false,
    };
  }

  render() {
    return (
      <div className={s.root}>
        <Header
            sidebarToggle={() =>
              this.setState({
                sidebarOpen: !this.state.sidebarOpen,
              })
            }
          />
        <Sidebar />
        {/*<div
          className={cx(s.wrap, {[s.sidebarOpen]: this.state.sidebarOpen})}
        >
          
          <main className={s.content}>*/}
            <Switch>
              <Route path="/app/main" exact component={Dashboard} />
              <Route path="/app/profile" exact component={Profile} />
            </Switch>
          {/*</main>
          <Footer />
          </div>*/}
      </div>
    );
  }
}

export default withRouter(Layout);

