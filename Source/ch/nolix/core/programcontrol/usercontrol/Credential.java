//package declaration
package ch.nolix.core.programcontrol.usercontrol;

//own imports
import ch.nolix.core.builder.andargumentcapturer.AndPasswordCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseCatalogue.LOGIN_NAME).isNotBlank();
    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseCatalogue.PASSWORD).isNotBlank();

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
