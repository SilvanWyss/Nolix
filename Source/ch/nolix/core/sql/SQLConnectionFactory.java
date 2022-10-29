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
					pSQLDatabaseTarget.getLoginName(),
					pSQLDatabaseTarget.getLoginPassword(),
					pSQLDatabaseTarget
				);
			case MYSQL, ORACLE:
				throw UnsupportedCaseException.forCase(pSQLDatabaseTarget.getSQLDatabaseEngine());
			default:
				throw InvalidArgumentException.forArgument(pSQLDatabaseTarget.getSQLDatabaseEngine());
		}
	}
}
