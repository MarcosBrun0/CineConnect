import {
    IconBook,
    IconChartPie3,
    IconChevronDown,
    IconCode,
    IconCoin,
    IconFingerprint,
    IconNotification,
} from "@tabler/icons-react";
import {
    Anchor,
    Box,
    Burger,
    Button,
    Center,
    Collapse,
    Divider,
    Drawer,
    Group,
    HoverCard,
    ScrollArea,
    SimpleGrid,
    Text,
    ThemeIcon,
    UnstyledButton,
    useMantineTheme,
} from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";


const mockdata = [
    {
        icon: IconCode,
        title: "Open source",
        description: "This Pokémon’s cry is very loud and distracting",
    },
    {
        icon: IconCoin,
        title: "Free for everyone",
        description: "The fluid of Smeargle’s tail secretions changes",
    },
    {
        icon: IconBook,
        title: "Documentation",
        description: "Yanma is capable of seeing 360 degrees without",
    },
    {
        icon: IconFingerprint,
        title: "Security",
        description: "The shell’s rounded shape and the grooves on its.",
    },
    {
        icon: IconChartPie3,
        title: "Analytics",
        description: "This Pokémon uses its flying ability to quickly chase",
    },
    {
        icon: IconNotification,
        title: "Notifications",
        description: "Combusken battles with the intensely hot flames it spews",
    },
];

export function HeaderMegaMenu() {
    const [drawerOpened, { toggle: toggleDrawer, close: closeDrawer }] =
        useDisclosure(false);
    const [linksOpened, { toggle: toggleLinks }] = useDisclosure(false);
    const theme = useMantineTheme();

    const links = mockdata.map((item) => (
        <UnstyledButton
            key={item.title}
            className="w-full rounded-md px-[var(--mantine-spacing-md)] py-[var(--mantine-spacing-xs)] hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-7))] transition"
        >
            <Group wrap="nowrap" align="flex-start">
                <ThemeIcon size={34} variant="default" radius="md">
                    <item.icon size={22} color={theme.colors.blue[6]} />
                </ThemeIcon>
                <div>
                    <Text size="sm" fw={500}>
                        {item.title}
                    </Text>
                    <Text size="xs" c="dimmed">
                        {item.description}
                    </Text>
                </div>
            </Group>
        </UnstyledButton>
    ));

    return (
        <Box pb={120}>
            <header
                className="
          h-[60px]
          flex items-center justify-between
          border-b border-[color:light-dark(var(--mantine-color-gray-3),var(--mantine-color-dark-4))]
          px-[var(--mantine-spacing-md)]
        "
            >
                <Group justify="space-between" h="100%" className="flex-1">


                    {/* Desktop navigation */}
                    <Group h="100%" gap={0} visibleFrom="sm">
                        <a
                            href="#"
                            className="
                flex items-center h-full px-[var(--mantine-spacing-md)]
                text-[var(--mantine-font-size-sm)] font-medium no-underline
                text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
                hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
                transition
              "
                        >
                            Home
                        </a>

                        <HoverCard
                            width={600}
                            position="bottom"
                            radius="md"
                            shadow="md"
                            withinPortal
                        >
                            <HoverCard.Target>
                                <a
                                    href="#"
                                    className="
                    flex items-center h-full px-[var(--mantine-spacing-md)]
                    text-[var(--mantine-font-size-sm)] font-medium no-underline
                    text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
                    hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
                    transition
                  "
                                >
                                    <Center inline>
                                        <Box component="span" mr={5}>
                                            Features
                                        </Box>
                                        <IconChevronDown
                                            size={16}
                                            color={theme.colors.blue[6]}
                                        />
                                    </Center>
                                </a>
                            </HoverCard.Target>

                            <HoverCard.Dropdown style={{ overflow: "hidden" }}>
                                <Group justify="space-between" px="md">
                                    <Text fw={500}>Features</Text>
                                    <Anchor href="#" fz="xs">
                                        View all
                                    </Anchor>
                                </Group>

                                <Divider my="sm" />

                                <SimpleGrid cols={2} spacing={0}>
                                    {links}
                                </SimpleGrid>

                                <div
                                    className="
                    bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-7))]
                    mt-[var(--mantine-spacing-sm)]
                    -mx-[var(--mantine-spacing-md)]
                    px-[calc(var(--mantine-spacing-md)*2)]
                    pb-[var(--mantine-spacing-xl)]
                    border-t border-[color:light-dark(var(--mantine-color-gray-1),var(--mantine-color-dark-5))]
                  "
                                >
                                    <Group justify="space-between">
                                        <div>
                                            <Text fw={500} fz="sm">
                                                Get started
                                            </Text>
                                            <Text size="xs" c="dimmed">
                                                Their food sources have decreased, and their numbers
                                            </Text>
                                        </div>
                                        <Button variant="default">Get started</Button>
                                    </Group>
                                </div>
                            </HoverCard.Dropdown>
                        </HoverCard>

                        <a
                            href="#"
                            className="
                flex items-center h-full px-[var(--mantine-spacing-md)]
                text-[var(--mantine-font-size-sm)] font-medium no-underline
                text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
                hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
                transition
              "
                        >
                            Learn
                        </a>
                        <a
                            href="#"
                            className="
                flex items-center h-full px-[var(--mantine-spacing-md)]
                text-[var(--mantine-font-size-sm)] font-medium no-underline
                text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
                hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
                transition
              "
                        >
                            Academy
                        </a>
                    </Group>

                    {/* Right-side buttons */}
                    <Group visibleFrom="sm">
                        <Button variant="default">Log in</Button>
                        <Button>Sign up</Button>
                    </Group>

                    <Burger opened={drawerOpened} onClick={toggleDrawer} hiddenFrom="sm" />
                </Group>
            </header>

            {/* Drawer (mobile menu) */}
            <Drawer
                opened={drawerOpened}
                onClose={closeDrawer}
                size="100%"
                padding="md"
                title="Navigation"
                hiddenFrom="sm"
                zIndex={1000000}
            >
                <ScrollArea h="calc(100vh - 80px)" mx="-md">
                    <Divider my="sm" />
                    <a
                        href="#"
                        className="
              block h-[42px] w-full px-[var(--mantine-spacing-md)] flex items-center
              text-[var(--mantine-font-size-sm)] font-medium no-underline
              text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
              hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
              transition
            "
                    >
                        Home
                    </a>
                    <UnstyledButton
                        onClick={toggleLinks}
                        className="
              block h-[42px] w-full px-[var(--mantine-spacing-md)] flex items-center
              text-[var(--mantine-font-size-sm)] font-medium
              text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
              hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
              transition
            "
                    >
                        <Center inline>
                            <Box component="span" mr={5}>
                                Features
                            </Box>
                            <IconChevronDown size={16} color={theme.colors.blue[6]} />
                        </Center>
                    </UnstyledButton>

                    <Collapse in={linksOpened}>{links}</Collapse>

                    <a
                        href="#"
                        className="
              block h-[42px] w-full px-[var(--mantine-spacing-md)] flex items-center
              text-[var(--mantine-font-size-sm)] font-medium no-underline
              text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
              hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
              transition
            "
                    >
                        Learn
                    </a>
                    <a
                        href="#"
                        className="
              block h-[42px] w-full px-[var(--mantine-spacing-md)] flex items-center
              text-[var(--mantine-font-size-sm)] font-medium no-underline
              text-[color:light-dark(var(--mantine-color-black),var(--mantine-color-white))]
              hover:bg-[color:light-dark(var(--mantine-color-gray-0),var(--mantine-color-dark-6))]
              transition
            "
                    >
                        Academy
                    </a>

                    <Divider my="sm" />

                    <Group justify="center" grow pb="xl" px="md">
                        <Button variant="default">Log in</Button>
                        <Button>Sign up</Button>
                    </Group>
                </ScrollArea>
            </Drawer>
        </Box>
    );
}
