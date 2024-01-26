//package declaration
package ch.nolix.core.programstructure.builder.withargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class WithLoginNameCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public WithLoginNameCapturer() {
  }

  //constructor
  public WithLoginNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
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
