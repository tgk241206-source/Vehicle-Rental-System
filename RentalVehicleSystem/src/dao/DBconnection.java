// package dao;

// import java.sql.Connection;
// import java.sql.DriverManager;

// public class DBConnection {

//     private static final String URL = "jdbc:mysql://localhost:3306/vehiclerental";
//     private static final String USER = "root";
//     private static final String PASSWORD = "";

//     private static Connection connection;

//     // Lay connection
//     public static Connection getConnection() {

//         try {
//             if (connection == null || connection.isClosed()) {

//                 Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver

//                 connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                 System.out.println("Ket noi database thanh cong!");
//             }
//         } catch (Exception e) {
//             System.out.println("Loi ket noi database!");
//             e.printStackTrace();
//         }

//         return connection;
//     }
// }
