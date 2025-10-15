import React from 'react';
import {HeaderMegaMenu} from './header';


// O 'children' é o conteúdo da página que será renderizado dentro do layout
const Layout = ({ children }) => { 
  return (
    <div>
      <HeaderMegaMenu />
      <main>{children}</main>

    </div>
  );
};

export default Layout;