package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public class AndLoginPasswordCaptor<N> extends ArgumentCaptor<String, N> {

  public AndLoginPasswordCaptor() {
  }

  public AndLoginPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andLoginPassword(final String loginPassword) {

    GlobalValidator.assertThat(loginPassword).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_PASSWORD).isNotBlank();

    return setArgumentAndGetNext(loginPassword);
  }

  public final String getLoginPassword() {
    return getStoredArgument();
  }
}
