//package declaration
package ch.nolix.core.environment.localcomputer;

//own imports
import java.net.InetAddress;
import java.net.UnknownHostException;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class LocalComputer {

  //constructor
  private LocalComputer() {
  }

  //static method
  public static String getLanIp() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (final UnknownHostException unknownHostException) {
      throw WrapperException.forError(unknownHostException);
    }
  }
}
