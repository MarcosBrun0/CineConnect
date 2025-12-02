import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // <--- 1. Importar useNavigate

const MovieSessions = ({ movieId }) => {
    const navigate = useNavigate(); // <--- 2. Inicializar o hook
    const [sessions, setSessions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchSessions = async () => {
            setLoading(true);
            try {
                // Ajuste a URL se necessário (com ou sem /api dependendo do seu Java)
                const response = await axios.get(`http://localhost:8080/api/sessions/movie/${movieId}`);
                setSessions(response.data);
                setError(null);
            } catch (err) {
                console.error("Erro ao buscar sessões:", err);
                setError("Não foi possível carregar as sessões.");
            } finally {
                setLoading(false);
            }
        };

        if (movieId) {
            fetchSessions();
        }
    }, [movieId]);

    const formatTime = (dateString) => {
        if (!dateString) return '--:--';
        return new Date(dateString).toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
    };

    const formatDate = (dateString) => {
        if (!dateString) return '--/--';
        return new Date(dateString).toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit' });
    };

    // --- 3. Atualizar o Handler ---
    const handleSessionClick = (session) => {
        // Navega para a rota /booking/ID_DA_SESSAO
        navigate(`/booking/${session.sessionId}`);
    };

    if (loading) return <p style={{ color: '#ccc', marginTop: '20px' }}>Carregando horários...</p>;
    if (error) return <p style={{ color: '#ff4d4d', marginTop: '20px' }}>{error}</p>;

    if (sessions.length === 0) {
        return (
            <div style={{ marginTop: '30px', padding: '20px', backgroundColor: '#1a1a1a', borderRadius: '8px', color: '#888' }}>
                <p>Não há sessões disponíveis para este filme no momento.</p>
            </div>
        );
    }

    const sessionsByCinema = sessions.reduce((acc, session) => {
        const cinemaName = session.cinema?.name || "Cinema Desconhecido";
        if (!acc[cinemaName]) {
            acc[cinemaName] = [];
        }
        acc[cinemaName].push(session);
        return acc;
    }, {});

    return (
        <div className="sessions-container" style={{ marginTop: '40px' }}>
            <h2 style={{ color: '#f5c518', marginBottom: '20px', fontSize: '1.5rem' }}>Sessões Disponíveis</h2>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
                {Object.keys(sessionsByCinema).map((cinemaName) => (
                    <div key={cinemaName} style={{ backgroundColor: '#222', padding: '20px', borderRadius: '10px', border: '1px solid #333' }}>
                        <div style={{ marginBottom: '15px' }}>
                            <h3 style={{ margin: 0, color: '#fff', fontSize: '1.2rem' }}>{cinemaName}</h3>
                            <span style={{ fontSize: '0.9rem', color: '#aaa' }}>
                                📍 {sessionsByCinema[cinemaName][0].cinema?.location || "Localização não informada"}
                            </span>
                        </div>

                        <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
                            {sessionsByCinema[cinemaName].map((session) => (
                                <button
                                    key={session.sessionId}
                                    onClick={() => handleSessionClick(session)}
                                    className="session-btn"
                                    style={{
                                        background: '#333',
                                        border: '1px solid #555',
                                        borderRadius: '5px',
                                        padding: '10px 15px',
                                        cursor: 'pointer',
                                        color: 'white',
                                        textAlign: 'center',
                                        minWidth: '100px',
                                        transition: 'background 0.2s'
                                    }}
                                    onMouseOver={(e) => {
                                        e.currentTarget.style.background = '#f5c518';
                                        e.currentTarget.style.color = 'black';
                                    }}
                                    onMouseOut={(e) => {
                                        e.currentTarget.style.background = '#333';
                                        e.currentTarget.style.color = 'white';
                                    }}
                                >
                                    <div style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>
                                        {formatTime(session.timeDate)}
                                    </div>
                                    <div style={{ fontSize: '0.75rem', marginTop: '4px', opacity: 0.8 }}>
                                        {formatDate(session.timeDate)} | {session.language ? session.language.substring(0, 3).toUpperCase() : 'LEG'}
                                    </div>
                                    <div style={{ fontSize: '0.7rem', color: '#ccc' }}>
                                        Sala {session.theater?.id || '?'}
                                    </div>
                                </button>
                            ))}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default MovieSessions;