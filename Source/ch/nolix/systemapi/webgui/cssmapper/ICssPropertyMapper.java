package ch.nolix.systemapi.webgui.cssmapper;

import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.systemapi.gui.font.LineDecoration;

/**
 * A {@link ICssPropertyMapper} can map objects to {@link ICssProperty}s.
 * 
 * @author Silvan Wyss
 * @version 2024-12-09
 */
public interface ICssPropertyMapper {

  /**
   * @param lineDecoration
   * @return a new or immutable {@link ICssProperty} mapped from the given
   *         lineDecoration.
   * @throws RuntimeException if the given lineDecoration is null.
   */
  ICssProperty mapLineDecorationToCssProperty(LineDecoration lineDecoration);
}
