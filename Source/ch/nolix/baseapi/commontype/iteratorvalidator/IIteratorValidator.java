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
   * @param iterator
   * @throws RuntimeException if the given iterator does not have a next element.
   */
  void assertHasNext(Iterator<?> iterator);
}
