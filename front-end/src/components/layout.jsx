import React from 'react';

import { FooterSocial } from './Footer';
import { HeaderSimples } from './HeaderSimples';

// O 'children' é o conteúdo da página que será renderizado dentro do layout
const Layout = ({ children }) => { 
  return (
    <div className="layout-container">
      <header><HeaderSimples/></header>
      <main>{children}</main>
      <footer><FooterSocial /></footer>
    </div>
  );
};

export default Layout;