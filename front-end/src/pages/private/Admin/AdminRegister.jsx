import RegistrationForm from './RegistrationForm';
import axios from 'axios';

export default function AdminRegistrationPage() {
    const handleAdminSubmit = async (values) => {
        try {
            // Admins can select a role for the new user
            await axios.post('/api/admin/register', values);
            alert('User registered by admin!');
        } catch (error) {
            console.error(error);
            alert('Failed to create user');
        }
    };

    return <RegistrationForm includeRole={true} onSubmit={handleAdminSubmit} />;
}
