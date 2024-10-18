package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public class AndLoginNameCaptor<N> extends ArgumentCaptor<String, N> {

  public AndLoginNameCaptor() {
  }

  public AndLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andLoginName(final String loginName) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  public final String getLoginName() {
    return getStoredArgument();
  }
}
