//package declaration
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndLoginNameCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public AndLoginNameCaptor() {
  }

  //constructor
  public AndLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N andLoginName(final String loginName) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  //method
  public final String getLoginName() {
    return getStoredArgument();
  }
}
