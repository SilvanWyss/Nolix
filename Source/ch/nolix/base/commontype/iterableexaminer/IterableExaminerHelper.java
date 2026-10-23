/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.iterableexaminer;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Of the {@link IterableExaminerHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IterableExaminerHelper {
  private IterableExaminerHelper() {
  }

  /**
   * @param iterable
   * @return true if the elements of the given iterable equal the elements of the
   *         given iterable in the same order, false otherwise
   */
  public static boolean containsExactlyAllEqualInSameOrderWhenIterableNotNullAndObjectsNotNull(Iterable<?> iterable) {
    final var objectsIterator = iterable.iterator();

    for (final var e : iterable) {
      if (objectsIterator.hasNext()) {
        if (!Objects.equals(e, objectsIterator.next())) {
          return false;
        }
      } else {
        return false;
      }
    }

    return !objectsIterator.hasNext();
  }

  /**
   * @param iterable
   * @param object
   * @return true if the given iterable contains the given object exactly once,
   *         false otherwise
   */
  public static boolean containsOnceWhenNotNull(final Iterable<?> iterable, final Object object) {
    var found = false;

    for (final var e : iterable) {
      if (e == object) {
        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }

  /**
   * @param iterable
   * @param object
   * @return true if the given iterable contains exactly one element that equals
   *         the given object, false otherwise
   */
  public static boolean containsOneEqualWhenNotNull(final Iterable<?> iterable, final Object object) {
    var found = false;

    for (final var e : iterable) {
      if (Objects.equals(e, object)) {
        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }

  /**
   * @param iterable
   * @param selector
   * @param <T>      the type of the elements of the given iterable
   * @return true if the given iterable contains exactly one element the given
   *         selector selects, false otherwise
   */
  public static <T> boolean containsOneMatchingWhenNotNullAndSelectorNotNull(
    final Iterable<T> iterable,
    final Predicate<T> selector) {
    var found = false;

    for (final var e : iterable) {
      if (e != null && selector.test(e)) {
        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }

  /**
   * @param iterable
   * @return true if the given iterable contains exactly one element that is not
   *         null, false otherwise
   */
  public static boolean containsOneNoneNullWhenNotNull(final Iterable<?> iterable) {
    var found = false;

    for (final var e : iterable) {
      if (e != null) {
        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }

  /**
   * @param iterable
   * @return the number of elements of the given iterable
   */
  public static int getCount(final Iterable<?> iterable) {
    if (iterable != null) {
      var count = 0;

      for (final var _ : iterable) {
        count++;
      }

      return count;
    }

    return 0;
  }

  /**
   * @param objects
   * @return the number of the given objects
   */
  public static int getCount(final Object[] objects) {
    if (objects != null) {
      return objects.length;
    }

    return 0;
  }

  /**
   * @param objects
   * @return true if the given objects is empty, false otherwise
   */
  public static boolean isEmpty(final Object[] objects) {
    if (objects != null) {
      return objects.length == 0;
    }

    return true;
  }
}
