import {Anchor, PasswordInput, Text, TextInput, Stack, Button, Card, createVarsResolver} from '@mantine/core';
import { useForm } from '@mantine/form';
import api from "../../api";


function UserLogin() {

    const handleSubmit = async (values) => {
        try {

            const response = await api.post("/api/login", {
                email: values.email,
                password: values.password,
            },
                {headers: {
                        "Content-Type": "application/json"
                    }})
        } catch (err) {
            console.log(err);
        }
    };


    const form = useForm(
        {
        mode: 'uncontrolled',
        initialValues: { email: '', password: ''},
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

            <div>
            <form onSubmit={form.onSubmit(handleSubmit)}>
            <Stack gap="sm">

            <TextInput
                label="Your email"
                name="email"
                placeholder="youremail@example.com"
                required
                {...form.getInputProps('email')}
                id="your-email"
                />



            <PasswordInput placeholder="Your password"
                                    name="password"
                                   label="Your password"
                                   id="your-password"
                                   required
                                   {...form.getInputProps('password')}                                   pb="xs"
                    />


                <Button
                type="submit"
                radius="md"

                >Login</Button>


                </Stack>
                </form>
            </div>

                <Card.Section
                    withBorder
                    inheritPadding
                    mt="sm"
                    p="sm">

                    <Anchor
                        href="/user/register"
                        //onClick={(event) => event.preventDefault()}
                        pt={2}
                        fw={500}
                        fz="xs">
                        Register
                    </Anchor>

                <Anchor href="#"
                        ml="lg"
                        onClick={(event) => event.preventDefault()} pt={2} fw={500} fz="xs"
                >
                    Forgot your password?
                </Anchor>
                </Card.Section>



            </Card>

        </div>

    );
}

export default UserLogin
