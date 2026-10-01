package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForDatabaseCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForDatabaseCaptor}
 */
public class ForDatabaseCaptor<S> extends AbstractArgumentCaptor<String, S> implements IForDatabaseCaptor<S> {
  public ForDatabaseCaptor() {
  }

  public ForDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forDatabase(String database) {
    Validator.assertThat(database).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE).isNotBlank();

    return setArgumentAndGetStoredSuccessor(database);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabase() {
    return getStoredArgument();
  }
}
