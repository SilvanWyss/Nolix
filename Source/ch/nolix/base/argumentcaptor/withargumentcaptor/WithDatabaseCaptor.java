package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithDatabaseCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link WithDatabaseCaptor}
 */
public class WithDatabaseCaptor<S> extends AbstractArgumentCaptor<String, S> implements IWithDatabaseCaptor<S> {
  public WithDatabaseCaptor() {
  }

  public WithDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabase() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withDatabase(String database) {
    Validator.assertThat(database).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE).isNotBlank();

    return setArgumentAndGetStoredSuccessor(database);
  }
}
