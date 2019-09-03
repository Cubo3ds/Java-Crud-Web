package com.ecodeup.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
	
	//Crear pool de conexiones 
	private static BasicDataSource dataSource=null;
	
	private static DataSource getDataSource() {
		if (dataSource==null) {
			dataSource=new BasicDataSource();//Creamos el objeto
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("magodeoz");
			dataSource.setUrl("jdbc:mysql://localhost:3306/crud?serverTimezone=UTC");
			dataSource.setInitialSize(20);//inicialice en 20
			dataSource.setMaxIdle(15);//15 conexiones minimo activas
			dataSource.setMaxTotal(20);//20 un maximo de conexiones
			dataSource.setMaxWaitMillis(5000);//definir si una conexion esta activa a la base de datos
		}
		
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();//retornamos el metodo getDataSource
		
	}

}
