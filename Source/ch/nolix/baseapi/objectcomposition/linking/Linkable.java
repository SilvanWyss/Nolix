/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.linking;

/**
 * @author Silvan Wyss
 */
public interface Linkable extends LinkedRequestable {
  void linkTo(Object object);
}
