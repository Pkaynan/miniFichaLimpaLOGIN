package percistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Politico;

public class PoliticoDAO {

	public List<Politico> findByNomeContaining(String nome) {
		List<Politico> lista = new ArrayList<>();
		String sql = "SELECT * FROM politico WHERE nome LIKE ?";

		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Politico p = new Politico();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setNumeroEleitoral(rs.getInt("numero_eleitoral"));
				p.setNomeEleitoral(rs.getString("nome_eleitoral"));
				p.setPartido(rs.getLong("partido_id"));
				lista.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Politico> findAll() {
		List<Politico> politicos = new ArrayList<>();

		try {
			Connection conn = ConnectionProvider.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, nome, numero_eleitoral, nome_eleitoral, partido_id FROM politico");

			while (rs.next()) {
				Politico politico = mapRow(rs);
				politicos.add(politico);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return politicos;
	}

	public Politico findById(Long idPolitico) {
		Politico politico = null;
		try {
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement ps = conn.prepareStatement(
					"SELECT id, nome, numero_eleitoral, nome_eleitoral, partido_id FROM politico WHERE id = ?");
			ps.setLong(1, idPolitico);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				politico = mapRow(rs);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return politico;
	}

	public Politico save(Politico politico) {
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO politico (nome, numero_eleitoral, nome_eleitoral, partido_id) VALUES (?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, politico.getNome());
			ps.setInt(2, politico.getNumeroEleitoral());
			ps.setString(3, politico.getNomeEleitoral());
			ps.setLong(4, politico.getPartido());

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					politico.setId(rs.getLong(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return politico;
	}

	private Politico mapRow(ResultSet rs) throws SQLException {
		Politico politico = new Politico();

		politico.setId(rs.getLong("id"));
		politico.setNome(rs.getString("nome"));
		politico.setNumeroEleitoral(rs.getInt("numero_eleitoral"));
		politico.setNomeEleitoral(rs.getString("nome_eleitoral"));
		politico.setPartido(rs.getLong("partido_id"));

		return politico;
	}

}
