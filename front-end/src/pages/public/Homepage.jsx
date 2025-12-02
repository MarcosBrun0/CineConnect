import React, { useEffect, useState } from 'react';
import axios from 'axios';

import MovieCard from "../../components/MovieCard"; // Garante que o CSS novo está sendo aplicado

const HomePage = () => {
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get('http://localhost:8080/api/movie',{withCredentials: true})
            .then(response => {

                setMovies(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.error("Erro:", err);
                setLoading(false);
            });
    }, []);

    return (
        <div className="container">
            <header className="header">
                <h1>Catálogo de Filmes</h1>
                <p>Veja os lançamentos disponíveis</p>
            </header>

            {loading ? (
                <p style={{textAlign: 'center', color: '#666'}}>Carregando catálogo...</p>
            ) : (
                <>
                    {movies.length === 0 ? (
                        <div style={{textAlign: 'center', padding: '40px', backgroundColor: '#fff', borderRadius: '8px'}}>
                            <h3>Nenhum filme encontrado</h3>
                            <p style={{color: '#999'}}>Cadastre filmes no sistema para vê-los aqui.</p>
                        </div>
                    ) : (
                        <div className="movies-grid">
                            {movies.map((movie) => (

                                <MovieCard key={movie.id} movie={movie} />
                            ))}
                        </div>
                    )}
                </>
            )}
        </div>
    );
};

export default HomePage;