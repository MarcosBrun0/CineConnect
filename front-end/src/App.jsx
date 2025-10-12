import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';import './App.css'
import { Button } from "@mantine/core";


function Example(){
    return(
        <div>
        <h1>Example!</h1>
        <Button>Example</Button>
        </div>
    )

}
function App() {
  const [count, setCount] = useState(0)

  return (
      <Router>
          <Routes>
              <Route path="/" element=<Example/> />
          </Routes>
              </Router>

  )
}

export default App
