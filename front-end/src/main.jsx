import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { MantineProvider } from '@mantine/core';
import '@mantine/core/styles.css';
import './index.css'
import {CartProvider} from "./context/CartContext";


createRoot(document.getElementById('root')).render(
  <StrictMode>

      <MantineProvider>
          <CartProvider>
          <App />
          </CartProvider>
      </MantineProvider>
  </StrictMode>
)
