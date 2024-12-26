package com.spring.mvcproject.database.jdbc.repository;

import com.spring.mvcproject.database.jdbc.entity.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 데이터베이스에 접근하는 객체
@Repository
public class PersonRepository {
    // db에 로그인할 정보
    private String username = "root";
    private String password = "mariadb";
    //데이터 베이스가 설치된 주소 (JDBC URL)
    private String url = "jdbc:mariadb://localhost:3306/practice";

    // 전용 드라이버 클래스
    private String driverClassName = "org.mariadb.Driver";

    // 데이터베이스 전용 드라이버 로딩
    public PersonRepository() {
        try {
            // 전용 로딩 클래스 - DB마다 다름
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("DB 연결 실패!");
        }

    }

    // insert
    public void save(Person person) {
        String sql = """
                INSERT INTO tbl_person
                    (id, person_name, age)
                VALUES
                    (?,?,?)
                """;

            Connection conn = null;
        try {
            // 1. db에 접속하고 접속 정보를 받아옴
            conn = DriverManager.getConnection(url, username, password);

            // 2. sql을 실행할 수 있는 실행기 객체를 가져옴
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 3. ?값을 세팅
            pstmt.setLong(1, person.getId());
            pstmt.setString(2, person.getPersonName());
            pstmt.setInt(3, person.getAge());

            // 4. sql 실행 명령
            //  4-a : 갱신(insert, update, delete) 명령 - executeUpdate()
            //  4-b : 조회(select) 명령 - executeQuery()
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 메모리 정리 : 연결 해제
            //
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    //UPDATE
    public void update(Person person) {
        String sql = """
                UPDATE tbl_person
                    SET person_name = ?, age =?
                    WHERE id =?
                """;

        try {
            // 1. db에 접속하고 접속 정보를 받아옴
            Connection conn = DriverManager.getConnection(url, username, password);

            // 2. sql을 실행할 수 있는 실행기 객체를 가져옴
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 3. ?값을 세팅
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getAge());
            pstmt.setLong(3, person.getId());

            // 4. sql 실행 명령
            //  4-a : 갱신(insert, update, delete) 명령 - executeUpdate()
            //  4-b : 조회(select) 명령 - executeQuery()
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //DELETE
    public void delete(Long id) {
        String sql = """
                DELETE FROM tbl_person
                    WHERE id =?
                """;

        try {
            // 1. db에 접속하고 접속 정보를 받아옴
            Connection conn = DriverManager.getConnection(url, username, password);

            // 2. sql을 실행할 수 있는 실행기 객체를 가져옴
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 3. ?값을 세팅
            pstmt.setLong(1, id);

            // 4. sql 실행 명령
            //  4-a : 갱신(insert, update, delete) 명령 - executeUpdate()
            //  4-b : 조회(select) 명령 - executeQuery()
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //다중 SELECT - 목록조회
    public List<Person> findAll() {
        String sql = """
                SELECT * FROM tbl_person
                """;

        List<Person> result = new ArrayList<>();

        try {
            // 1. db에 접속하고 접속 정보를 받아옴
            Connection conn = DriverManager.getConnection(url, username, password);

            // 2. sql을 실행할 수 있는 실행기 객체를 가져옴
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 3. ?값을 세팅

            // 4. sql 실행 명령
            //  4-a : 갱신(insert, update, delete) 명령 - executeUpdate()
            //  4-b : 조회(select) 명령 - executeQuery()
            ResultSet rs = pstmt.executeQuery();

            // .next() : 포인터를 한 행씩 이동
            while (rs.next()) {
                Long id = rs.getLong("id");
                String personName = rs.getString("person_name");
                int age = rs.getInt("age");

                Person p = new Person(id, personName, age);

                System.out.println("p = " + p);
                result.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
