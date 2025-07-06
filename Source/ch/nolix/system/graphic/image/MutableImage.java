package ch.nolix.system.graphic.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.matrixapi.IMatrix;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.element.mutableelement.AbstractMutableElement;
import ch.nolix.system.element.property.MutableSpecificationValueExtractor;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

public final class MutableImage extends AbstractMutableElement implements IMutableImage<MutableImage> {

  private static final String PIXEL_ARRAY_HEADER = "PixelArray";

  private static final String JPG_STRING = "JPGString";

  private static final BufferedImageTool BUFFERED_IMAGE_TOOL = new BufferedImageTool();

  private final Matrix<IColor> pixels;

  @SuppressWarnings("unused")
  private final MutableSpecificationValueExtractor pixelsExtractor = new MutableSpecificationValueExtractor(
    PIXEL_ARRAY_HEADER, this::setPixelArray, this::getPixelArraySpecification);

  private Node pixelArraySpecification;

  private BufferedImage bufferedImage;

  private MutableImage(final Matrix<IColor> pixels) {
    this.pixels = pixels;
  }

  public static MutableImage fromAnyImage(final IImage image) {
    return withPixels(image.getPixels());
  }

  public static MutableImage fromBytes(final byte[] bytes) {
    return fromBufferedImage(BUFFERED_IMAGE_TOOL.fromBytes(bytes));
  }

  public static MutableImage fromBufferedImage(final BufferedImage bufferedImage) {

    final var image = MutableImage.withWidthAndHeightAndWhiteColor(bufferedImage.getWidth(), bufferedImage.getHeight());
    for (var i = 1; i <= image.getWidth(); i++) {
      for (var j = 1; j <= image.getHeight(); j++) {
        final var pixel = bufferedImage.getRGB(i - 1, j - 1);
        image.setPixel(
          i,
          j,
          Color.withRedValueAndGreenValueAndBlueValueAndAlphaValue(
            (pixel >> 16) & 0xff,
            (pixel >> 8) & 0xff,
            pixel & 0xFF,
            (pixel >> 24) & 0xff));
      }
    }

    return image;
  }

  public static MutableImage fromFile(final String filePath) {

    final var bufferedImage = BUFFERED_IMAGE_TOOL.fromFile(filePath);

    return fromBufferedImage(bufferedImage);
  }

  public static MutableImage fromResource(final String path) {
    return fromBytes(RunningJar.getResourceAsBytes(path));
  }

  public static MutableImage fromSpecification(final INode<?> specification) {

    if (specification.containsChildNodeWithHeader(JPG_STRING)) {

      final var lJPGString = specification.getStoredFirstChildNodeWithHeader(JPG_STRING).getSingleChildNodeHeader();

      return fromBytes(Base64.getDecoder().decode(lJPGString.substring(lJPGString.indexOf(',') + 1)));
    }

    final var image = MutableImage.withWidthAndHeightAndWhiteColor(
      specification.getStoredFirstChildNodeWithHeader(PascalCaseVariableCatalog.WIDTH).getSingleChildNodeAsInt(),
      specification.getStoredFirstChildNodeWithHeader(PascalCaseVariableCatalog.HEIGHT).getSingleChildNodeAsInt());
    image.setPixelArray(specification.getStoredFirstChildNodeThat(a -> a.hasHeader(PIXEL_ARRAY_HEADER)));

    return image;
  }

  public static MutableImage fromString(final String string) {
    return fromSpecification(Node.fromString(string));
  }

  public static MutableImage withPixels(final IMatrix<IColor> pixels) {
    return new MutableImage(Matrix.fromMatrix(pixels));
  }

  public static MutableImage withWidthAndHeightAndColor(final int width, final int height, final IColor color) {

    Validator.assertThat(width).thatIsNamed(LowerCaseVariableCatalog.WIDTH).isPositive();
    Validator.assertThat(height).thatIsNamed(LowerCaseVariableCatalog.HEIGHT).isPositive();
    Validator.assertThat(color).thatIsNamed(Color.class).isNotNull();

    Matrix<IColor> pixels = Matrix.createEmpty();

    if (width > 0 && height > 0) {

      final var row = new Color[width];
      for (var i = 0; i < width; i++) {
        row[i] = Color.fromColor(color);
      }

      for (var i = 1; i <= height; i++) {
        pixels.addRow(ContainerView.forArray(row));
      }
    }

    return new MutableImage(pixels);
  }

  public static MutableImage withWidthAndHeightAndWhiteColor(final int width, final int height) {
    return withWidthAndHeightAndColor(width, height, X11ColorCatalog.WHITE);
  }

  @Override
  public IColor getBottomLeftPixel() {
    return getPixel(1, getHeight());
  }

  @Override
  public IColor getBottomRightPixel() {
    return getPixel(getWidth(), getHeight());
  }

  public MutableImage getCopy() {
    return new MutableImage(pixels.getCopy());
  }

  @Override
  public int getHeight() {
    return pixels.getRowCount();
  }

  @Override
  public IColor getPixel(final int xPosition, final int yPosition) {
    return pixels.getStoredAtOneBasedRowIndexAndColumnIndex(yPosition, xPosition);
  }

  @Override
  public int getPixelCount() {
    return pixels.getCount();
  }

  @Override
  public Matrix<IColor> getPixels() {
    return pixels.getCopy();
  }

  @Override
  public MutableImage getSection(final int xPosition, final int yPosition, final int width, final int height) {

    Validator.assertThat(xPosition).thatIsNamed("x-position").isPositive();
    Validator.assertThat(xPosition).thatIsNamed("y-position").isPositive();

    Validator
      .assertThat(width)
      .thatIsNamed(LowerCaseVariableCatalog.WIDTH)
      .isBetween(0, getWidth() - xPosition + 1);

    Validator
      .assertThat(height)
      .thatIsNamed(LowerCaseVariableCatalog.WIDTH)
      .isBetween(0, getHeight() - yPosition + 1);

    final var section = MutableImage.withWidthAndHeightAndWhiteColor(width, height);
    for (var i = 1; i <= width; i++) {
      for (var j = 1; j <= height; j++) {
        section.setPixel(i, j, getPixel(xPosition + i - 1, yPosition + j - 1));
      }
    }

    return section;
  }

  @Override
  public IColor getTopLeftPixel() {
    return getPixel(1, 1);
  }

  @Override
  public IColor getTopRightPixel() {
    return getPixel(getWidth(), 1);
  }

  @Override
  public int getWidth() {
    return pixels.getColumnCount();
  }

  @Override
  public void reset() {

    deletePixelArraySpecificationAndBufferedImage();

    final var pixelCount = getPixelCount();
    for (var i = 1; i <= pixelCount; i++) {
      pixels.setAt(i, X11ColorCatalog.WHITE);
    }
  }

  public void saveAsPNG() {
    try {
      ImageIO.write(toBufferedImage(), "PNG", new File(StringCatalog.EMPTY_STRING));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  @Override
  public MutableImage setPixel(int xPosition, int yPosition, final IColor color) {

    deletePixelArraySpecificationAndBufferedImage();

    pixels.setAtOneBasedRowIndexAndColumnIndex(yPosition, xPosition, color);

    return this;
  }

  public void setPixelArray(final INode<?> pixelArray) {

    final var lPixelArray = pixelArray.getStoredChildNodes();

    Validator.assertThat(lPixelArray.getCount()).thatIsNamed("number of pixels")
      .isEqualTo(getPixelCount());

    deletePixelArraySpecificationAndBufferedImage();

    var i = 1;
    for (final var p : lPixelArray) {
      this.pixels.setAt(i, Color.fromString(p.getHeader()));
      i++;
    }
  }

  public void setPixelArray(final Iterable<Color> pixelArray) {

    final var lPixelArray = ContainerView.forIterable(pixelArray);

    Validator.assertThat(lPixelArray.getCount()).thatIsNamed("number of pixels")
      .isEqualTo(getPixelCount());

    deletePixelArraySpecificationAndBufferedImage();

    var i = 1;
    for (final var p : lPixelArray) {
      pixels.setAt(i, p);
      i++;
    }
  }

  @Override
  public BufferedImage toBufferedImage() {

    generateBufferedImageIfNeeded();

    return bufferedImage;
  }

  @Override
  public Image toImmutableImage() {
    return Image.withPixels(pixels);
  }

  @Override
  public byte[] toJPG() {

    final var byteArrayOutputStream = new ByteArrayOutputStream();
    final var imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
    final var imageWriteParam = imageWriter.getDefaultWriteParam();
    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    imageWriteParam.setCompressionQuality(1.0F);

    try {

      imageWriter.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));

      imageWriter.write(null, new IIOImage(createJPGBufferedImage(), null, null), imageWriteParam);
      imageWriter.dispose();

      return byteArrayOutputStream.toByteArray();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  @Override
  public String toJPGString() {
    return Base64.getEncoder().encodeToString(toJPG());
  }

  @Override
  public MutableImage toLeftRotatedImage() {
    return new MutableImage(pixels.toLeftRotatedMatrix());
  }

  @Override
  public byte[] toPNG() {

    final var byteArrayOutputStream = new ByteArrayOutputStream();

    try {

      ImageIO.write(toBufferedImage(), "png", byteArrayOutputStream);

      return byteArrayOutputStream.toByteArray();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  @Override
  public String toPNGString() {
    return Base64.getEncoder().encodeToString(toPNG());
  }

  @Override
  public MutableImage toRepeatedImage(final int width, final int height) {

    final var image = MutableImage.withWidthAndHeightAndWhiteColor(width, height);

    final var sourceWidth = getWidth();
    final var sourceHeight = getHeight();

    for (var x = 1; x <= width; x++) {
      for (var y = 1; y <= height; y++) {
        image.setPixel(x, y, getPixel((x - 1) % sourceWidth + 1, (y - 1) % sourceHeight + 1));
      }
    }

    return image;
  }

  @Override
  public MutableImage toRightRotatedImage() {
    return new MutableImage(pixels.toRightRotatedMatrix());
  }

  @Override
  public MutableImage toScaledImage(final double factor) {

    Validator.assertThat(factor).thatIsNamed(LowerCaseVariableCatalog.FACTOR).isPositive();

    return toScaledImage(factor, factor);
  }

  @Override
  public MutableImage toScaledImage(final double widthFactor, final double heightFactor) {

    Validator.assertThat(widthFactor).thatIsNamed("width factor").isPositive();
    Validator.assertThat(heightFactor).thatIsNamed("height factor").isPositive();

    final var image = MutableImage.withWidthAndHeightAndWhiteColor(
      (int) (widthFactor * getWidth()),
      (int) (heightFactor * getHeight()));

    final var reziprocalWidthFactor = 1.0 / widthFactor;
    final var reziprocalHeightFactor = 1.0 / heightFactor;

    //sourceYs[y] := the source Image's y for the new Image's y
    final var sourceYs = new int[image.getHeight() + 1];
    for (var i = 1; i <= image.getHeight(); i++) {
      sourceYs[i] = (int) ((i - 1) * reziprocalHeightFactor) + 1;
    }

    for (var x = 1; x <= image.getWidth(); x++) {

      //sourceX := the source Image's x for the new Image's x
      final var sourceX = (int) ((x - 1) * reziprocalWidthFactor) + 1;

      for (var y = 1; y <= image.getHeight(); y++) {
        final var sourceY = sourceYs[y];
        image.setPixel(x, y, this.getPixel(sourceX, sourceY));
      }
    }

    return image;
  }

  @Override
  public IMutableImage<?> withAlphaValue(final double alphaValue) {

    final Matrix<IColor> localPixels = Matrix.createEmpty();
    for (final var r : pixels.getRows()) {
      localPixels.addRow(r.to(p -> p.withFloatingPointAlphaValue(alphaValue)));
    }

    return new MutableImage(localPixels);
  }

  @Override
  public IMutableImage<?> withWidthAndHeight(final int width, final int height) {
    return toScaledImage((double) width / getWidth(), (double) height / getHeight());
  }

  private BufferedImage createBufferedImage() {

    final var lBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
    for (var y = 0; y < getHeight(); y++) {
      for (var x = 0; x < getWidth(); x++) {

        final var pixel = pixels.getStoredAtOneBasedRowIndexAndColumnIndex(y + 1, x + 1);

        lBufferedImage.setRGB(x, y, pixel.toAlphaRedGreenBlueInt());
      }
    }

    return lBufferedImage;
  }

  private BufferedImage createJPGBufferedImage() {

    final var lBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    for (var y = 0; y < getHeight(); y++) {
      for (var x = 0; x < getWidth(); x++) {

        final var pixel = pixels.getStoredAtOneBasedRowIndexAndColumnIndex(y + 1, x + 1);

        lBufferedImage.setRGB(x, y, pixel.toAlphaRedGreenBlueInt());
      }
    }

    return lBufferedImage;
  }

  private Node createPixelArraySpecification() {
    return //
    Node.withHeaderAndChildNode(
      PIXEL_ARRAY_HEADER,
      Node.withHeaderAndChildNode(PascalCaseVariableCatalog.WIDTH, getWidth()),
      Node.withHeaderAndChildNodes(
        "Pixels", //TODO: Extend variable catalogs
        pixels.to(p -> Node.withHeader(p.toHexadecimalStringWithAlphaValue()))));
  }

  private void deletePixelArraySpecificationAndBufferedImage() {
    pixelArraySpecification = null;
    bufferedImage = null;
  }

  private void generateBufferedImageIfNeeded() {
    if (bufferedImage == null) {
      bufferedImage = createBufferedImage();
    }
  }

  private void generatePixelArraySpecificationIfNeeded() {
    if (pixelArraySpecification == null) {
      pixelArraySpecification = createPixelArraySpecification();
    }
  }

  private Node getPixelArraySpecification() {

    generatePixelArraySpecificationIfNeeded();

    return pixelArraySpecification;
  }
}
