//package declaration
package ch.nolix.core.programstructure.builder.withargumentcaptor;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public class WithSqlDatabaseEngineCaptor<N> extends ArgumentCaptor<SqlDatabaseEngine, N> {

  //constructor
  public WithSqlDatabaseEngineCaptor() {
  }

  //constructor
  public WithSqlDatabaseEngineCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final SqlDatabaseEngine getSqlDatabaseEngine() {
    return getStoredArgument();
  }

  //method
  public final N withSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine) {

    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    return setArgumentAndGetNext(sqlDatabaseEngine);
  }
}
