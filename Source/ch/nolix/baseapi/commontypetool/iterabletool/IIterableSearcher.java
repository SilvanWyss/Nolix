/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypetool.iterabletool;

/**
 * @author Silvan Wyss
 */
public interface IIterableSearcher extends IIterableCountSearcher {
  <E> E getStoredAtOneBasedIndex(Iterable<E> iterable, int oneBasedIndex);
}
