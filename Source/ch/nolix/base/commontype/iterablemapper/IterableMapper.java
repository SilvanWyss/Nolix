/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.iterablemapper;

import java.util.function.ToIntFunction;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontype.iterablemapper.IIterableMapper;

/**
 * @author Silvan Wyss
 */
public final class IterableMapper implements IIterableMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public <E> int[] toIntArray(final Iterable<E> iterable, final int n, final ToIntFunction<E> intMapper) {
    Validator.assertThat(intMapper).thatIsNamed("int mapper").isNotNull();

    if (iterable != null) {
      final var array = new int[n];
      final var iterator = iterable.iterator();

      for (var i = 0; i < n; i++) {
        final var element = iterator.next();

        if (element == null) {
          array[i] = 0;
        } else {
          array[i] = intMapper.applyAsInt(element);
        }
      }

      return array;
    }

    Validator.assertThat(n).thatIsNamed("n").isEqualTo(0);

    return new int[0];
  }
}
