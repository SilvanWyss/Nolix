/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IApplicationTarget extends IServerTarget {
  String getApplicationname();

  String getUrlApplicationName();
}
