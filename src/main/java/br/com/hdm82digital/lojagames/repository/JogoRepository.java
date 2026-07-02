package br.com.hdm82digital.lojagames.repository;

import br.com.hdm82digital.lojagames.config.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class JogoRepository {

    public boolean salvar(String titulo, double preco, int qtd) {
        String sql = "INSERT INTO jogo (titulo, preco, quantidade) VALUES (?, ?, ?)";
        Connection conn = Conexao.pegarInstancia().pegarConexao();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setDouble(2, preco);
            ps.setInt(3, qtd);

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar jogo: " + e.getMessage());
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
        String sql = "SELECT * FROM jogo";
        List<Object[]> lista = new ArrayList<>();

        Connection conn = Conexao.pegarInstancia().pegarConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] linha = new Object[]{
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                };
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar jogos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public boolean atualizar(int id, String titulo, double preco, int quantidade) {
        String sql = "UPDATE jogo SET titulo = ?, preco = ?, quantidade = ? WHERE id = ?";
        Connection conn = Conexao.pegarInstancia().pegarConexao();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setDouble(2, preco);
            ps.setInt(3, quantidade);
            ps.setInt(4, id);

            ps.executeUpdate();
            return true;
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao atualizar jogo: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (java.sql.SQLException e) {
            }
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM jogo WHERE id = ?";
        Connection conn = Conexao.pegarInstancia().pegarConexao();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            return true;
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao excluir jogo: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (java.sql.SQLException e) {
            }
        }
    }
}
