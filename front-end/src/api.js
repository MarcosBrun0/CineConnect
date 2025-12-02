// src/api.js
import axios from 'axios';


const api = axios.create({
  baseURL: 'http://localhost:8080', // Exemplo de URL base
  timeout:
    5000, // 5 segundos
  withCredentials: true,
});

export default api;
