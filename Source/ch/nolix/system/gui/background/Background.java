package ch.nolix.system.gui.background;

import java.util.Locale;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.system.element.base.Element;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.ColorGradient;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.backgroundapi.BackgroundType;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;
import ch.nolix.systemapi.guiapi.canvasapi.DirectionInCanvas;

public final class Background extends Element implements IBackground {

  public static final Background TRANSPARENT_BACKGROUND = new Background();

  public static final ImageApplication DEFAULT_IMAGE_APPLICATION = ImageApplication.SCALE_TO_FRAME;

  private static final String COLOR_HEADER = "Color";

  private static final String COLOR_GRADIENT_HEADER = "ColorGradient";

  private static final String IMAGE_HEADER = "Image";

  private static final String TRANSPARENCY_HEADER = "Transparency";

  private final IColor color;

  private final IColorGradient colorGradient;

  private final IImage image;

  private final ImageApplication imageApplication;

  private Background() {
    color = null;
    colorGradient = null;
    image = null;
    imageApplication = null;
  }

  private Background(final IColor color) {

    GlobalValidator.assertThat(color).thatIsNamed(IColor.class).isNotNull();

    this.color = color;
    colorGradient = null;
    image = null;
    imageApplication = null;
  }

  private Background(final IColorGradient colorGradient) {

    GlobalValidator.assertThat(colorGradient).thatIsNamed(IColorGradient.class).isNotNull();

    color = null;
    this.colorGradient = colorGradient;
    image = null;
    imageApplication = null;
  }

  private Background(final IImage image, final ImageApplication imageApplication) {

    GlobalValidator.assertThat(image).thatIsNamed(IImage.class).isNotNull();
    GlobalValidator.assertThat(imageApplication).thatIsNamed(ImageApplication.class).isNotNull();

    color = null;
    colorGradient = null;
    this.image = image;
    this.imageApplication = imageApplication;
  }

  public static Background fromSpecification(final INode<?> specification) {

    final var childNode = specification.getStoredFirstChildNode();

    return switch (childNode.getHeader()) {
      case COLOR_HEADER ->
        withColor(Color.fromSpecification(childNode));
      case COLOR_GRADIENT_HEADER ->
        withColorGradient(ColorGradient.fromSpecification(childNode));
      case IMAGE_HEADER ->
        withImageAndImageApplication(
          Image.fromSpecification(specification.getStoredChildNodeAt1BasedIndex(1)),
          ImageApplication.fromSpecification(specification.getStoredChildNodeAt1BasedIndex(2)));
      case TRANSPARENCY_HEADER ->
        TRANSPARENT_BACKGROUND;
      default ->
        throw createExceptionForSpecificationDoesNotSpecifyBackground(specification);
    };
  }

  public static Background withColor(final IColor color) {
    return new Background(color);
  }

  public static Background withColorGradient(final IColorGradient colorGradient) {
    return new Background(colorGradient);
  }

  public static Background withImage(final IImage image) {
    return withImageAndImageApplication(image, DEFAULT_IMAGE_APPLICATION);
  }

  public static Background withImageAndImageApplication(
    final IImage image,
    final ImageApplication imageApplication) {
    return new Background(image, imageApplication);
  }

  private static UnrepresentingArgumentException createExceptionForSpecificationDoesNotSpecifyBackground(
    final INode<?> specification) {
    return UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
      LowerCaseVariableCatalogue.SPECIFICATION,
      specification,
      Background.class);
  }

  @Override
  public IContainer<INode<?>> getAttributes() {
    return switch (getType()) {
      case COLOR ->
        LinkedList.withElement(getColor().getSpecification());
      case COLOR_GRADIENT ->
        LinkedList.withElement(getColorGradient().getSpecification());
      case IMAGE ->
        LinkedList.withElement(getImage().getSpecification(), Node.fromEnum(getImageApplication()));
      case TRANSPARENCY ->
        LinkedList.withElement(Node.withHeader(TRANSPARENCY_HEADER));
      default ->
        throw InvalidArgumentException.forArgument(this);
    };
  }

  @Override
  public IColor getColor() {

    assertIsColor();

    return color;
  }

  @Override
  public IColorGradient getColorGradient() {

    assertIsColorGradient();

    return colorGradient;
  }

  @Override
  public IImage getImage() {

    assertIsImage();

    return image;
  }

  @Override
  public ImageApplication getImageApplication() {

    assertIsImage();

    return imageApplication;
  }

  @Override
  public BackgroundType getType() { //NOSONAR: The type of a Background is determined by all of its other attributes.

    if (isColor()) {
      return BackgroundType.COLOR;
    }

    if (isColorGradient()) {
      return BackgroundType.COLOR_GRADIENT;
    }

    if (isImage()) {
      return BackgroundType.IMAGE;
    }

    return BackgroundType.TRANSPARENCY;
  }

  public boolean isColor() {
    return (color != null);
  }

  public boolean isColorGradient() {
    return (colorGradient != null);
  }

  public boolean isImage() {
    return (image != null);
  }

  public boolean isTransparent() {
    return !isColor() && !isColorGradient() && !isImage();
  }

  @Override
  public IContainer<ICssProperty> toCssProperties() {
    return switch (getType()) {
      case COLOR ->
        toCssPropertiesWhenIsColor();
      case COLOR_GRADIENT ->
        toCssPropertiesWhenIsColorGradient();
      case IMAGE ->
        toCssPropertiesWhenIsImage();
      case TRANSPARENCY ->
        toCssPropertiesWhenIsTransparent();
      default ->
        throw InvalidArgumentException.forArgument(this);
    };
  }

  private void assertIsColor() {
    if (!isColor()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IColor.class);
    }
  }

  private void assertIsColorGradient() {
    if (!isColorGradient()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IColorGradient.class);
    }
  }

  private void assertIsImage() {
    if (!isImage()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IImage.class);
    }
  }

  private String getColorCodeOfColor(final IColor color) {

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

  private Object getDegreeCodeOfColorGradient(final IColorGradient pColorGradient) {
    return (getDegreeOfColorGradient(pColorGradient) + "deg");
  }

  private int getDegreeOfColorGradient(final IColorGradient pColorGradient) {
    return getDegreeOfDirection(pColorGradient.getDirection());
  }

  private int getDegreeOfDirection(final DirectionInCanvas direction) {
    return switch (direction) {
      case VERTICAL ->
        180;
      case HORIZONTAL ->
        90;
      case DIAGONAL_DOWN ->
        135;
      case DIAGONAL_UP ->
        45;
      default ->
        throw InvalidArgumentException.forArgument(direction);
    };
  }

  private IContainer<ICssProperty> toCssPropertiesWhenIsColor() {
    final var colorCode = getColorCodeOfColor(color);

    return ImmutableList.withElement(CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND, colorCode));
  }

  private IContainer<ICssProperty> toCssPropertiesWhenIsColorGradient() {
    final var degreeCode = getDegreeCodeOfColorGradient(colorGradient);
    final var color1Code = getColorCodeOfColor(colorGradient.getColor1());
    final var color2Code = getColorCodeOfColor(colorGradient.getColor2());
    final var linearGradientCode = "linear-gradient(" + degreeCode + "," + color1Code + "," + color2Code + ")";

    return ImmutableList.withElement(
      CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND_IMAGE, linearGradientCode));
  }

  private IContainer<ICssProperty> toCssPropertiesWhenIsImage() {
    final var backgroundImage = "data:image/jpeg;base64," + image.toJPGString();

    return ImmutableList.withElement(
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BACKGROUND_IMAGE,
        "url('" + backgroundImage + "')"),
      CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND_SIZE, "100% 100%"));
  }

  private IContainer<ICssProperty> toCssPropertiesWhenIsTransparent() {
    return ImmutableList.withElement(CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND, "none"));
  }
}
