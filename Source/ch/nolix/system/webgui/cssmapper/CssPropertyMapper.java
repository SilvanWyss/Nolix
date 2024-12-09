package ch.nolix.system.webgui.cssmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.guiapi.fontapi.LineDecoration;
import ch.nolix.systemapi.webguiapi.cssmapperapi.ICssPropertyMapper;

/**
 * @author Silvan Wyss
 * @version 2024-12-09
 */
public final class CssPropertyMapper implements ICssPropertyMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ICssProperty mapLineDecorationToCssProperty(final LineDecoration lineDecoration) {
    return //
    switch (lineDecoration) {
      case UNDERLINE -> CssProperty.withNameAndValue("text-decoration-line", "underline");
      case MIDLINE -> CssProperty.withNameAndValue("text-decoration-line", "line-through");
      case OVERLINE -> CssProperty.withNameAndValue("text-decoration-line", "overline");
      default -> throw InvalidArgumentException.forArgument(lineDecoration);
    };
  }
}
