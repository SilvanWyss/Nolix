/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.localcomputer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;

/**
 * @author Silvan Wyss
 */
public final class LocalComputer {
  private LocalComputer() {
  }

  public static String getLanIp() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (final UnknownHostException unknownHostException) {
      throw WrapperException.forError(unknownHostException);
    }
  }
}
