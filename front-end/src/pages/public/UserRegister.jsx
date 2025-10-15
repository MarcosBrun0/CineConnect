import { Anchor, PasswordInput, Text, TextInput, Flex, Button, Card} from '@mantine/core';
import { useForm } from '@mantine/form';


function Register() {

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

                    <form onSubmit={form.onSubmit((values) => console.log(values))}>
                        <Text component="label" htmlFor="your-email" size="sm" pb="xs" fw={500}>
                            Your Email
                        </Text>

                        <TextInput
                            placeholder="youremail@example.com"
                            key={form.key('email')}
                            {...form.getInputProps('email')}
                            id="your-email"
                            pb="xs">

                        </TextInput>

                        <Text component="label" htmlFor="your-password" size="sm" pb="xs" fw={500}>
                            Your password

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