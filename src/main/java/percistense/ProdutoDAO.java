package percistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO {

	public List<Produto> findByNomeContaining(String nome) {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM produto WHERE nome LIKE ?";
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto p = mapRow(rs);
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Produto> findAll() {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM produto";
		try (Connection conn = ConnectionProvider.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Produto p = mapRow(rs);
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Produto findById(Long id) {
		Produto produto = null;
		String sql = "SELECT * FROM produto WHERE id = ?";
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				produto = mapRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

	public List<Produto> findByPolitico(Long id) {
		List<Produto> listaProdutoComPolitico = new ArrayList<>();
		String sql = "SELECT * FROM produto WHERE politico_id = ?";

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Produto produto = produto = mapRow(rs);
				listaProdutoComPolitico.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProdutoComPolitico;
	}

	public void save(Produto produto) {
		String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, produto.getNome());
			ps.setDouble(2, produto.getPreco());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				produto.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Produto mapRow(ResultSet rs) throws SQLException {
		Produto p = new Produto();
		p.setId(rs.getLong("id"));
		p.setNome(rs.getString("nome"));
		p.setPreco(rs.getDouble("preco"));
		return p;
	}
}
