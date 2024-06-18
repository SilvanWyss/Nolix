//package declaration
package ch.nolix.core.programstructure.builder.andargumentcaptor;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndLoginPasswordCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public AndLoginPasswordCaptor() {
  }

  //constructor
  public AndLoginPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N andLoginPassword(final String loginPassword) {

    GlobalValidator.assertThat(loginPassword).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_PASSWORD).isNotBlank();

    return setArgumentAndGetNext(loginPassword);
  }

  //method
  public final String getLoginPassword() {
    return getStoredArgument();
  }
}
