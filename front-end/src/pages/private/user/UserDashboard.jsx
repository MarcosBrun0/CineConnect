import { Card } from '@mantine/core';
import { useState, useEffect } from 'react';
import api from "../../../api";

function UserDashboard() {
    const [users, setUsers] = useState(null);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await api.get("/api/usuarios", {
                    withCredentials: true, // ensures cookies are sent
                });
                setUsers(response.data);
            } catch (error) {
                console.error("Error fetching users:", error);
            }
        };

        fetchUsers();
    }, []);

    return (
        <div>
            <Card shadow="lg" withBorder radius="md">
                <h1>User List</h1>
                {users ? (
                    <ul>
                        {users.map((user) => (
                            <li key={user.id}>
                                {user.name} - {user.email}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>Loading...</p>
                )}
            </Card>
        </div>
    );
}

export default UserDashboard;
