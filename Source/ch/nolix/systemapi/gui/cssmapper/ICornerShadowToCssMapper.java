package ch.nolix.systemapi.gui.cssmapper;

import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.box.ICornerShadow;

/**
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public interface ICornerShadowToCssMapper {

  /**
   * @param cornerShadow
   * @return a new {@link ICssProperty} from the given cornerShadow.
   * @throws RuntimeException if the given cornerShadow is null.
   */
  ICssProperty mapCornerShadowToCssProperty(ICornerShadow cornerShadow);
}
