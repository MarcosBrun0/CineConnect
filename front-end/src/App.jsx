import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';import './App.css'
import {Form} from "@mantine/form";
import UserLogin from "./pages/public/UserLogin"


function App() {
  const [count, setCount] = useState(0)

  return (
      <Router>
          <Routes>
              <Route path="/user/login" element={<UserLogin/>}></Route>
              <Route path="/" element={<UserLogin/>} />
              <Route path="/user/register"></Route>
          </Routes>
              </Router>

  )
}

export default App
