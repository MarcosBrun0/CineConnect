import { useState, useEffect } from 'react';
import { Card, Stack, Text, Badge, Group, Image } from "@mantine/core";
import api from "../../api";

function UserPurchases() {
    const [user, setUser] = useState(null);
    const [purchases, setPurchases] = useState(null)

    useEffect(() => {
        const fetchPurchases = async () => {
            try {
                const response = await api.get("/api/me/purchases", {
                    withCredentials: true, // ensures cookies are sent
                });
                setPurchases(response.data);
            } catch (error) {
                console.error("Error fetching purchases:", error);
            }
        };

        fetchPurchases();
    }, []);

    return (
        <Stack spacing="xl" p="md">
            <h1 style={{ fontSize: '2rem', fontWeight: 'bold', marginBottom: '1rem' }}>My Purchases</h1>
            {purchases ? (
                purchases.length === 0 ? (
                    <Text c="dimmed">You haven't made any purchases yet.</Text>
                ) : (
                    purchases.map((purchase) => (
                        <Card key={purchase.id} shadow="sm" padding="lg" radius="md" withBorder>
                            <Card.Section withBorder inheritPadding py="xs">
                                <Group position="apart">
                                    <Group>
                                        <Text fw={500}>Order #{purchase.id.substring(0, 8)}</Text>
                                        <Badge color={purchase.status === 'PAID' || purchase.status === 'succeeded' ? 'green' : 'yellow'}>
                                            {purchase.status}
                                        </Badge>
                                    </Group>
                                    <Text c="dimmed" size="sm">
                                        {new Date(purchase.createdAt).toLocaleDateString()} {new Date(purchase.createdAt).toLocaleTimeString()}
                                    </Text>
                                </Group>
                            </Card.Section>

                            <Stack mt="md" spacing="sm">
                                {purchase.items.map((item, index) => (
                                    <Group key={index} align="flex-start" noWrap>
                                        <Image
                                            src={item.imageUrl || "https://placehold.co/40x40?text=No+Image"}
                                            width={40}
                                            height={40}
                                            radius="md"
                                            fit="contain"
                                            alt={item.name}
                                        />
                                        <div style={{ flex: 1 }}>
                                            <Text fw={500}>{item.name}</Text>
                                            <Text size="sm" c="dimmed">
                                                {item.quantity} x ${item.priceAtPurchase.toFixed(2)}
                                            </Text>
                                            {item.addons && item.addons.length > 0 && (
                                                <Stack spacing={2} mt={4}>
                                                    {item.addons.map((addon, idx) => (
                                                        <Text key={idx} size="xs" c="dimmed" pl="xs" style={{ borderLeft: '2px solid #eee' }}>
                                                            + {addon.name} (${addon.priceAtPurchase.toFixed(2)})
                                                        </Text>
                                                    ))}
                                                </Stack>
                                            )}
                                        </div>
                                        <Text fw={500}>
                                            ${((item.priceAtPurchase * item.quantity) + (item.addons ? item.addons.reduce((sum, a) => sum + a.priceAtPurchase, 0) : 0)).toFixed(2)}
                                        </Text>
                                    </Group>
                                ))}
                            </Stack>

                            <Card.Section withBorder inheritPadding py="xs" mt="md">
                                <Group position="right">
                                    <Text size="lg" fw={700}>Total: ${purchase.totalAmount.toFixed(2)}</Text>
                                </Group>
                            </Card.Section>
                        </Card>
                    ))
                )
            ) : (
                <Text>Loading purchases...</Text>
            )}
        </Stack>
    );
}
export default UserPurchases;
