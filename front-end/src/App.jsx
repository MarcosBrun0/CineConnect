import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';import './App.css'
import {Form} from "@mantine/form";
import UserLogin from "./pages/public/UserLogin"
import UserRegister from "./pages/public/UserRegister";
import Layout from './components/layout';
import UserDashboard from "./pages/private/user/UserDashboard";


function App() {
  const [count, setCount] = useState(0)

  return (
      <Router>
          <Routes>
              <Route path="/user/login" element={<Layout><UserLogin/></Layout>}></Route>
              <Route path="/" element={<UserLogin/>} />
              <Route path="/user/register" element={<UserRegister/>}></Route>
              <Route path="/user/dashboard" element={<UserDashboard/>}></Route>
          </Routes>
              </Router>

  )
}

export default App
