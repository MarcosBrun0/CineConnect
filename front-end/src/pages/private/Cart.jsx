import { Anchor, TextInput, Flex, Button, Card, Stack } from '@mantine/core';
import { useForm } from '@mantine/form';
import { DatePickerInput } from "@mantine/dates";
import '@mantine/dates/styles.css';
import '@mantine/core/styles.css';
import { IconCalendar } from '@tabler/icons-react';
import api from "../../api";
import PasswordStrengthForm from "../../components/PasswordStrengthForm";

function Cart() {
    const form = useForm({
        mode: 'uncontrolled',
        initialValues: {
            email: '',
            password: '',
            name: '',
            birth_date: '',
        },
        validate: {
            email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
        },
    });

    const handleSubmit = async (values) => {
        try {
            const response = await api.post("/api/register", values);
            console.warn("User Registered");
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <Card shadow="lg" withBorder radius="md">
            <Flex direction="column" p="sm">
                <form onSubmit={form.onSubmit(handleSubmit)}>
                    <Stack gap="sm">
                        <TextInput
                            label="Your Name"
                            placeholder="John Doe"
                            {...form.getInputProps('name')}
                        />
                        <DatePickerInput
                            leftSection={<IconCalendar size={18} stroke={1.5} />}
                            clearable
                            label="Your Birthdate"
                            placeholder="00/00/0000"
                            {...form.getInputProps('birth_date')}
                        />
                        <TextInput
                            label="Your email"
                            placeholder="youremail@example.com"
                            {...form.getInputProps('email')}
                        />
                        <PasswordStrengthForm
                            value={form.values.password}
                            onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                            required
                        />


                        <Button type="submit" radius="md">Register</Button>
                    </Stack>
                </form>

                <Card.Section withBorder inheritPadding mt="sm" p="sm">
                    <Anchor
                        href="#"
                        onClick={(event) => event.preventDefault()}
                        pt={2}
                        fw={500}
                        fz="xs"
                    >
                        Already have an account?
                    </Anchor>
                </Card.Section>
            </Flex>
        </Card>
    );
}

export default Cart;