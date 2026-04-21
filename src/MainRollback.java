import java.sql.*;
import java.util.*;

public class MainRollback {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "RIBERA",
                    "ribera"
            );

            System.out.println("Conectado!");

            connection.setAutoCommit(false); // Modo transaccional

            // Hacemos dos PreparedStatement para hacer algunas operaciones
            try (PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO empleado (nombre, salario) VALUES (?, ?)")) {
                ps1.setString(1, "Carlos");
                ps1.setDouble(2, 4200);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = connection.prepareStatement(
                    "UPDATE departamento SET presupuesto = presupuesto + ? WHERE id = 1")) {
                ps2.setDouble(1, 4200);
                ps2.executeUpdate();
            }

            // Aqui despues de las operaciones, hacemos un commit para guardar.
            connection.commit();
            System.out.println("Transaccion exitosa!");
            
        // Si algo falla, mostramos el error por la pantalla
        // Y hacemos un rollback.
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            connection.rollback();
        }
    }
}
