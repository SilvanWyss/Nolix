//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;

//class
public final class SqlConnectionFactory {

  // method
  public SqlConnection createQslConnectionFor(final SqlConnectionPool sqlDatabaseTarget) {
    return switch (sqlDatabaseTarget.getSqlDatabaseEngine()) {
      case MSSQL ->
        new MsSqlConnection(
            sqlDatabaseTarget.getIpOrDomain(),
            sqlDatabaseTarget.getPort(),
            sqlDatabaseTarget.getLoginName(),
            sqlDatabaseTarget.getLoginPassword(),
            sqlDatabaseTarget);
      case MYSQL, ORACLE ->
        throw UnsupportedCaseException.forCase(sqlDatabaseTarget.getSqlDatabaseEngine());
      default ->
        throw InvalidArgumentException.forArgument(sqlDatabaseTarget.getSqlDatabaseEngine());
    };
  }
}
