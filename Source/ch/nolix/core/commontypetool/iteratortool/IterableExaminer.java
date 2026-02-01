/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.commontypetool.iteratortool;

import ch.nolix.coreapi.commontypetool.iterabletool.IIterableExaminer;

/**
 * @author Silvan Wyss
 */
public final class IterableExaminer implements IIterableExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAny(final Iterable<?> iterable) {
    return //
    iterable != null
    && iterable.iterator().hasNext();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty(final Iterable<?> iterable) {
    return //
    iterable == null
    || !iterable.iterator().hasNext();
  }
}
