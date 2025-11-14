import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';import './App.css'
import {Form} from "@mantine/form";
import UserLogin from "./pages/public/UserLogin"
import UserRegister from "./pages/public/UserRegister";
import Layout from './components/layout';
import UserDashboard from "./pages/private/user/UserDashboard";

import Homepage from './pages/public/Homepage';
import AdminDashboard from "./pages/private/Admin/AdminDashboard";
import PaymentBrick from "./components/MercadoPago/PaymentBrick";
import { initMercadoPago } from '@mercadopago/sdk-react';
function App() {
  const [count, setCount] = useState(0)


    initMercadoPago(import.meta.env.VITE_MERCADO_PAGO_PUBLIC_KEY);

  return (
      <Router>
          <Routes>

              <Route path="/login" element={<Layout><UserLogin/></Layout>}></Route>
              <Route path="/register" element={<Layout><UserRegister/></Layout>}></Route>
              <Route path="/user/dashboard" element={<Layout><UserDashboard/></Layout>}></Route>
              <Route path="/" element={<Layout><Homepage/></Layout>}></Route>
              <Route path="/payment" element={<Layout><PaymentBrick/></Layout>}></Route>


          </Routes>
        </Router>

  )
}


export default App
