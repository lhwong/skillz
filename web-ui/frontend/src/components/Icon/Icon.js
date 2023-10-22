import React from 'react';
import PropTypes from 'prop-types';

import icons from './icons';

const Icon = ({glyph}) => (
  icons[glyph] ? <img src={icons[glyph]} alt={`${glyph}-icon`} width="21" height="21" class="mb-1"/> : null
);

Icon.propTypes = {
  glyph: PropTypes.string.isRequired
};

export default Icon;