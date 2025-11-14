import React, { useState, useEffect } from "react";
import axios from 'axios';
import api from "../../../api";
import PaymentBrick from "../../../components/MercadoPago/PaymentBrick";
import { initMercadoPago } from '@mercadopago/sdk-react';


export function PaymentPage() {
    const [pref, setPreference] = useState("id");
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    initMercadoPago(import.meta.env.VITE_MERCADO_PAGO_PUBLIC_KEY);

    // useEffect para chamar a API
    useEffect(() => {
        const fetchPreferences = async () => {
            try {
                setIsLoading(true);
                setError(null);

                const response = await api.get("api/preferences")

                setPreference(response.data);

            } catch (err) {
                console.error("Erro ao buscar página de pagamento:", err);
                setError("Não foi possível carregar o conteúdo. Verifique o servidor e o CORS.");
                setDebugJson(`ERRO: ${err.message}`);

            } finally {
                setIsLoading(false);
            }
        };

        fetchPreferences();
    }, []);

    if (isLoading) {
        return <div className="p-8 text-center text-lg">Carregando...</div>;
    }

    if (error) {
        return <div className="p-8 text-center text-lg text-red-600">Erro: {error}</div>;
    }

    return (
        <div>
        <h1>{pref}</h1>
        <PaymentBrick preference={pref}></PaymentBrick>
        </div>
    );
}

export default PaymentPage;