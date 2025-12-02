import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom'; import './App.css'
import { Form } from "@mantine/form";
import UserLogin from "./pages/public/UserLogin"
import UserRegister from "./pages/public/UserRegister";
import Layout from './components/layout';
import UserDashboard from "./pages/private/user/UserDashboard";

import Homepage from './pages/public/Homepage';
import AdminDashboard from "./pages/private/Admin/AdminDashboard";
import CheckoutForm from "./components/CheckoutForm";
import StorePage from "./pages/private/store/StorePage";
import Cart from "./pages/private/Cart";
import PaymentPage from "./pages/private/payment/PaymentPage";
import CartDisplay from "./pages/CartDisplay";
import Purchases from "./pages/private/Purchases";
import MovieDetails from "./components/MovieDetails";
import BookingPage from "./pages/public/BookingPage";

function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout><Homepage /></Layout>}></Route>
        <Route path="/login" element={<Layout><UserLogin /></Layout>}></Route>
        <Route path="/register" element={<Layout><UserRegister /></Layout>}></Route>
        <Route path="/user/dashboard" element={<Layout><UserDashboard /></Layout>}></Route>

        {/* Store & Payments */}
        <Route path="/store" element={<Layout><StorePage /><CartDisplay /></Layout>}></Route>
        <Route path="/checkout" element={<Layout><PaymentPage /></Layout>}></Route>
        <Route path="/user/purchases" element={<Layout><Purchases /></Layout>}></Route>

        {/* Movies & Booking */}
        <Route path="/movie/:id" element={<MovieDetails />} />
        <Route path="/booking/:sessionId" element={<BookingPage />} />


      </Routes>
    </Router>

  )
}


export default App
