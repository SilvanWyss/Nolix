package ch.nolix.core.programcontrol.usercontrol;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class Credential {

  private final String loginName;

  private final String password;

  Credential(final String loginName, final String password) {

    GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalogue.LOGIN_NAME).isNotBlank();
    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseVariableCatalogue.PASSWORD).isNotBlank();

    this.loginName = loginName;
    this.password = password;
  }

  public String getLoginName() {
    return loginName;
  }

  public String getPassword() {
    return password;
  }
}
