package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;

public class AndLoginNameCaptor<N> extends ArgumentCaptor<String, N> {

  public AndLoginNameCaptor() {
  }

  public AndLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andLoginName(final String loginName) {

    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  public final String getLoginName() {
    return getStoredArgument();
  }
}
