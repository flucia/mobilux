package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuerySelectAll {

	private Connection c;

	public QuerySelectAll(Connection c) {
		this.c = c;
	}

	public ArrayList<Cliente> selectAllCliente() throws SQLException {
		SelectMySql select = new SelectMySql(c);

		ArrayList<Cliente> toret = new ArrayList<>();

		ResultSet rs = select.select("SELECT * FROM Cliente");
		while (rs.next()) {
			Cliente cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			toret.add(cliente);
		}

		return toret;
	}
}
