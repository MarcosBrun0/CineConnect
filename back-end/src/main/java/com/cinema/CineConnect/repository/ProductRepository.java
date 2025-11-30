package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.TypeCount;
import com.cinema.CineConnect.model.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void removeProduct(JdbcClient jdbcClient, UUID id) {
        jdbcClient.sql("""
                    DELETE FROM products
                    WHERE id = :id
                """)
                .param("id", id)
                .update();
    }

    public boolean removeProductTypeSafely(JdbcClient jdbcClient, UUID typeId) {
        // check if any product uses this type
        Integer count = jdbcClient.sql("""
                    SELECT COUNT(*)
                    FROM products
                    WHERE type_id = :id
                """)
                .param("id", typeId)
                .query(Integer.class)
                .single();

        if (count > 0)
            return false; // refuse to delete

        // delete type
        jdbcClient.sql("""
                    DELETE FROM product_types
                    WHERE id = :id
                """)
                .param("id", typeId)
                .update();

        return true;
    }

    public List<com.cinema.CineConnect.model.DTO.ProductRecord> findAll() {
        return jdbcClient.sql("""
                    SELECT p.id as productId, p.name, pt.name as type, p.price, null as sessionId, null as addOns
                    FROM products p
                    JOIN product_types pt ON p.type_id = pt.id
                """)
                .query(com.cinema.CineConnect.model.DTO.ProductRecord.class)
                .list();
    }

    public Integer getQuantityById(JdbcClient jdbcClient, UUID productTypeId) {
        return jdbcClient.sql("""
                    SELECT COUNT(*)
                    FROM products
                    WHERE type_id = (
                        SELECT type_id
                        FROM products
                        WHERE id = :productId
                    )
                """)
                .param("productId", productTypeId)
                .query(Integer.class)
                .single();
    }

    public List<Product> findProductsByType(JdbcClient jdbcClient, UUID productTypeId) {
        return jdbcClient.sql("""
                    SELECT * FROM products WHERE type_id = :productType
                """)
                .param("productType", productTypeId)
                .query(Product.class)
                .list();
    }

    public List<TypeCount> listTypesWithCount(JdbcClient jdbcClient) {
        return jdbcClient.sql("""
                    SELECT pt.id, pt.name, COUNT(p.id) AS total
                    FROM product_types pt
                    LEFT JOIN products p ON p.type_id = pt.id
                    GROUP BY pt.id, pt.name
                    ORDER BY pt.name
                """)
                .query(TypeCount.class)
                .list();
    }

}
