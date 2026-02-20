/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IApplicationInstanceTarget extends IServerTarget {
  String getApplicationInstanceName();

  String getApplicationUrlInstanceName();
}
