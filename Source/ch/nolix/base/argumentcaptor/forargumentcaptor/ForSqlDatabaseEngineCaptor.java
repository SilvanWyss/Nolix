package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForSqlDatabaseEngineCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForSqlDatabaseEngineCaptor}
 */
public class ForSqlDatabaseEngineCaptor<S>
extends AbstractArgumentCaptor<SqlDatabaseEngine, S>
implements IForSqlDatabaseEngineCaptor<S> {
  public ForSqlDatabaseEngineCaptor() {
  }

  public ForSqlDatabaseEngineCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine) {
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
