package ch.nolix.core.programcontrol.usercontrol;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class Credential {

  private final String loginName;

  private final String password;

  Credential(final String loginName, final String password) {

    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalog.LOGIN_NAME).isNotBlank();
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableCatalog.PASSWORD).isNotBlank();

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
