/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.graphic.image;

import java.awt.image.BufferedImage;

import ch.nolix.base.datastructure.matrix.Matrix;
import ch.nolix.base.environment.runningjar.RunningJar;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.graphic.image.IMutableImage;

/**
 * @author Silvan Wyss
 */
public final class ImmutableImage extends AbstractElement implements IImage {
  private final MutableImage internalImage;

  private ImmutableImage(final MutableImage internalImage) {
    Validator.assertThat(internalImage).thatIsNamed("internal image").isNotNull();

    this.internalImage = internalImage;
  }

  public static ImmutableImage fromAnyImage(final IImage image) {
    if (image instanceof final ImmutableImage lImage) {
      return lImage;
    }

    return new ImmutableImage(MutableImage.fromAnyImage(image));
  }

  public static ImmutableImage fromBytes(final byte[] bytes) {
    final var mutableImage = MutableImage.fromBytes(bytes);

    return fromAnyImage(mutableImage);
  }

  public static ImmutableImage fromFile(final String filePath) {
    return new ImmutableImage(MutableImage.fromFile(filePath));
  }

  public static ImmutableImage fromResource(final String path) {
    return new ImmutableImage(MutableImage.fromBytes(RunningJar.getResourceAsBytes(path)));
  }

  public static ImmutableImage fromSpecification(final Node<?> specification) {
    return new ImmutableImage(MutableImage.fromSpecification(specification));
  }

  public static ImmutableImage withPixels(Matrix<IColor> pixels) {
    return new ImmutableImage(MutableImage.withPixels(pixels));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getAttributes() {
    return internalImage.getAttributes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getBottomLeftPixel() {
    return internalImage.getBottomLeftPixel();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getBottomRightPixel() {
    return internalImage.getBottomRightPixel();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getHeight() {
    return internalImage.getHeight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getPixel(int xPosition, int yPosition) {
    return internalImage.getPixel(xPosition, yPosition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getPixelCount() {
    return internalImage.getPixelCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Matrix<IColor> getPixels() {
    return internalImage.getPixels();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage getSection(int xPosition, int yPosition, int width, int height) {
    return internalImage.getSection(xPosition, yPosition, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getTopLeftPixel() {
    return internalImage.getTopLeftPixel();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getTopRightPixel() {
    return internalImage.getTopRightPixel();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getWidth() {
    return internalImage.getWidth();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toBase64Jpg() {
    return internalImage.toBase64Jpg();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toBase64Png() {
    return internalImage.toBase64Png();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BufferedImage toBufferedImage() {
    return internalImage.toBufferedImage();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImmutableImage toImmutableImage() {
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] toJpg() {
    return internalImage.toJpg();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage toLeftRotatedImage() {
    return internalImage.toLeftRotatedImage();
  }

  public MutableImage toMutableImage() {
    return internalImage.getCopy();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] toPng() {
    return internalImage.toPng();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage toRepeatedImage(int width, int height) {
    return internalImage.toRepeatedImage(width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage toRightRotatedImage() {
    return internalImage.toRightRotatedImage();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage toScaledImage(double factor) {
    return internalImage.toScaledImage(factor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage toScaledImage(double widthFactor, double heightFactor) {
    return internalImage.toScaledImage(widthFactor, heightFactor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableImage<?> withAlphaValue(final double alphaValue) {
    return internalImage.withAlphaValue(alphaValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableImage<?> withWidthAndHeight(final int width, final int height) {
    return internalImage.withWidthAndHeight(width, height);
  }
}
