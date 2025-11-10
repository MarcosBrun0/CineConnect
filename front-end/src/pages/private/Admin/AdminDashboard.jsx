import {Button, Card, Stack} from '@mantine/core';
import { useState, useEffect } from 'react';
import api from '../../../api';

function AdminDashboard() {
    const [users, setUsers] = useState([]);
    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await api.get('/api/usuarios', {
                    withCredentials: true,
                });
                setUsers(response.data);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };

        fetchUsers();
    }, []);

    return (

        <div>
            <Stack>
            <Card>
            <h2>Admin Dashboard</h2>
            </Card>

            {users.length === 0 ? (
                <p>Loading...</p>
            ) : (
                <Stack gap="sm">
                    {users.map((user) => (
                        <Card key={user.id} shadow="lg" withBorder radius="md">
                            <h3>Hello {user.name}!</h3>
                            <p>Birthdate: {user.birth_date}</p>
                        </Card>
                    ))}
                </Stack>
            )}
            </Stack>
        </div>
    );
}

export default AdminDashboard;
