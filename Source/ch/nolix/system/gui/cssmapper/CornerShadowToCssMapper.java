package ch.nolix.system.gui.cssmapper;

import java.util.Optional;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.gui.cssmapper.ICornerShadowToCssMapper;

/**
 * A {@link CornerShadowToCssMapper} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public final class CornerShadowToCssMapper implements ICornerShadowToCssMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ICssProperty mapCornerShadowToCssProperty(final ICornerShadow cornerShadow) {
    return //
    switch (cornerShadow.getLocation()) {
      case OUTSIDE ->
        CornerShadowToCssMapperHelper.mapCornerShadowToCssPropertyWhenShadowIsOutsideTheBox(cornerShadow);
      case INSIDE ->
        CornerShadowToCssMapperHelper.mapCornerShadowToCssPropertyWhenShadowIsInsideTheBox(cornerShadow);
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<ICssProperty> mapCornerShadowsToOptionalCssProperty(
    final IContainer<? extends ICornerShadow> cornerShadows) {

    if (cornerShadows.containsAny()) {
      return //
      Optional.of(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          cornerShadows.getViewOf(s -> mapCornerShadowToCssProperty(s).getValue()).toString()));
    }

    return Optional.empty();
  }
}
