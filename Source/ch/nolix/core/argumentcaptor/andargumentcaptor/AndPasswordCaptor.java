package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public class AndPasswordCaptor<N> extends ArgumentCaptor<String, N> {
  public AndPasswordCaptor() {
  }

  public AndPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andPassword(final String password) {
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableCatalog.PASSWORD).isNotNull();

    return setArgumentAndGetNext(password);
  }

  public final String getPassword() {
    return getStoredArgument();
  }
}
