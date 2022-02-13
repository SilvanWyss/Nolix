//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;

//class
public final class SQLConnectionFactory {
	
	//method
	public SQLConnection createSQLConnectionFor(final SQLConnectionPool pSQLDatabaseTarget) {
		switch (pSQLDatabaseTarget.getSQLDatabaseEngine()) {
			case MSSQL:
				return new MSSQLConnection(
					pSQLDatabaseTarget.getIpOrAddressName(),
					pSQLDatabaseTarget.getPort(),
					pSQLDatabaseTarget.getDatabaseName(),
					pSQLDatabaseTarget.getLoginName(),
					pSQLDatabaseTarget.getLoginPassword(),
					pSQLDatabaseTarget
				);
			case MYSQL:
			case ORACLE:
				throw new UnsupportedCaseException(pSQLDatabaseTarget.getSQLDatabaseEngine().toString());
			default:
				throw new InvalidArgumentException(pSQLDatabaseTarget.getSQLDatabaseEngine());
		}
	}
}
