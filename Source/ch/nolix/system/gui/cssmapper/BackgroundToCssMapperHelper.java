package ch.nolix.system.gui.cssmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.box.Direction;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;
import ch.nolix.systemapi.gui.cssmapper.ICssValueMapper;

/**
 * @author Silvan Wyss
 */
public final class BackgroundToCssMapperHelper {
  private static final ICssProperty TRANSPARENT_BACKGROUND_CSS_PROPERTY = //
  CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND, "none");

  private static final ICssValueMapper CSS_VALUE_MAPPER = new CssValueMapper();

  public static final ImmutableList<ICssProperty> TRANSPARENT_BACKGROUND_CSS_PROPERTIES = ImmutableList
    .withElements(TRANSPARENT_BACKGROUND_CSS_PROPERTY);

  private BackgroundToCssMapperHelper() {
  }

  private static String getDegreeCodeOfColorGradient(final IColorGradient pColorGradient) {
    return (getDegreeOfColorGradient(pColorGradient) + "deg");
  }

  private static int getDegreeOfColorGradient(final IColorGradient pColorGradient) {
    return getDegreeOfDirection(pColorGradient.getDirection());
  }

  private static int getDegreeOfDirection(final Direction direction) {
    return switch (direction) {
      case VERTICAL ->
        180;
      case HORIZONTAL ->
        90;
      case DIAGONAL_LEFT_DOWN ->
        135;
      case DIAGONAL_LEFT_UP ->
        45;
      default ->
        throw InvalidArgumentException.forArgument(direction);
    };
  }

  public static IContainer<ICssProperty> mapBackgroundToCssPropertiesWhenIsColor(final IBackground background) {
    final var color = background.getColor();
    final var colorCssValue = CSS_VALUE_MAPPER.mapColorToCssValue(color);
    final var backgroundCssProperty = CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND, colorCssValue);

    return ImmutableList.withElements(backgroundCssProperty);
  }

  public static IContainer<ICssProperty> mapBackgroundToCssPropertiesWhenIsColorGradient(final IBackground background) {
    final var colorGradient = background.getColorGradient();
    final var degreeCode = getDegreeCodeOfColorGradient(colorGradient);
    final var color1CssValue = CSS_VALUE_MAPPER.mapColorToCssValue(colorGradient.getColor1());
    final var color2CssValue = CSS_VALUE_MAPPER.mapColorToCssValue(colorGradient.getColor2());
    final var linearGradientCode = "linear-gradient(" + degreeCode + "," + color1CssValue + "," + color2CssValue + ")";

    final var backgroundCssProperty = //
    CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND, linearGradientCode);

    return ImmutableList.withElements(backgroundCssProperty);
  }

  public static IContainer<ICssProperty> mapBackgroundToCssPropertiesWhenIsImage(final IBackground background) {
    final var image = background.getImage();
    final var imageCode = "data:image/jpeg;base64," + image.toBase64Jpg();
    final var imageUrl = "url('" + imageCode + "')";

    final var backgroundImageCssProperty = //
    CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND_IMAGE, imageUrl);

    final var imageApplication = background.getImageApplication();
    final var backgroundRepeatCssProperty = mapImageApplicationToBackgroundRepeatCssProperty(imageApplication);

    return ImmutableList.withElements(backgroundImageCssProperty, backgroundRepeatCssProperty);
  }

  public static ICssProperty mapImageApplicationToBackgroundRepeatCssProperty(final ImageApplication imageApplication) {
    return //
    switch (imageApplication) {
      case SCALE_TO_FRAME ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND_SIZE, "100% 100%");
      case REPEAT ->
        CssProperty.withNameAndValue("background-repeat", "repeat");
      default ->
        throw InvalidArgumentException.forArgument(imageApplication);
    };
  }
}
