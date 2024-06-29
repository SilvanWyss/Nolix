//package declaration
package ch.nolix.coreapi.netapi.netconstantapi;

//class
/**
 * Of the {@link IPv4Catalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2018-12-02
 */
public final class IPv4Catalogue {

  //constant
  public static final String LOOP_BACK_ADDRESS = "127.0.0.1";

  //constructor
  /**
   * Prevents that an instance of the {@link IPv4Catalogue} can be created.
   */
  private IPv4Catalogue() {
  }
}
