package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForLoginNameCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForLoginNameCaptor}
 */
public class ForLoginNameCaptor<S> extends AbstractArgumentCaptor<String, S> implements IForLoginNameCaptor<S> {
  public ForLoginNameCaptor() {
  }

  public ForLoginNameCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public S forLoginName(String loginName) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(loginName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLoginName() {
    return getStoredArgument();
  }
}
