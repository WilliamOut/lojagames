/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.hdm82digital.lojagames.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author will
 */
public class Conexao {

    private static final String URL = "jdbc:mariadb://localhost:3306/loja_games";
    private static final String USER = "root";
    private static final String PASS = "wadm";
    private static Conexao instancia;
    private Connection conn = null;
    
    private Conexao() {}
    
    public static synchronized Conexao pegarInstancia() {
        if(instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }
    
    public Connection pegarConexao() {
        try {
            if(conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL,USER,PASS);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
    
    public void fecharConexao() {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
            }       
        } catch (SQLException e) {
            System.out.println("Erro ao tentar fechar a conexão com o banco de dados: " +e.getMessage());
            e.printStackTrace();
        } finally {
            conn = null;
        }
        
    }
}
