import { useState, useEffect } from 'react';
import api from "../../../api";
import AdminDashboard from "../Admin/AdminDashboard";
import ClientDashboard from "../Client/ClientDashBoard";

function UserDashboard() {
    const [role, setRole] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchUserRole = async () => {
            try {
                const response = await api.get("/api/me");
                setRole(response.data.roleName);
            } catch (err) {
                console.error("Error fetching user role:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchUserRole();
    }, []);

    if (loading) return <p>Loading...</p>;

    switch (role) {
        case "Admin":
            return <AdminDashboard />;
        case "Client":
        case "Cashier":
        case "Employee":
        case "Manager":
            return <ClientDashboard />;
        default:
            return <p>Invalid Role, please try again.</p>;
    }
}

export default UserDashboard;
