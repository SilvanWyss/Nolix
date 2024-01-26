//package declaration
package ch.nolix.core.programstructure.builder.andargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndPasswordCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public AndPasswordCapturer() {
  }

  //constructor
  public AndPasswordCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final N andPassword(final String password) {

    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseVariableCatalogue.PASSWORD).isNotNull();

    return setArgumentAndGetNext(password);
  }

  //method
  public final String getPassword() {
    return getStoredArgument();
  }
}
