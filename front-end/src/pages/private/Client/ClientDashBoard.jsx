import { Card, Paper, Stack, Button } from '@mantine/core';
import { useState, useEffect } from 'react';
import api from "../../../api";
import { Calendar } from "@mantine/dates";
import { useNavigate } from "react-router-dom";

function ClientDashboard() {
    const [user, setUser] = useState(null);
    const nav = useNavigate();

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await api.get("/api/me", {
                    withCredentials: true, // ensures cookies are sent
                });
                setUser(response.data);
            } catch (error) {
                console.error("Error fetching user:", error);
            }
        };

        fetchUser();
    }, []);

    const goToPurchases = () => {
        nav("/user/purchases");
    };

    return (
        <div>
            <Stack gap="sm">
                <Card shadow="sm" withBorder radius="md">
                    <div className="flex justify-between items-center">
                        <h2>User Dashboard</h2>
                        <Button onClick={goToPurchases}>My Purchases</Button>
                    </div>
                </Card>

                <div>
                    {user ? (
                        <h2>Hello {user.name}!</h2>
                    ) : (
                        <p>Loading...</p>
                    )}
                </div>
                {user ? (

                    <h2>Your Birthdate is {user.birth_date}</h2>
                ) : (
                    <p>Loading...</p>
                )}

                {user ? (

                    <h2>Your access level is: {user.roleName}</h2>
                ) : (
                    <p>Loading...</p>
                )}
            </Stack>

        </div>
    );
}

export default ClientDashboard;
