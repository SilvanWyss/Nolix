/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithSqlDatabaseEngineCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a
 *            {@link WithSqlDatabaseEngineCaptor}.
 */
public class WithSqlDatabaseEngineCaptor<S> extends AbstractArgumentCaptor<SqlDatabaseEngine, S>
implements IWithSqlDatabaseEngineCaptor<S> {
  public WithSqlDatabaseEngineCaptor() {
  }

  public WithSqlDatabaseEngineCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final SqlDatabaseEngine getSqlDatabaseEngine() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine) {
    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    return setArgumentAndGetStoredSuccessor(sqlDatabaseEngine);
  }
}
