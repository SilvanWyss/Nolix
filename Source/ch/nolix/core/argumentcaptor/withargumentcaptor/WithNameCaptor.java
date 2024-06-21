//package declaration
package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class WithNameCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public WithNameCaptor() {
  }

  //constructor
  public WithNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final String getName() {
    return getStoredArgument();
  }

  //method
  public final N withName(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    return setArgumentAndGetNext(name);
  }
}
