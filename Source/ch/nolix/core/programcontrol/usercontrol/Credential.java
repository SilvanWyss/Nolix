//package declaration
package ch.nolix.core.programcontrol.usercontrol;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class Credential {

  //attribute
  private final String loginName;

  //attribute
  private final String password;

  //constructor
  Credential(final String loginName, final String password) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();
    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseVariableCatalogue.PASSWORD).isNotBlank();

    this.loginName = loginName;
    this.password = password;
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
