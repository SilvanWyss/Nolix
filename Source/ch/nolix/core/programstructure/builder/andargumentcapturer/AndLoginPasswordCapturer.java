//package declaration
package ch.nolix.core.programstructure.builder.andargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndLoginPasswordCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public AndLoginPasswordCapturer() {
  }

  //constructor
  public AndLoginPasswordCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
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
