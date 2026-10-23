/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IAuthenticationServerTarget extends IServerTarget {
  /**
   * @return the login name of the current {@link IAuthenticationServerTarget}
   */
  String getLoginName();

  /**
   * @return the login password of the current {@link IAuthenticationServerTarget}
   */
  String getLoginPassword();
}
