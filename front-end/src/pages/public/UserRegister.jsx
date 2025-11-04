import { Anchor, PasswordInput, Text, TextInput, Stack, Button, Card} from '@mantine/core';
import { useForm } from '@mantine/form';
import {PasswordStrengthForm} from '../../components/PasswordStrengthForm'
import {DateInput, DatePickerInput} from "@mantine/dates";
import '@mantine/dates/styles.css'
import '@mantine/core/styles.css';
import { IconCalendar } from '@tabler/icons-react';
import dayjs from 'dayjs';
import api from "../../api";
import {redirect} from "react-router-dom";



function UserRegister() {


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
            initialValues: { email: '', password: '', name:'',birthDate:null},
            validate:{
                email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
                birthDate: (value) => {
                    if (!value) return "Please select your birth date";
                    const age = dayjs().diff(dayjs(value), "year");
                    return age < 18 ? "You must be at least 18 years old" : null;
                },
                name: (value) =>{
                    if (!(/^[a-zA-Z\s]+$/.test(value))) return "invalid Name"
    }
            },

        });
    return (
        <div>
            <Card
                shadow="lg"
                withBorder
                radius="md"
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

                <Card.Section
                    withBorder
                    inheritPadding
                    mt="sm"
                    p="sm">


                    <Anchor href="/user/login"
                            ml="lg"
                            pt={2}
                            fw={500}
                            fz="xs"
                            //onClick={(event) => event.preventDefault()}
                    >
                        Already have an account?
                    </Anchor>
                </Card.Section>



            </Card>

        </div>

    );
}

export default UserRegister
