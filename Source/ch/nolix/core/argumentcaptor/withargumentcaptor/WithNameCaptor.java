package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public class WithNameCaptor<N> extends ArgumentCaptor<String, N> {

  public WithNameCaptor() {
  }

  public WithNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getName() {
    return getStoredArgument();
  }

  public final N withName(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    return setArgumentAndGetNext(name);
  }
}
