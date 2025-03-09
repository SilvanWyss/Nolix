package ch.nolix.core.commontypetool.stringtool;

import java.util.Locale;

import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringExaminer;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;

/**
 * @author Silvan Wyss
 * @version 2025-03-01
 */
public final class StringExaminer implements IStringExaminer {

  private static final IStringTool STRING_TOOL = new StringToolUnit();

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
    && string.equals(STRING_TOOL.toPascalCase(string));
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
