import React from 'react';
import { useNavigate } from 'react-router-dom'; // <--- 1. Importar o hook

const API_BASE_URL = "http://localhost:8080";

const MovieCard = ({ movie }) => {
    const navigate = useNavigate(); // <--- 2. Inicializar o hook

    // Constrói a URL da imagem
    const imageUrl = movie.imageFilename
        ? `${API_BASE_URL}/uploads/${movie.imageFilename}`
        : "https://via.placeholder.com/300x450?text=Sem+Imagem";

    // <--- 3. Função de navegação
    const handleCardClick = () => {
        // Assume que seu objeto movie tem um 'id'. Ajuste se for 'movieId'
        navigate(`/movie/${movie.id}`);
    };

    return (
        // <--- 4. Adicionar onClick e style cursor
        <div
            className="movie-card"
            onClick={handleCardClick}
            style={{ cursor: 'pointer' }}
        >
            <div className="poster-container">
                <img
                    src={imageUrl}
                    alt={movie.title}
                    className="movie-poster"
                    onError={(e) => { e.target.src = "https://via.placeholder.com/300x450?text=Indisponível"; }}
                />
            </div>

            <div className="movie-info">
                <h3 className="movie-title" title={movie.title}>{movie.title}</h3>

                <span className="movie-genre">{movie.genre || "Filme"}</span>

                <div className="movie-details">
                    <span>{movie.duration ? `${movie.duration} min` : '--'}</span>

                    <div className="rating-badge">
                        <span className="star">★</span>
                        <span>{movie.rating ? movie.rating : 'N/A'}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MovieCard;