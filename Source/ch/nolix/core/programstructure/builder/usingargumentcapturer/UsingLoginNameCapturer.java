//package declaration
package ch.nolix.core.programstructure.builder.usingargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class UsingLoginNameCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public UsingLoginNameCapturer() {
  }

  //method
  public UsingLoginNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final N usingLoginName(final String loginName) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  //method
  public final String getLoginName() {
    return getStoredArgument();
  }
}
