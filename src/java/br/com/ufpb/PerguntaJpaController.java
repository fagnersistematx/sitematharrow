package br.com.ufpb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t1061605
 */
public class PerguntaJpaController {

    public List<Pergunta> findAll() {
        List<Pergunta> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT * FROM pergunta";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public Pergunta create(Pergunta pergunta) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO pergunta (pergunta, assunto, resposta) VALUES (?, ?, ?)",
                    new String[]{"ID"});
            ps.setString(1, pergunta.getPergunta());
            ps.setString(2, "Assunto");
            ps.setString(3, "" + pergunta.getResposta());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            pergunta.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return pergunta;
    }

    public Pergunta edit(Pergunta pergunta) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE pergunta SET pergunta=?, assunto=?, resposta=? WHERE id=?");
            ps.setString(1, pergunta.getPergunta());
            ps.setString(2, "asuntoww");
            ps.setString(3, "" + pergunta.getResposta());
            ps.setInt(4, pergunta.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return pergunta;
    }

    public boolean destroy(Integer id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM pergunta WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    public List<Pergunta> findPerguntaEntities() {
        return findAll();
    }

    protected Pergunta processRow(ResultSet rs) throws SQLException {
        Pergunta pergunta = new Pergunta();
        pergunta.setId(rs.getInt("ID"));
        pergunta.setPergunta(rs.getString("PERGUNTA"));
        pergunta.setResposta(Double.parseDouble(rs.getString("RESPOSTA")));
        return pergunta;
    }

}
