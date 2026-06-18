/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.iterableexaminer;

import java.util.function.Predicate;

import ch.nolix.baseapi.commontype.iterableexaminer.IIterableExaminer;

/**
 * @author Silvan Wyss
 */
public final class IterableExaminer implements IIterableExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(final Iterable<?> iterable, final Object object) {
    if (iterable != null) {
      for (final var e : iterable) {
        if (e == object) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAll(final Iterable<?> iterable, final Iterable<?> objects) {
    if (objects != null) {
      for (final var o : objects) {
        if (!contains(iterable, o)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAll(final Iterable<? extends Object> iterable, final Object... objects) {
    if (objects != null) {
      for (final var o : objects) {
        if (!contains(iterable, o)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAny(final Iterable<?> iterable) {
    if (iterable != null) {
      final var iterator = iterable.iterator();

      return iterator.hasNext();
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAny(final Iterable<?> iterable, final Iterable<?> objects) {
    if (objects != null) {
      for (final var o : objects) {
        if (contains(iterable, o)) {
          return true;
        }
      }

      return false;
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAny(final Iterable<?> iterable, final Object... objects) {
    if (objects != null) {
      for (final var o : objects) {
        if (contains(iterable, o)) {
          return true;
        }
      }

      return false;
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsExactlyAll(final Iterable<?> iterable, final Iterable<?> objects) {
    return //
    containsAll(iterable, objects)
    && IterableExaminerHelper.getCount(iterable) == IterableExaminerHelper.getCount(objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsExactlyAll(final Iterable<?> iterable, final Object... objects) {
    return //
    containsAll(iterable, objects)
    && IterableExaminerHelper.getCount(iterable) == IterableExaminerHelper.getCount(objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsExactlyAllInSameOrder(final Iterable<?> iterable, final Iterable<?> objects) {
    if (iterable != null && objects != null) {
      final var objectsIterator = objects.iterator();

      for (final var e : iterable) {
        if (!objectsIterator.hasNext()) {
          return false;
        }

        final var object = objectsIterator.next();

        if (e != object) {
          return false;
        }
      }

      return !objectsIterator.hasNext();
    }

    return isEmpty(iterable) && isEmpty(objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsExactlyAllInSameOrder(final Iterable<?> iterable, final Object... objects) {
    if (iterable != null && objects != null) {
      final var objectsLength = objects.length;
      var zeroBasedobjectsIndex = 0;

      for (final var e : iterable) {
        if (zeroBasedobjectsIndex >= objectsLength) {
          return false;
        }

        final var object = objects[zeroBasedobjectsIndex];

        if (e != object) {
          return false;
        }

        zeroBasedobjectsIndex++;
      }

      return zeroBasedobjectsIndex == objectsLength;
    }

    return isEmpty(iterable) && IterableExaminerHelper.isEmpty(objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> boolean containsMatching(final Iterable<T> iterable, final Predicate<T> selector) {
    if (iterable != null && selector != null) {
      for (final var e : iterable) {
        if (e != null && selector.test(e)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> boolean containsMatchingOnly(final Iterable<T> iterable, final Predicate<T> selector) {
    if (iterable != null && selector != null) {
      for (final var e : iterable) {
        if (e == null || !selector.test(e)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsNone(final Iterable<?> iterable, final Iterable<?> objects) {
    if (objects != null) {
      for (final var o : objects) {
        if (contains(iterable, o)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsNone(final Iterable<?> iterable, final Object... objects) {
    if (objects != null) {
      for (final var o : objects) {
        if (contains(iterable, o)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsNonNull(final Iterable<?> iterable) {
    if (iterable != null) {
      for (final var e : iterable) {
        if (e != null) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsOnce(final Iterable<?> iterable, final Object object) {
    if (iterable != null) {
      return IterableExaminerHelper.containsOnceWhenNotNull(iterable, object);
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> boolean containsOneMatching(final Iterable<T> iterable, final Predicate<T> selector) {
    if (iterable != null && selector != null) {
      return IterableExaminerHelper.containsOneMatchingWhenNotNullAndSelectorNotNull(iterable, selector);
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsOnly(final Iterable<?> iterable, final Object object) {
    if (iterable != null) {
      for (final var e : iterable) {
        if (e != object) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty(final Iterable<?> iterable) {
    if (iterable != null) {
      final var iterator = iterable.iterator();

      return !iterator.hasNext();
    }

    return true;
  }
}
