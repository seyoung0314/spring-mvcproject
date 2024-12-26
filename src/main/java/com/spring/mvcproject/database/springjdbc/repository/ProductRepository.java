package com.spring.mvcproject.database.springjdbc.repository;

import com.spring.mvcproject.database.springjdbc.entity.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
//@AllArgsConstructor
@RequiredArgsConstructor // final 필드만 골라내서 생성자를 만듬
public class ProductRepository {

    // spring jdbc를 수행하는 객체
    private final JdbcTemplate jdbcTemplate; // 의존객체에는 final 처리


    //생성자가 단 1개면 자동 주입처리
//    @Autowired
//    public ProductRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    // insert
    public void save(Product product) {
        String sql = """
                INSERT INTO products
                    (name,price,stock_quantity,description,seller,status)
                VALUES
                    (?,?,?,?,?,?)
                """;

        if (product.getStatus() != null) {
            // status 값을 넘길 때
            sql = """
                    INSERT INTO products
                        (name,price,stock_quantity,description,seller,status) 
                        VALUES (?, ?, ?, ?, ?, ?)""";

            jdbcTemplate.update(sql,
                    product.getName(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getDescription(),
                    product.getSeller(),
                    product.getStatus());
        } else {
            sql = """
                    INSERT INTO products
                        (name,price,stock_quantity,description,seller) 
                        VALUES (?, ?, ?, ?, ?)""";
            jdbcTemplate.update(sql,
                    product.getName(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getDescription(),
                    product.getSeller());
        }
    }

    // delete
    public void deleteByid(Long id) {
        String sql = """
                DELETE FROM products
                WHERE id = ?
                """;
        jdbcTemplate.update(sql, id);
    }

    // update (가격 수정)
    public void updatePrice(Long id, int newPrice) {
        String sql = """
                UPDATE products
                SET price = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql, newPrice, id);
    }

    // 다중 select
    public List<Product> findAll() {
        String sql = """
                SELECT * FROM products
                """;

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

//    public List<Product> findAll2() {
//        String sql = """
//                SELECT * FROM products
//                """;
//
////        List<Product> productList = jdbcTemplate.query(sql, new RowMapper<Product>() {
////            @Override
////            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
////                return new Product(rs);
////            }
////        });
//        List<Product> productList = jdbcTemplate.query(sql,(rs, rowNum) -> new Product(rs));
//
//        return productList;
//    }

    // 다중 select
    public List<Product> findAll3() {
        String sql = """
                SELECT * FROM products
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

//    public  Product findById(Long id){
//        String sql = """
//                SELECT * FROM products
//                WHERE id = ?
//                """;
//
//        Product p = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Product(rs), id);
//        return p;
//    }

    public  Product findById2(Long id){
        String sql = """
                SELECT * FROM products
                WHERE id = ?
                """;

        Product p = jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<>(Product.class),id);
        return p;
    }

    // 단일 SELECT


}
