//package declaration
package ch.nolix.core.programstructure.builder.usingargumentcapturer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;

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

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseCatalogue.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  //method
  public final String getLoginName() {
    return getStoredArgument();
  }
}
