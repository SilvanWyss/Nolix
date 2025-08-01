package ch.nolix.core.programcontrol.usercontrol;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public final class Credential {

  private final String loginName;

  private final String password;

  private Credential(final String loginName, final String password) {

    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalog.LOGIN_NAME).isNotBlank();
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableCatalog.PASSWORD).isNotBlank();

    this.loginName = loginName;
    this.password = password;
  }

  public static final Credential withLoginNameAndPassword(final String loginName, final String password) {
    return new Credential(loginName, password);
  }

  public String getLoginName() {
    return loginName;
  }

  public String getPassword() {
    return password;
  }
}
