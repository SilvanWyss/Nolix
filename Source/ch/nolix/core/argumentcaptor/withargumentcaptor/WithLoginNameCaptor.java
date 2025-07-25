package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;

public class WithLoginNameCaptor<N> extends ArgumentCaptor<String, N> {

  public WithLoginNameCaptor() {
  }

  public WithLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getLoginName() {
    return getStoredArgument();
  }

  public final N withLoginName(final String loginName) {

    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }
}
