package ch.nolix.coreapi.netapi.netconstantapi;

/**
 * Of the {@link IPv6Catalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2017-03-05
 */
public final class IPv6Catalogue {

  public static final String LOOP_BACK_ADDRESS = "::1";

  /**
   * Prevents that an instance of the {@link IPv6Catalogue} can be created.
   */
  private IPv6Catalogue() {
  }
}
