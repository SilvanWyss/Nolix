/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IApplicationTarget extends IServerTarget {
  /**
   * @return the name of the application of the current {@link IApplicationTarget}
   */
  String getApplicationName();

  /**
   * @return the name of the application of the current {@link IApplicationTarget}
   *         for URLs
   */
  String getUrlApplicationName();
}
