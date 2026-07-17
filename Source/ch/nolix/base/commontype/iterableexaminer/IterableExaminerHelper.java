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

  public static int getCount(final Object[] objects) {
    if (objects != null) {
      return objects.length;
    }

    return 0;
  }

  public static boolean isEmpty(final Object[] objects) {
    if (objects != null) {
      return objects.length == 0;
    }

    return true;
  }
}
