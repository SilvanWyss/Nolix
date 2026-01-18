/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.netconstant;

/**
 * Of the {@link IPv4Catalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IPv4Catalog {
  public static final String LOOP_BACK_ADDRESS = "127.0.0.1";

  /**
   * Prevents that an instance of the {@link IPv4Catalog} can be created.
   */
  private IPv4Catalog() {
  }
}
