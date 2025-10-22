import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';import './App.css'
import {Form} from "@mantine/form";
import UserLogin from "./pages/public/UserLogin"
import UserRegister from "./pages/public/UserRegister";
import Layout from './components/layout';
import Homepage from './pages/public/Homepage';

function App() {
  const [count, setCount] = useState(0)

  return (
      <Router>
          <Routes>

              <Route path="/user/login" element={<Layout><UserLogin/></Layout>}></Route>
              
              <Route path="/user/register" element={<Layout><UserRegister/></Layout>}></Route>

              <Route path="/" element={<Layout><Homepage/></Layout>}></Route>

              

          </Routes>
        </Router>

  )
}


export default App
