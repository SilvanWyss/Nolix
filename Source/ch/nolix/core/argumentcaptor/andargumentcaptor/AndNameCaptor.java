package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public class AndNameCaptor<N> extends ArgumentCaptor<String, N> {

  public AndNameCaptor() {
  }

  public AndNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getName() {
    return getStoredArgument();
  }

  public final N andName(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    return setArgumentAndGetNext(name);
  }
}
