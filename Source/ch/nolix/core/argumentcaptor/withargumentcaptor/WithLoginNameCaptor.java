//package declaration
package ch.nolix.core.argumentcaptor.withargumentcaptor;

//own imports
import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class WithLoginNameCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public WithLoginNameCaptor() {
  }

  //constructor
  public WithLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final String getLoginName() {
    return getStoredArgument();
  }

  //method
  public final N withLoginName(final String loginName) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }
}
