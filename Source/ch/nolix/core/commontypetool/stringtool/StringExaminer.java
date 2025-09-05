package ch.nolix.core.commontypetool.stringtool;

import java.util.Locale;

import ch.nolix.coreapi.commontypetool.stringtool.IStringExaminer;

/**
 * @author Silvan Wyss
 * @version 2025-03-01
 */
public final class StringExaminer implements IStringExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLowerCase(final String string) {
    return //
    string != null
    && string.equals(string.toLowerCase(Locale.ENGLISH));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPascalCase(final String string) {
    return //
    string != null
    && string.equals(StringTool.toPascalCase(string));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean startsWithIgnoringCase(final String string, final String prefix) {
    return //
    string != null
    && prefix != null
    && string.regionMatches(true, 0, prefix, 0, prefix.length());
  }
}
