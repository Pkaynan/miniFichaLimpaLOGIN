package percistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Usuario;

public class UsuarioDao {

	public boolean validar(String nome, String pass) {
		String sql = "SELECT * FROM usuario WHERE nome = ? AND pass = ?";
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, pass);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(nome);
				usuario.setPass(pass);
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void save(Usuario usuario) {
		String sql = "INSERT INTO usuario (nome, pass) VALUES (?, ?)";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getPass());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
