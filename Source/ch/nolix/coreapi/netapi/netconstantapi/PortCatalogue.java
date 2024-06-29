//package declaration
package ch.nolix.coreapi.netapi.netconstantapi;

//class
/**
 * Of the {@link PortCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class PortCatalogue {

  //constant
  public static final int MIN_PORT = 0;

  //constant
  public static final int FTP = 20;

  //constant
  public static final int SMTP = 25;

  //constant
  public static final int HTTP = 80;

  //constant
  public static final int HTTPS = 443;

  //constant
  public static final int MAX_PORT = 65535;

  //constant
  public static final int MS_SQL = 1433;

  //constructor
  /**
   * Prevents that an instance of the {@link PortCatalogue} can be created.
   */
  private PortCatalogue() {
  }
}
