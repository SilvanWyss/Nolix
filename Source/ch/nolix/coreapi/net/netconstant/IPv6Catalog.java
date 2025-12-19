package ch.nolix.coreapi.net.netconstant;

/**
 * Of the {@link IPv6Catalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IPv6Catalog {
  public static final String LOOP_BACK_ADDRESS = "::1";

  /**
   * Prevents that an instance of the {@link IPv6Catalog} can be created.
   */
  private IPv6Catalog() {
  }
}
