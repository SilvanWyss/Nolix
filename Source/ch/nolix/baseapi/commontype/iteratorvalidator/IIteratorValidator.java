/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iteratorvalidator;

import java.util.Iterator;

/**
 * @author Silvan Wyss
 */
public interface IIteratorValidator {
  /**
   * @param iterator the examined iterator, is considered to not have a next
   *                 element when is null
   * @throws RuntimeException if the given iterator does not have a next element
   */
  void assertHasNext(Iterator<?> iterator);
}
