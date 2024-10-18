package ch.nolix.core.environment.localcomputer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.nolix.core.errorcontrol.exception.WrapperException;

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
