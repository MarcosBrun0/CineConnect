import { Anchor, PasswordInput, Text, TextInput, Flex, Button, Card} from '@mantine/core';
import { useForm } from '@mantine/form';
import {PasswordStrengthForm} from '../../components/PasswordStrengthForm'
import {DateInput, DatePickerInput} from "@mantine/dates";
import '@mantine/dates/styles.css'
import '@mantine/core/styles.css';
import { IconCalendar } from '@tabler/icons-react';
import dayjs from 'dayjs';
import api from "../../api";
import {redirect} from "react-router-dom";


function Register() {


    const handleSubmit = async (values) => {
        try {
            const response = await api.post("/api/register", {
                email: values.email,
                password: values.password,
                name: values.name,
                birthDate: values.birthDate

            });
            console.warn("User Registered")
        } catch (err) {
            console.log(err);
        }
    };

    const form = useForm(
        {
            mode: 'uncontrolled',
            initialValues: { email: '', password: '', name},
            validate:{
                email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email')
            },

        });

    return (
        <div>
            <Card
                shadow="lg"
                withBorder
                radius="md"
            >
                <Flex
                    justify="space-between"
                    direction='column'
                    p="sm"
                >

                <div>
                    <form onSubmit={form.onSubmit(handleSubmit)}>
                        <Stack gap="sm">
                            <TextInput
                                label="Your Name"
                                placeholder="John Doe"
                                {...form.getInputProps('name')}
                                id="register-name"
                            />
                            <DatePickerInput
                                leftSection={<IconCalendar size={18} stroke={1.5} />}
                                clearable
                                label="Your Birthdate"
                                placeholder="00/00/0000"
                                {...form.getInputProps("birthDate")}
/>
                            <TextInput
                                label="Your email"
                                placeholder="youremail@example.com"
                                {...form.getInputProps('email')}
                                id="register-email"
                            />


                            <PasswordStrengthForm
                            Label="Your Password"
                            {...form.getInputProps('password')}
                            required
                                >
                            </PasswordStrengthForm>

                            <Button
                                type="submit"
                                radius="md"

                            >Register</Button>


                        </Stack>
                    </form>
                </div>

                        </Text>

                        <PasswordInput placeholder="Your password"
                                       id="your-password"
                                       key={form.key('password')}
                                       {...form.getInputProps('password')}                                   pb="xs"
                        />


                        <Button
                            type="submit"
                            radius="md"
                            mt="md"
                        >Login</Button>

                    </form>
                    <Card.Section
                        withBorder
                        inheritPadding
                        mt="sm"
                        p="sm">

                        <Anchor
                            href="#" onClick={(event) => event.preventDefault()} pt={2} fw={500} fz="xs">
                            Register
                        </Anchor>

                        <Anchor href="#"
                                ml="lg"
                                onClick={(event) => event.preventDefault()} pt={2} fw={500} fz="xs"
                        >

                            Forgot your password?
                        </Anchor>
                    </Card.Section>


                </Flex>
            </Card>

        </div>

    );
}

export default Register