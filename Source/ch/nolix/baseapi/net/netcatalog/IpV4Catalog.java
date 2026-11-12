/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.netcatalog;

/**
 * Of the {@link IpV4Catalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IpV4Catalog {
  public static final String LOOP_BACK_ADDRESS = "127.0.0.1";

  /**
   * Prevents that an instance of the {@link IpV4Catalog} can be created.
   */
  private IpV4Catalog() {
  }
}
