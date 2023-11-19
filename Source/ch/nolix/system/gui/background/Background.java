//package declaration
package ch.nolix.system.gui.background;

//Java imports
import java.util.Locale;

//own imports
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
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.system.element.main.Element;
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

//class
public final class Background extends Element implements IBackground {

  //constant
  public static final Background TRANSPARENT_BACKGROUND = new Background();

  //constant
  public static final ImageApplication DEFAULT_IMAGE_APPLICATION = ImageApplication.SCALE_TO_FRAME;

  //constant
  private static final String COLOR_HEADER = "Color";

  //constant
  private static final String COLOR_GRADIENT_HEADER = "ColorGradient";

  //constant
  private static final String IMAGE_HEADER = "Image";

  //constant
  private static final String TRANSPARENCY_HEADER = "Transparency";

  //attribute
  private final IColor color;

  //attribute
  private final IColorGradient colorGradient;

  //attribute
  private final IImage image;

  //attribute
  private final ImageApplication imageApplication;

  //constructor
  private Background() {
    color = null;
    colorGradient = null;
    image = null;
    imageApplication = null;
  }

  //constructor
  private Background(final IColor color) {

    GlobalValidator.assertThat(color).thatIsNamed(IColor.class).isNotNull();

    this.color = color;
    colorGradient = null;
    image = null;
    imageApplication = null;
  }

  //constructor
  private Background(final IColorGradient colorGradient) {

    GlobalValidator.assertThat(colorGradient).thatIsNamed(IColorGradient.class).isNotNull();

    color = null;
    this.colorGradient = colorGradient;
    image = null;
    imageApplication = null;
  }

  //constructor
  private Background(final IImage image, final ImageApplication imageApplication) {

    GlobalValidator.assertThat(image).thatIsNamed(IImage.class).isNotNull();
    GlobalValidator.assertThat(imageApplication).thatIsNamed(ImageApplication.class).isNotNull();

    color = null;
    colorGradient = null;
    this.image = image;
    this.imageApplication = imageApplication;
  }

  //static method
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

  //static method
  public static Background withColor(final IColor color) {
    return new Background(color);
  }

  //static method
  public static Background withColorGradient(final IColorGradient colorGradient) {
    return new Background(colorGradient);
  }

  //static method
  public static Background withImage(final IImage image) {
    return withImageAndImageApplication(image, DEFAULT_IMAGE_APPLICATION);
  }

  //static method
  public static Background withImageAndImageApplication(
    final IImage image,
    final ImageApplication imageApplication) {
    return new Background(image, imageApplication);
  }

  //method
  private static UnrepresentingArgumentException createExceptionForSpecificationDoesNotSpecifyBackground(
    final INode<?> specification) {
    return UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
      LowerCaseCatalogue.SPECIFICATION,
      specification,
      Background.class);
  }

  //method
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

  //method
  @Override
  public IColor getColor() {

    assertIsColor();

    return color;
  }

  //method
  @Override
  public IColorGradient getColorGradient() {

    assertIsColorGradient();

    return colorGradient;
  }

  //method
  @Override
  public IImage getImage() {

    assertIsImage();

    return image;
  }

  //method
  @Override
  public ImageApplication getImageApplication() {

    assertIsImage();

    return imageApplication;
  }

  //method
  @Override
  public BackgroundType getType() { //NOSONAR: The type of a Background is determined by all of its other
                                    //attributes.

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

  //method
  public boolean isColor() {
    return (color != null);
  }

  //method
  public boolean isColorGradient() {
    return (colorGradient != null);
  }

  //method
  public boolean isImage() {
    return (image != null);
  }

  //method
  public boolean isTransparent() {
    return !isColor() && !isColorGradient() && !isImage();
  }

  //method
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

  //method
  private void assertIsColor() {
    if (!isColor()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IColor.class);
    }
  }

  //method
  private void assertIsColorGradient() {
    if (!isColorGradient()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IColorGradient.class);
    }
  }

  //method
  private void assertIsImage() {
    if (!isImage()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IImage.class);
    }
  }

  //method
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

  //method
  private Object getDegreeCodeOfColorGradient(final IColorGradient pColorGradient) {
    return (getDegreeOfColorGradient(pColorGradient) + "deg");
  }

  //method
  private int getDegreeOfColorGradient(final IColorGradient pColorGradient) {
    return getDegreeOfDirection(pColorGradient.getDirection());
  }

  //method
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

  //method
  private IContainer<ICssProperty> toCssPropertiesWhenIsColor() {
    final var colorCode = getColorCodeOfColor(color);

    return ImmutableList.withElement(CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND, colorCode));
  }

  //method
  private IContainer<ICssProperty> toCssPropertiesWhenIsColorGradient() {
    final var degreeCode = getDegreeCodeOfColorGradient(colorGradient);
    final var color1Code = getColorCodeOfColor(colorGradient.getColor1());
    final var color2Code = getColorCodeOfColor(colorGradient.getColor2());
    final var linearGradientCode = "linear-gradient(" + degreeCode + "," + color1Code + "," + color2Code + ")";

    return ImmutableList.withElement(
      CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND_IMAGE, linearGradientCode));
  }

  //method
  private IContainer<ICssProperty> toCssPropertiesWhenIsImage() {
    final var backgroundImage = "data:image/jpeg;base64," + image.toJPGString();

    return ImmutableList.withElement(
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BACKGROUND_IMAGE,
        "url('" + backgroundImage + "')"),
      CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND_SIZE, "100% 100%"));
  }

  //method
  private IContainer<ICssProperty> toCssPropertiesWhenIsTransparent() {
    return ImmutableList.withElement(CssProperty.withNameAndValue(CssPropertyNameCatalogue.BACKGROUND, "none"));
  }
}
