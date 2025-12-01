import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const MovieDetails = () => {
    const { id } = useParams();
    const navigate = useNavigate();

    const [movie, setMovie] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const API_URL = 'http://localhost:8080/api/movie';
    const IMAGE_BASE_URL = 'http://localhost:8080/uploads/';

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await axios.get(`${API_URL}/${id}`);

                // DEBUG: Olhe no Console (F12) para ver como o nome da imagem está chegando
                console.log("DADOS DO FILME RECEBIDOS:", response.data);

                setMovie(response.data);
                setLoading(false);

            } catch (err) {
                console.error("Erro na requisição:", err);

                if (err.response && err.response.status === 404) {
                    setError('Filme não encontrado no sistema.');
                } else {
                    setError('Erro de conexão com o servidor.');
                }
                setLoading(false);
            }
        };

        fetchMovie();
    }, [id]);

    if (loading) {
        return (
            <div className="container" style={{textAlign: 'center', marginTop: '50px'}}>
                <p>Carregando detalhes...</p>
            </div>
        );
    }

    if (error || !movie) {
        return (
            <div className="container" style={{textAlign: 'center', marginTop: '50px'}}>
                <h2>Ops!</h2>
                <p>{error}</p>
                <button className="back-btn" onClick={() => navigate('/')}>&larr; Voltar ao início</button>
            </div>
        );
    }

    // LÓGICA DE CORREÇÃO:
    // Tenta ler 'image_filename' (snake_case) OU 'imageFilename' (camelCase)
    // Isso garante que funcione independente da configuração do seu Java.
    const imageName = movie.image_filename || movie.imageFilename;

    return (
        <div className="container">
            <button className="back-btn" onClick={() => navigate(-1)}>
                &larr; Voltar
            </button>

            <div className="movie-detail-card">
                <div className="detail-image-container">
                    <img
                        src={imageName ? `${IMAGE_BASE_URL}${imageName}` : "https://img.freepik.com/fotos-gratis/um-belo-nascer-de-sol-na-praia-sob-um-ceu-azul_181624-26939.jpg?semt=ais_hybrid&w=740&q=80"}
                        alt={`Poster de ${movie.title}`}
                        className="detail-poster"
                        onError={(e) => {
                            console.log("Falha ao carregar imagem:", e.target.src);
                            e.target.src = 'https://via.placeholder.com/350x500?text=Erro+Imagem';
                        }}
                    />
                </div>

                <div className="detail-content">
                    <span className="movie-genre detail-genre">{movie.genre}</span>

                    <h1 className="detail-title">{movie.title}</h1>

                    <div className="detail-meta">
                        <div className="rating-badge" style={{fontSize: '1.1rem'}}>
                            <span className="star">★</span>
                            <span>{movie.rating ? Number(movie.rating).toFixed(1) : 'N/A'}</span>
                        </div>
                        <span>|</span>
                        <div className="duration">
                            ⏱ {movie.duration} min
                        </div>
                    </div>

                    <div className="synopsis-section">
                        <span className="detail-synopsis-label">Sinopse</span>
                        <p className="detail-synopsis-text">
                            {movie.synopsis}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MovieDetails;