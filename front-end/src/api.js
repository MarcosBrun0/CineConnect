// src/api.js
import axios from 'axios';


const api = axios.create({
  baseURL: 'http://localhost:5432/api', // Exemplo de URL base
  timeout: 5000, // 5 segundos
});

export default api;