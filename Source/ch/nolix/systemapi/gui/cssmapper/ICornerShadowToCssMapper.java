package ch.nolix.systemapi.gui.cssmapper;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
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

  /**
   * @param cornerShadows
   * @return a {@link Optional} with a new {@link ICssProperty} from the given
   *         cornerShadows if the given cornerShadows is not empty, an empty
   *         {@link Optional} otherwise.
   * @throws RuntimeException if the given cornerShadows is null or one of the
   *                          given cornerShadows is null.
   */
  Optional<ICssProperty> mapCornerShadowsToOptionalCssProperty(IContainer<? extends ICornerShadow> cornerShadows);
}
