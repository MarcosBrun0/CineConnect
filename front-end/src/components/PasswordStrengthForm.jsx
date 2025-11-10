import {IconCheck, IconX} from '@tabler/icons-react';
import {Box, Center, Group, PasswordInput, Progress, Text} from '@mantine/core';
import {useState, useEffect} from 'react';

function PasswordRequirement({ meets, label }) {
    return (
        <Text component="div" c={meets ? 'teal' : 'red'} mt={5} size="sm">
            <Center inline>
                {meets ? <IconCheck size={14} stroke={1.5} /> : <IconX size={14} stroke={1.5} />}
                <Box ml={7}>{label}</Box>
            </Center>
        </Text>
    );
}

const requirements = [
    { re: /[0-9]/, label: 'Includes number' },
    { re: /[a-z]/, label: 'Includes lowercase letter' },
    { re: /[A-Z]/, label: 'Includes uppercase letter' },
    { re: /[$&+,:;=?@#|'<>.^*()%!-]/, label: 'Includes special symbol' },
];

function getStrength(password) {
    let multiplier = password.length > 5 ? 0 : 1;

    requirements.forEach((requirement) => {
        if (!requirement.re.test(password)) {
            multiplier += 1;
        }
    });

    return Math.max(100 - (100 / (requirements.length + 1)) * multiplier, 0);
}

export default function PasswordStrengthForm({ value: parentValue, onChange }) {
    const [value, setValue] = useState(parentValue || '');

    // Update parent form whenever internal value changes
    useEffect(() => {
        onChange({ currentTarget: { value } });
    }, [value, onChange]);

    const strength = getStrength(value);
    const checks = requirements.map((req, i) => (
        <PasswordRequirement key={i} label={req.label} meets={req.re.test(value)} />
    ));
    const bars = Array(4).fill(0).map((_, index) => (
        <Progress
            styles={{ section: { transitionDuration: '0ms' } }}
            value={
                value.length > 0 && index === 0 ? 100 : strength >= ((index + 1) / 4) * 100 ? 100 : 0
            }
            color={strength > 80 ? 'teal' : strength > 50 ? 'yellow' : 'red'}
            key={index}
            size={4}
        />
    ));

    return (
        <div>
            <PasswordInput
                value={value}
                onChange={(e) => setValue(e.currentTarget.value)}
                placeholder="Your password"
                label="Password"
                required
            />

            <Group gap={5} grow mt="xs" mb="md">
                {bars}
            </Group>

            <PasswordRequirement label="Has at least 6 characters" meets={value.length > 5} />
            {checks}
        </div>
    );
}
