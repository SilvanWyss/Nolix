/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.authentification;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class Credential {
  private final String loginName;

  private final String password;

  private Credential(final String loginName, final String password) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_NAME).isNotBlank();
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableNameCatalog.PASSWORD).isNotBlank();

    this.loginName = loginName;
    this.password = password;
  }

  /**
   * @param loginName
   * @param password
   * @return a new {@link Credential} with the given loginName and password
   */
  public static Credential withLoginNameAndPassword(final String loginName, final String password) {
    return new Credential(loginName, password);
  }

  /**
   * @return the login name of the current {@link Credential}
   */
  public String getLoginName() {
    return loginName;
  }

  /**
   * @return the password of the current {@link Credential}
   */
  public String getPassword() {
    return password;
  }
}
