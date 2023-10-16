//package declaration
package ch.nolix.core.environment.localcomputer;

//own imports
import java.net.InetAddress;
import java.net.UnknownHostException;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class LocalComputer {

  //static method
  public static String getLANIP() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (final UnknownHostException unknownHostException) {
      throw WrapperException.forError(unknownHostException);
    }
  }

  //constructor
  private LocalComputer() {
  }
}
