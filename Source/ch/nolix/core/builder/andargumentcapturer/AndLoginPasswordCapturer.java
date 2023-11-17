//package declaration
package ch.nolix.core.builder.andargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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

    GlobalValidator.assertThat(loginPassword).thatIsNamed(LowerCaseCatalogue.LOGIN_PASSWORD).isNotBlank();

    return setArgumentAndGetNext(loginPassword);
  }

  //method
  public final String getLoginPassword() {
    return getStoredArgument();
  }
}
