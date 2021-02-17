package ru.geekbrains.cloud.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
@Repository
public class SqlAuthManager implements AuthManager {


    public static Connection conn;
    private static Statement statement;
    private static PreparedStatement ps;


    public SqlAuthManager(DataSource dataSource) throws SQLException, ClassNotFoundException {
        this(dataSource.getConnection());
    }

    @Autowired
    public SqlAuthManager(Connection conn) throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:ClientStorage.db");
        statement = conn.createStatement();
    }


    @Override
    public void connect() throws ClassNotFoundException, SQLException {

    }

    @Override
    public void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public String getNickNameByLoginAndPassword(String login, String password) throws SQLException {
        String s = "";
        try {
            ResultSet rs = statement.executeQuery("SELECT USERNAME FROM users WHERE login like " + "'" + login + "'" + "AND pass like " + "'" + password + "'");
            while (rs.next()) {
                s = (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

}