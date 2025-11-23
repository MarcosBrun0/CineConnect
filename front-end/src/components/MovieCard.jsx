import React from 'react';
// Não esqueça de importar o CSS no arquivo pai ou aqui
// import './App.css';

const API_BASE_URL = "http://localhost:8080";

const MovieCard = ({ movie }) => {

    // Constrói a URL
    const imageUrl = movie.imageFilename
        ? `${API_BASE_URL}/uploads/${movie.imageFilename}`
        : "https://via.placeholder.com/300x450?text=Sem+Imagem";

    return (
        <div className="movie-card">
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

                {/* Etiqueta de Gênero */}
                <span className="movie-genre">{movie.genre || "Filme"}</span>

                {/* Detalhes do rodapé do card */}
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