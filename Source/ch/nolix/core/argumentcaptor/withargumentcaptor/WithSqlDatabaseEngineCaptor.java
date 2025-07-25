package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;

public class WithSqlDatabaseEngineCaptor<N> extends ArgumentCaptor<SqlDatabaseEngine, N> {

  public WithSqlDatabaseEngineCaptor() {
  }

  public WithSqlDatabaseEngineCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final SqlDatabaseEngine getSqlDatabaseEngine() {
    return getStoredArgument();
  }

  public final N withSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine) {

    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    return setArgumentAndGetNext(sqlDatabaseEngine);
  }
}
