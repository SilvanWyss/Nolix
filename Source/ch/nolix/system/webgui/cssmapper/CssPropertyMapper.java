package ch.nolix.system.webgui.cssmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.webgui.cssmapper.ICssPropertyMapper;

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
