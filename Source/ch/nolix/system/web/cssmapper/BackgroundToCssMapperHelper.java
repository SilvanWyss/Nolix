package ch.nolix.system.web.cssmapper;

import java.util.Locale;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalog;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageproperty.Alignment;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;

public final class BackgroundToCssMapperHelper {

  private static final ICssProperty TRANSPARENT_BACKGROUND_CSS_PROPERTY = //
  CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND, "none");

  public static final ImmutableList<ICssProperty> TRANSPARENT_BACKGROUND_CSS_PROPERTIES = ImmutableList
    .withElement(TRANSPARENT_BACKGROUND_CSS_PROPERTY);

  private BackgroundToCssMapperHelper() {
  }

  private static String getColorCodeOfColor(final IColor color) {

    if (color.hasFullAlphaValue()) {
      return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
    }

    return String.format(
      Locale.ENGLISH,
      "rgba(%d, %d, %d, %f)",
      color.getRedValue(),
      color.getGreenValue(),
      color.getBlueValue(),
      color.getAlphaPercentage());
  }

  private static String getDegreeCodeOfColorGradient(final IColorGradient pColorGradient) {
    return (getDegreeOfColorGradient(pColorGradient) + "deg");
  }

  private static int getDegreeOfColorGradient(final IColorGradient pColorGradient) {
    return getDegreeOfDirection(pColorGradient.getDirection());
  }

  private static int getDegreeOfDirection(final Alignment direction) {
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
    final var colorCode = getColorCodeOfColor(color);
    final var backgroundCssProperty = CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND, colorCode);

    return ImmutableList.withElement(backgroundCssProperty);
  }

  public static IContainer<ICssProperty> mapBackgroundToCssPropertiesWhenIsColorGradient(final IBackground background) {

    final var colorGradient = background.getColorGradient();
    final var degreeCode = getDegreeCodeOfColorGradient(colorGradient);
    final var color1Code = getColorCodeOfColor(colorGradient.getColor1());
    final var color2Code = getColorCodeOfColor(colorGradient.getColor2());
    final var linearGradientCode = "linear-gradient(" + degreeCode + "," + color1Code + "," + color2Code + ")";

    final var backgroundCssProperty = //
    CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND, linearGradientCode);

    return ImmutableList.withElement(backgroundCssProperty);
  }

  public static IContainer<ICssProperty> mapBackgroundToCssPropertiesWhenIsImage(final IBackground background) {

    final var image = background.getImage();
    final var imageCode = "data:image/jpeg;base64," + image.toBase64Jpg();
    final var imageUrl = "url('" + imageCode + "')";

    final var backgroundImageCssProperty = //
    CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND_IMAGE, imageUrl);

    return //
    ImmutableList.withElement(
      backgroundImageCssProperty,
      CssProperty.withNameAndValue(CssPropertyNameCatalog.BACKGROUND_SIZE, "100% 100%"));
  }
}
