package com.dam.example_DDL_DML;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleDdlDmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleDdlDmlApplication.class, args);

		java.sql.Connection conexion = null;
		try {
			conexion = DriverManager.getConnection("jdb.mysql://localhost:3306/universidad", "root", "password");
			if (conexion != null) {
				System.out.println("Conectado");
				// Stamenent
				Statement sentencia = conexion.createStatement();
				// ResulSet
				ResultSet resultado = sentencia.executeQuery("SELECT * FROM PERSONA");

				while (resultado.next()) {
					System.out.println("La persona con " + resultado.getString("nif"));
				}

				resultado.close();
				sentencia.close();
			}
			conexion.close();
		} catch (SQLException SQLException) {
			System.err.println("Problema SQL");
		} catch (Exception e) {
			System.out.println("Problema");
		} finally {
			try {
				if (conexion != null) conexion.close();
			} catch (Exception e) {
				System.err.println("Error cerrando conexiones");
			}
		}
	}

}
