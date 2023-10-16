//package declaration
package ch.nolix.core.builder.usingargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class UsingLoginNameCapturer<N> extends ArgumentCapturer<String, N> {

  //method
  public UsingLoginNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final N usingLoginName(final String loginName) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseCatalogue.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  //method
  public final String getLoginName() {
    return getStoredArgument();
  }
}
