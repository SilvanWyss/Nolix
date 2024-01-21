//package declaration
package ch.nolix.core.programcontrol.usercontrol;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPasswordCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class Credential {

  //attribute
  private final String loginName;

  //attribute
  private final String password;

  //constant
  public static final class CredentialBuilder extends AndPasswordCapturer<Credential> {

    //constructor
    private CredentialBuilder(final String loginName) {
      this.setBuilder(() -> new Credential(loginName, super.getPassword()));
    }
  }

  //constructor
  Credential(final String loginName, final String password) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();
    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseVariableCatalogue.PASSWORD).isNotBlank();

    this.loginName = loginName;
    this.password = password;
  }

  //static method
  public static CredentialBuilder withLoginName(final String loginName) {
    return new CredentialBuilder(loginName);
  }

  //method
  public String getLoginName() {
    return loginName;
  }

  //method
  public String getPassword() {
    return password;
  }
}
