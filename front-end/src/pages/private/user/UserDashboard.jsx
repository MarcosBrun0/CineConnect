import {Card, Paper, Stack} from '@mantine/core';
import { useState, useEffect } from 'react';
import api from "../../../api";
import {Calendar} from "@mantine/dates";

function UserDashboard() {
    const [user, setUser] = useState(null);

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

    return (
        <div>
            <Card shadow="lg" withBorder radius="md">
                <h2>UserDashboard</h2>
                {user ? (
                    <h2>Hello {user.name}!</h2>
                ) : (
                    <p>Loading...</p>
                )}
            </Card>
            <Stack gap="sm">
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

            </Stack>

        </div>
    );
}

export default UserDashboard;
