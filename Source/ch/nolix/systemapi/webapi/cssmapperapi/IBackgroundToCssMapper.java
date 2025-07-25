package ch.nolix.systemapi.webapi.cssmapperapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IBackgroundToCssMapper {

  /**
   * @param background
   * @return the {@link ICssProperty}s from the given background.
   * @throws RuntimeException if the given background is null.
   */
  IContainer<ICssProperty> mapBackgroundToCssProperties(IBackground background);
}
