package com.example.javahomework.w5_fx.t10;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:mydb;MODE=MYSQL";

    static final String USER = "sa";
    static final String PASS = "";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {// 设置默认工厂类
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);

        // Hikari 连接池
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername("sa");
        config.setPassword("");
        config.setMaximumPoolSize(50);
        DataSource dataSource = new HikariDataSource(config);

        // 打开链接
        try (// Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try {
                // 启动事务
                connection.setAutoCommit(false);

                // 创建表结构
                System.out.println("创建表结构：student");
                String createTableSql = "CREATE TABLE student (id INT(11), name VARCHAR(20))";
                statement.executeUpdate(createTableSql);

                // 增（批处理）
                String insertSql = "INSERT into student values (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

                System.out.println("新增：11-张三");
                preparedStatement.setInt(1, 11);
                preparedStatement.setString(2, "张三");
                preparedStatement.addBatch();
                System.out.println("新增：12-李四");
                preparedStatement.setInt(1, 12);
                preparedStatement.setString(2, "李四");
                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                preparedStatement.close();

                // 查
                System.out.println("查找：");
                String selectSql = "SELECT id, name FROM student";
                ResultSet resultSet = statement.executeQuery(selectSql);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "-" + resultSet.getString(2) + "; ");
                }

                // 删
                System.out.println("删除：11-张三");
                String deleteSql = "DELETE FROM student WHERE id = ?";
                preparedStatement = connection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, 11);
                preparedStatement.execute();
                preparedStatement.close();

                // 改
                System.out.println("更新：12-李四");
                String updateSql = "UPDATE student set name = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setString(1, "王五");
                preparedStatement.setInt(2, 12);
                preparedStatement.execute();
                preparedStatement.close();

                // 查
                resultSet = statement.executeQuery(selectSql);
                System.out.println("查找：");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "-" + resultSet.getString(2) + "; ");
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }

    }
}
