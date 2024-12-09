package ch.nolix.systemapi.webguiapi.cssmapperapi;

import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.guiapi.fontapi.LineDecoration;

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
