package br.com.hdm82digital.lojagames.repository;

import br.com.hdm82digital.lojagames.config.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    public boolean salvar(String nome, String email, String telefone, int idJogo) {
        String sql = "INSERT INTO cliente (nome, email, telefone, id_jogo_comprado) "
                + "VALUES (?, ?, ?, ?)";
        Connection conn = Conexao.pegarInstancia().pegarConexao();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            if (idJogo > 0) {
                ps.setInt(4, idJogo);
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Object[]> listarTodos() {
        String sql = "SELECT * FROM cliente";
        List<Object[]> lista = new ArrayList<>();
        
        Connection conn = Conexao.pegarInstancia().pegarConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] linha = new Object[] {
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getInt("id_jogo_comprado")
                };
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
