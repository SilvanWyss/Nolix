package ch.nolix.system.gui.cssmapper;

import java.util.Optional;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.gui.cssmapper.ICssPropertyMapper;
import ch.nolix.systemapi.gui.font.LineDecoration;

/**
 * A {@link CssPropertyMapper} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public final class CssPropertyMapper implements ICssPropertyMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ICssProperty> mapBackgroundToCssProperties(final IBackground background) {

    final var type = background.getType();

    return //
    switch (type) {
      case COLOR ->
        BackgroundToCssMapperHelper.mapBackgroundToCssPropertiesWhenIsColor(background);
      case COLOR_GRADIENT ->
        BackgroundToCssMapperHelper.mapBackgroundToCssPropertiesWhenIsColorGradient(background);
      case IMAGE ->
        BackgroundToCssMapperHelper.mapBackgroundToCssPropertiesWhenIsImage(background);
      case TRANSPARENCY ->
        BackgroundToCssMapperHelper.TRANSPARENT_BACKGROUND_CSS_PROPERTIES;
      default ->
        throw InvalidArgumentException.forArgument(type);
    };
  }

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
          cornerShadows.getViewOf(s -> mapCornerShadowToCssProperty(s).getValue()).toStringWithSeparator(",")));
    }

    return Optional.empty();
  }

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
