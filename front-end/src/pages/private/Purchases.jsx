import { useState, useEffect } from 'react';
import {Card, Stack} from "@mantine/core";
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

    return (<div>


            <div>
                <Stack gap="sm">

                    <Card shadow="sm" withBorder radius="md">
                        <h2>Purchases</h2>
                        <div>
                            {purchases ? <h2>Hello {purchases.map((purchase)=>{
                                return <p>{purchase.itemProductName}</p>
                            })} </h2> : <p>Loading...</p>}
                        </div>
                    </Card>


                </Stack>

            </div>

        </div>
    );
}
export default UserPurchases;
