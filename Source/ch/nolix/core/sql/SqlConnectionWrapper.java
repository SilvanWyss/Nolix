//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class SqlConnectionWrapper implements AutoCloseable {

  //static method
  public static SqlConnectionWrapper forSqlConnection(final SqlConnection sqlConnection) {
    return new SqlConnectionWrapper(sqlConnection);
  }

  //attribute
  private final SqlConnection sqlConnection;

  //attribute
  private boolean available = true;

  //constructor
  private SqlConnectionWrapper(final SqlConnection sqlConnection) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();
    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).fulfills(SqlConnection::isOpen);

    this.sqlConnection = sqlConnection;
  }

  //method
  public SqlConnection getStoredSqlConnection() {

    assertIsAvailable();

    return sqlConnection;
  }

  //method
  @Override
  public void close() {
    sqlConnection.internalCloseDirectly();
  }

  //method
  public boolean contains(final SqlConnection sqlConnection) {
    return (this.sqlConnection == sqlConnection);
  }

  //method
  public boolean isAvailable() {
    return available;
  }

  //method
  public boolean isInUse() {
    return !isAvailable();
  }

  //method
  public void setAvailable() {
    available = true;
  }

  //method
  public void setAsInUse() {

    assertIsAvailable();

    available = false;
  }

  //method
  private void assertIsAvailable() {
    if (!isAvailable()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not available");
    }
  }
}
