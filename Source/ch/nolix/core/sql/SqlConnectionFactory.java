//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;

//class
public final class SqlConnectionFactory {
	
	//method
	public SqlConnection createSQLConnectionFor(final SqlConnectionPool sqlDatabaseTarget) {
		switch (sqlDatabaseTarget.getSQLDatabaseEngine()) {
			case MSSQL:
				return new MsSqlConnection(
					sqlDatabaseTarget.getIpOrAddressName(),
					sqlDatabaseTarget.getPort(),
					sqlDatabaseTarget.getLoginName(),
					sqlDatabaseTarget.getLoginPassword(),
					sqlDatabaseTarget
				);
			case MYSQL, ORACLE:
				throw UnsupportedCaseException.forCase(sqlDatabaseTarget.getSQLDatabaseEngine());
			default:
				throw InvalidArgumentException.forArgument(sqlDatabaseTarget.getSQLDatabaseEngine());
		}
	}
}
