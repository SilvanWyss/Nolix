/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a
 *            {@link WithSqlDatabaseEngineCaptor}.
 */
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
