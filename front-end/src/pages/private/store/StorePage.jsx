import { useEffect, useState } from 'react';
import { Card, Image, Text, Badge, Button, Group, SimpleGrid, Container, Title, Loader, Center } from '@mantine/core';
import { IconShoppingCart } from '@tabler/icons-react';
import api from '../../../api';
import { useCart } from '../../../context/CartContext';

function StorePage() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const { addToCart } = useCart();

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await api.get('/api/products');
                setProducts(response.data);
            } catch (error) {
                console.error("Error fetching products:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);

    if (loading) {
        return (
            <Center h={300}>
                <Loader size="xl" />
            </Center>
        );
    }

    return (
        <Container size="xl" py="xl">
            <Title order={1} mb="xl" ta="center">CineConnect Store</Title>
            <SimpleGrid cols={{ base: 1, sm: 2, md: 3, lg: 4 }} spacing="lg">
                {products.map((product) => (
                    <Card key={product.productId} shadow="sm" padding="lg" radius="md" withBorder>
                        <Card.Section>
                            <Image
                                src={product.imageUrl || "https://placehold.co/300x200?text=No+Image"}
                                height={160}
                                alt={product.name}
                                fallbackSrc="https://placehold.co/300x200?text=No+Image"
                            />
                        </Card.Section>

                        <Group justify="space-between" mt="md" mb="xs">
                            <Text fw={500}>{product.name}</Text>
                            <Badge color="pink" variant="light">
                                {product.type}
                            </Badge>
                        </Group>

                        <Group justify="space-between" mb="md">
                            <Text size="sm" c="dimmed">
                                ${product.price.toFixed(2)}
                            </Text>
                            <Badge color={product.available ? "green" : "red"} variant="light">
                                {product.available ? `In Stock (${product.quantity})` : "Out of Stock"}
                            </Badge>
                        </Group>

                        <Button
                            color="blue"
                            fullWidth
                            mt="md"
                            radius="md"
                            leftSection={<IconShoppingCart size={18} />}
                            onClick={() => addToCart(product)}
                            disabled={!product.available}
                        >
                            {product.available ? 'Add to Cart' : 'Unavailable'}
                        </Button>
                    </Card>
                ))}
            </SimpleGrid>
        </Container>
    );
}

export default StorePage;
