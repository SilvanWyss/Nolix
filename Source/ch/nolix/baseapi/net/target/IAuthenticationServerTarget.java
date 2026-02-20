/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IAuthenticationServerTarget extends IServerTarget {
  String getLoginName();

  String getLoginPassword();
}
