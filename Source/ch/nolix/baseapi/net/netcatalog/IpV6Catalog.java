/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.netcatalog;

/**
 * Of the {@link IpV6Catalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IpV6Catalog {
  public static final String LOOP_BACK_ADDRESS = "::1";

  /**
   * Prevents that an instance of the {@link IpV6Catalog} can be created.
   */
  private IpV6Catalog() {
  }
}
