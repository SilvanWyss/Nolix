/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypetool.iterabletool;

/**
 * @author Silvan Wyss
 */
public interface IIterableTool {
  int getCount(Iterable<?> iterable);

  <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, int oneBasedIndex);
}
