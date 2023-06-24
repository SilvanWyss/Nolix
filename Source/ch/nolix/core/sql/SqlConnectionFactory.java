//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;

//class
public final class SqlConnectionFactory {
	
	//method
	public SqlConnection createQslConnectionFor(final SqlConnectionPool sqlDatabaseTarget) {
		switch (sqlDatabaseTarget.getSqlDatabaseEngine()) {
			case MSSQL:
				return new MsSqlConnection(
					sqlDatabaseTarget.getIpOrAddressName(),
					sqlDatabaseTarget.getPort(),
					sqlDatabaseTarget.getLoginName(),
					sqlDatabaseTarget.getLoginPassword(),
					sqlDatabaseTarget
				);
			case MYSQL, ORACLE:
				throw UnsupportedCaseException.forCase(sqlDatabaseTarget.getSqlDatabaseEngine());
			default:
				throw InvalidArgumentException.forArgument(sqlDatabaseTarget.getSqlDatabaseEngine());
		}
	}
}
