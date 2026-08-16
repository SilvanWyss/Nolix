/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.exceptionargumentpreparatorimpl;

import ch.nolix.baseapi.errorcontrol.exceptionargumentpreparator.IExceptionArgumentStringRepresentaionPreparator;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class ExceptionArgumentStringRepresentaionPreparator
implements IExceptionArgumentStringRepresentaionPreparator {
  private static final int MAX_STRING_REPRESENTATION_LENGTH = 10_000;

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(final Object argument) {
    if (argument == null) {
      return StringCatalog.SPACE;
    }

    final var string = getStringRepresentationWithMaxLengthOfArgument(argument);

    if (string.isBlank()) {
      return StringCatalog.SPACE;
    }

    return (" '" + string + "' ");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStringRepresentationOfArgument(final Object argument) {
    if (argument != null) {
      return getStringRepresentationOfArgumentWhenIsNotNull(argument);
    }

    return StringCatalog.LOWER_CASE_NULL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStringRepresentationWithMaxLengthOfArgument(final Object argument) {
    final var string = getStringRepresentationOfArgument(argument);

    if (string.length() > MAX_STRING_REPRESENTATION_LENGTH) {
      return (string.substring(0, MAX_STRING_REPRESENTATION_LENGTH) + CharacterCatalog.ELLIPSIS);
    }

    return string;
  }

  /**
   * @param argument
   * @return a {@link String} representation of the given argument for the case
   *         that given argument is not null.
   */
  private String getStringRepresentationOfArgumentWhenIsNotNull(final Object argument) {
    try {
      final var string = argument.toString();

      if (string != null) {
        return string;
      }

      return StringCatalog.LOWER_CASE_NULL;
    } catch (final Throwable _) { // NOSONAR: All Throwable must be caught.
      return StringCatalog.EMPTY_STRING;
    }
  }
}
