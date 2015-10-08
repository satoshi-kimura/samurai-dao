package config;

public class SQLServerConfig implements DBConfig {

	@Override
	public String driverClassName() {
		return "net.sourceforge.jtds.jdbc.Driver";
	}

	@Override
	public String URL() {
		return "jdbc:jtds:sqlserver://localhost/TEST;instance=SQLEXPRESS";
	}

	@Override
	public String user() {
		return "xxx";
	}

	@Override
	public String password() {
		return "xxx";
	}
}
