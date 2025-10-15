import { Anchor, PasswordInput, Text, TextInput, Stack, Button, Card} from '@mantine/core';
import { useForm } from '@mantine/form';


function UserLogin() {

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
            <form onSubmit={form.onSubmit((values) => console.log(values))}>
            <Stack gap="sm">

            <TextInput
                label="Your email"
                placeholder="youremail@example.com"
                required
                {...form.getInputProps('email')}
                id="your-email"
                />



            <PasswordInput placeholder="Your password"
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
