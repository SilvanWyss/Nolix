/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.math.machineprecision;

/**
 * @author Silvan Wyss
 */
public final class ComparsionThresholdCatalog {
  public static final double COMMON_DOUBLE_COMPARSION_THRESHOLD = 0.000_001; //10E^6

  public static final float COMMON_FLOAT_COMPARSION_THRESHOLD = 0.001F; //10E-3

  private ComparsionThresholdCatalog() {
  }
}
