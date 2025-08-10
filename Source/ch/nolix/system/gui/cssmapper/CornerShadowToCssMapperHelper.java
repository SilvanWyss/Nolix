package ch.nolix.system.gui.cssmapper;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.gui.cssmapper.IColorToCssMapper;

/**
 * Of the {@link CornerShadowToCssMapperHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public final class CornerShadowToCssMapperHelper {

  private static final IColorToCssMapper COLOR_TO_CSS_MAPPER = new ColorToCssMapper();

  /**
   * Prevents that an instance of the {@link CornerShadowToCssMapperHelper} can be
   * created.
   */
  private CornerShadowToCssMapperHelper() {
  }

  /**
   * @param cornerShadow
   * @return a new {@link ICssProperty} from the given cornerShadow when the
   *         shadow is inside the box.
   * @throws RuntimeException if the given cornerShadow is null.
   */
  public static ICssProperty mapCornerShadowToCssPropertyWhenShadowIsInsideTheBox(final ICornerShadow cornerShadow) {

    final var side1Thickness = cornerShadow.getSide1Thickness();
    final var side2Thickness = cornerShadow.getSide2Thickness();
    final var blurRadius = cornerShadow.getBlurRadius();
    final var color = cornerShadow.getColor();
    final var colorCssValue = COLOR_TO_CSS_MAPPER.mapColorToCssValueCode(color);

    return //
    switch (cornerShadow.getCorner()) {
      case BOTTOM_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", side1Thickness, -side2Thickness, blurRadius, colorCssValue));
      case BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", -side1Thickness, -side2Thickness, blurRadius, colorCssValue));
      case TOP_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", side1Thickness, side2Thickness, blurRadius, colorCssValue));
      case TOP_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", -side1Thickness, side2Thickness, blurRadius, colorCssValue));
    };
  }

  /**
   * @param cornerShadow
   * @return a new {@link ICssProperty} from the given cornerShadow when the
   *         shadow is outside the box.
   * @throws RuntimeException if the given cornerShadow is null.
   */
  public static ICssProperty mapCornerShadowToCssPropertyWhenShadowIsOutsideTheBox(final ICornerShadow cornerShadow) {

    final var side1Thickness = cornerShadow.getSide1Thickness();
    final var side2Thickness = cornerShadow.getSide2Thickness();
    final var blurRadius = cornerShadow.getBlurRadius();
    final var color = cornerShadow.getColor();
    final var colorCssValue = COLOR_TO_CSS_MAPPER.mapColorToCssValueCode(color);

    return //
    switch (cornerShadow.getCorner()) {
      case BOTTOM_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", -side1Thickness, side2Thickness, blurRadius, colorCssValue));
      case BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", side1Thickness, side2Thickness, blurRadius, colorCssValue));
      case TOP_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", -side1Thickness, -side2Thickness, blurRadius, colorCssValue));
      case TOP_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", side1Thickness, -side2Thickness, blurRadius, colorCssValue));
    };
  }
}
