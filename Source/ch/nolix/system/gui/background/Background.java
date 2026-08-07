/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.gui.background;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.ImmutableImage;
import ch.nolix.system.gui.colorgradient.ColorGradient;
import ch.nolix.system.gui.cssmapper.CssPropertyMapper;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.background.BackgroundType;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;

/**
 * @author Silvan Wyss
 */
public final class Background extends AbstractElement implements IBackground {
  public static final Background TRANSPARENT_BACKGROUND = new Background();

  public static final ImageApplication DEFAULT_IMAGE_APPLICATION = ImageApplication.SCALE_TO_FRAME;

  private static final String COLOR_HEADER = "Color";

  private static final String COLOR_GRADIENT_HEADER = "ColorGradient";

  private static final String IMAGE_HEADER = "Image";

  private static final String TRANSPARENCY_HEADER = "Transparency";

  private static final CssPropertyMapper CSS_PROPERTY_MAPPER = new CssPropertyMapper();

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
    Validator.assertThat(color).thatIsNamed(IColor.class).isNotNull();

    this.color = color;
    colorGradient = null;
    image = null;
    imageApplication = null;
  }

  private Background(final IColorGradient colorGradient) {
    Validator.assertThat(colorGradient).thatIsNamed(IColorGradient.class).isNotNull();

    color = null;
    this.colorGradient = colorGradient;
    image = null;
    imageApplication = null;
  }

  private Background(final IImage image, final ImageApplication imageApplication) {
    Validator.assertThat(image).thatIsNamed(IImage.class).isNotNull();
    Validator.assertThat(imageApplication).thatIsNamed(ImageApplication.class).isNotNull();

    color = null;
    colorGradient = null;
    this.image = image;
    this.imageApplication = imageApplication;
  }

  public static Background fromSpecification(final Node<?> specification) {
    final var childNode = specification.getStoredFirstChildNode();

    return switch (childNode.getHeader()) {
      case COLOR_HEADER ->
        withColor(Color.fromSpecification(childNode));
      case COLOR_GRADIENT_HEADER ->
        withColorGradient(ColorGradient.fromSpecification(childNode));
      case IMAGE_HEADER ->
        withImageAndImageApplication(
          ImmutableImage.fromSpecification(specification.getStoredChildNodeAtOneBasedIndex(1)),
          ImageApplication.fromSpecification(specification.getStoredChildNodeAtOneBasedIndex(2)));
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
    final Node<?> specification) {
    return //
    UnrepresentingArgumentException.forArgumentAndArgumentNameAndType(
      specification,
      LowerCaseVariableNameCatalog.SPECIFICATION,
      Background.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getAttributes() {
    return switch (getType()) {
      case COLOR ->
        LinkedList.withElement(getColor().getSpecification());
      case COLOR_GRADIENT ->
        LinkedList.withElement(getColorGradient().getSpecification());
      case IMAGE ->
        LinkedList.withElement(
          getImage().getSpecification().withNewHeader(IMAGE_HEADER),
          ImmutableNode.fromEnum(getImageApplication()));
      case TRANSPARENCY ->
        LinkedList.withElement(ImmutableNode.withHeader(TRANSPARENCY_HEADER));
      default ->
        throw InvalidArgumentException.forArgument(this);
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getColor() {
    assertIsColor();

    return color;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColorGradient getColorGradient() {
    assertIsColorGradient();

    return colorGradient;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IImage getImage() {
    assertIsImage();

    return image;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageApplication getImageApplication() {
    assertIsImage();

    return imageApplication;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BackgroundType getType() { // NOSONAR: The type of a Background is determined by all of its other attributes.

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

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ICssProperty> toCssProperties() {
    return CSS_PROPERTY_MAPPER.mapBackgroundToCssProperties(this);
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
}
