// import React, { useState, useEffect } from "react";
// import axios from 'axios';
// // A importação do Carousel deve estar correta.
// import { Carousel } from "@material-tailwind/react";
// import { useParams } from 'react-router-dom';
// import api from "../api";
// // Defina a URL completa da API
// const API_URL = 'http://localhost:8080/api/movie';
//
// export function ConfirmationPage() {
//     const [TransactionResult, SetTransactionResult] = useState({});
//     const [isLoading, setIsLoading] = useState(true);
//     const [error, setError] = useState(null);
//     const { id } = useParams();
//     const {payment_intent} = useParams();
//     // 1. Estado para exibir o JSON bruto na tela (para debug)
//     const [debugJson, setDebugJson] = useState('Aguardando dados...');
//
//     // useEffect para chamar a API
//     useEffect(() => {
//         const fetchMovies = async () => {
//             try {
//                 setIsLoading(true);
//                 setError(null);
//                 const response = await api.post("api/stripe/confirm-payment", payment_intent);
//                 // 2. Log no console e armazena para exibição na tela
//                 setMovies(response.data);
//                 console.log("Dados da API recebidos:", response.data);
//                 setDebugJson(JSON.stringify(response.data, null, 2));
//
//             } catch (err) {
//                 console.error("Erro ao buscar filmes:", err);
//                 setError("Não foi possível carregar o conteúdo. Verifique o servidor e o CORS.");
//                 setMovies([]);
//                 setDebugJson(`ERRO: ${err.message}`);
//
//             } finally {
//                 setIsLoading(false);
//             }
//         };
//
//         fetchMovies();
//     }, []);
//
//     // --- Renderização de Estado ---
//     if (isLoading) {
//         return <div className="p-8 text-center text-lg">Carregando filmes...</div>;
//     }
//
//     if (error) {
//         return <div className="p-8 text-center text-lg text-red-600">Erro: {error}</div>;
//     }
//
//     if (movies.length === 0) {
//         return (
//             <div className="p-4">
//                 <div className="p-8 text-center text-lg text-gray-500">Nenhum filme encontrado.</div>
//                 {/* Bloco de Debug para NENHUM FILME */}
//                 <h3 className="text-xl font-semibold mt-6 mb-2">JSON Recebido (Debug)</h3>
//                 <pre className="bg-gray-100 p-3 rounded overflow-auto text-sm">{debugJson}</pre>
//             </div>
//         );
//     }
//
//     // --- Renderização do Carrossel com os dados ---
//     return (
//         <div className="p-4">
//             <h2 className="text-2xl font-bold mb-4">Em Destaque</h2>
//
//             {/* 3. Bloco de Debug Reintroduzido */}
//             <h3 className="text-xl font-semibold mt-6 mb-2">JSON Recebido (Debug)</h3>
//             <pre className="bg-gray-100 p-3 rounded overflow-auto text-sm">
//         {debugJson}
//       </pre>
//
//             <h3 className="text-xl font-bold mt-6 mb-2">Carrossel de Filmes</h3>
//
//             {/* 4. Carrossel com a navegação personalizada CORRIGIDA */}
//             <Carousel
//                 className="rounded-xl h-96"
//                 loop={true}
//                 // Este bloco de navegação é crucial para o carrossel funcionar corretamente
//                 // e exibir os indicadores (bolinhas)
//                 navigation={({ setActiveIndex, activeIndex, length }) => (
//                     <div className="absolute bottom-4 left-2/4 z-50 flex -translate-x-2/4 gap-2">
//                         {new Array(length).fill("").map((_, i) => (
//                             <span
//                                 key={i}
//                                 className={`block h-3 w-3 cursor-pointer rounded-full transition-colors content-[''] ${
//                                     activeIndex === i ? "bg-white" : "bg-white/50"
//                                 }`}
//                                 onClick={() => setActiveIndex(i)}
//                             />
//                         ))}
//                     </div>
//                 )}
//             >
//                 {/* MAPEAR OS DADOS NO CARROSSEL */}
//                 {movies.map((movie) => (
//                     <div key={movie.movieId} className="relative h-full w-full">
//                         <img
//                             // Usando o caminho 'img' do seu JSON
//                             src={"https://images.ctfassets.net/hrltx12pl8hq/4f6DfV5DbqaQUSw0uo0mWi/6fbcf889bdef65c5b92ffee86b13fc44/shutterstock_376532611.jpg?fit=fill&w=600&h=400"}
//                             alt={movie.name}
//                             className="h-full w-full object-cover"
//                         />
//
//                         {/* Overlay com Informações do Filme */}
//                         <div className="absolute inset-0 grid h-full w-full items-end bg-black/50 p-6">
//                             <div className="text-white">
//                                 <h4 className="text-3xl font-bold mb-1">{movie.name}</h4>
//                                 <p className="text-xl">Nota: {movie.rating} / 10</p>
//                                 <p className="text-md">Duração: {movie.duration} min</p>
//                             </div>
//                         </div>
//                     </div>
//                 ))}
//             </Carousel>
//         </div>
//     );
// }
//
// export default ConfirmationPage;