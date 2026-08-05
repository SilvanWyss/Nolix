/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.list;

import ch.nolix.baseapi.foundation.marker.Marker;

/**
 * @author Silvan Wyss
 */
public interface ArrayListCreator {
  /**
   * @param marker
   * @param initialCapacity
   * @param <E>             the type of the elements the created
   *                        {@link IArrayList} can contain
   * @return a new empty {@link IArrayList} with the given initialCapacity
   */
  <E> IArrayList<E> createEmptyArrayListFromMarkerWithInitialCapacity(Marker<E> marker, int initialCapacity);
}
