package ch.nolix.coreapi.netapi.netconstantapi;

/**
 * Of the {@link PortCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class PortCatalogue {

  public static final int MIN_PORT = 0;

  public static final int FTP = 20;

  public static final int SMTP = 25;

  public static final int HTTP = 80;

  public static final int HTTPS = 443;

  public static final int MAX_PORT = 65535;

  public static final int MS_SQL = 1433;

  /**
   * Prevents that an instance of the {@link PortCatalogue} can be created.
   */
  private PortCatalogue() {
  }
}
