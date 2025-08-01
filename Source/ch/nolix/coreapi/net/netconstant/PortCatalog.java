package ch.nolix.coreapi.net.netconstant;

/**
 * Of the {@link PortCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class PortCatalog {

  public static final int MIN_PORT = 0;

  public static final int FTP = 20;

  public static final int SMTP = 25;

  public static final int HTTP = 80;

  public static final int HTTPS = 443;

  public static final int MAX_PORT = 65535;

  public static final int MS_SQL = 1433;

  /**
   * Prevents that an instance of the {@link PortCatalog} can be created.
   */
  private PortCatalog() {
  }
}
