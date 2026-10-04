package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForNameCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForNameCaptor}
 */
public class ForNameCaptor<S> extends AbstractArgumentCaptor<String, S> implements IForNameCaptor<S> {
  public ForNameCaptor() {
  }

  public ForNameCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forName(String name) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return getStoredArgument();
  }
}
