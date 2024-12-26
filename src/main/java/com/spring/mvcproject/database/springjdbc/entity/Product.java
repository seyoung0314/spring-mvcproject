package com.spring.mvcproject.database.springjdbc.entity;


/*
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '상품명',
    price INT NOT NULL COMMENT '가격',
    stock_quantity INT NOT NULL COMMENT '재고수량',
    description TEXT COMMENT '상품설명',
    seller VARCHAR(50) NOT NULL COMMENT '판매자',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '상품상태',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='상품';
 */

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // id만 같으면 같다고 처리
@ToString
@Builder
// DB 컬럼명과 클래스의 필드명을 똑같이 작성하기
public class Product {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String description;
    private String seller;
//    @Builder.Default
    private String status;
//    private String status = "Active";
    private LocalDateTime createdAt;

//    public Product(ResultSet rs) throws SQLException {
//        this.id = rs.getLong("id");
//        this.name = rs.getString("name");
//        this.price = rs.getInt("price");
//        this.description = rs.getString("description");
//        this.seller = rs.getString("seller");
//        this.status = rs.getString("status");
//        this.stockQuantity = rs.getInt("stock_quantity");
//        this.createdAt = rs.getTimestamp("created_at").toLocalDateTime();
//    }

}
