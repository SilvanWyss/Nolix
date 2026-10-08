/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndSqlDatabaseEngineCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndSqlDatabaseEngineCaptor}
 */
public class AndSqlDatabaseEngineCaptor<S>
extends AbstractArgumentCaptor<SqlDatabaseEngine, S>
implements IAndSqlDatabaseEngineCaptor<S> {
  public AndSqlDatabaseEngineCaptor() {
  }

  public AndSqlDatabaseEngineCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine) {
    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    return setArgumentAndGetStoredSuccessor(sqlDatabaseEngine);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final SqlDatabaseEngine getSqlDatabaseEngine() {
    return getStoredArgument();
  }
}
