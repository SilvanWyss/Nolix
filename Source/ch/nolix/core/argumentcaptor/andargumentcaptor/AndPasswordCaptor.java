package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public class AndPasswordCaptor<N> extends ArgumentCaptor<String, N> {

  public AndPasswordCaptor() {
  }

  public AndPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andPassword(final String password) {

    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseVariableCatalogue.PASSWORD).isNotNull();

    return setArgumentAndGetNext(password);
  }

  public final String getPassword() {
    return getStoredArgument();
  }
}
