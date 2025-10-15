import React from 'react';
import {HeaderMegaMenu} from './header';


// O 'children' é o conteúdo da página que será renderizado dentro do layout
const Layout = ({ children }) => { 
  return (
    <>
      <HeaderMegaMenu />
      <main>{children}</main>

    </>
  );
};

export default Layout;