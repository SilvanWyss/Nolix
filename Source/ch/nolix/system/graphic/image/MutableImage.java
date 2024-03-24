//package declaration
package ch.nolix.system.graphic.image;

//Java imports
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

//Javax imports
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;

//own imports
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.matrixapi.IMatrix;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MutableSpecificationValueExtractor;
import ch.nolix.system.element.property.Value;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

//class
public final class MutableImage extends MutableElement implements IMutableImage<MutableImage> {

  //constant
  private static final String PIXEL_ARRAY_HEADER = "PixelArray";

  //constant
  private static final String JPG_STRING = "JPGString";

  //attribute
  private final Value<Integer> width = new Value<>(
    PascalCaseVariableCatalogue.WIDTH,
    this::setWidth,
    INode::getSingleChildNodeAsInt,
    Node::withChildNode);

  //attribute
  private final Value<Integer> height = new Value<>(
    PascalCaseVariableCatalogue.HEIGHT,
    this::setHeight,
    INode::getSingleChildNodeAsInt,
    Node::withChildNode);

  //attribute
  private final Matrix<IColor> pixels;

  //attribute
  @SuppressWarnings("unused")
  private final MutableSpecificationValueExtractor pixelsExtractor = new MutableSpecificationValueExtractor(
    PIXEL_ARRAY_HEADER, this::setPixelArray, this::getPixelArraySpecification);

  //optional attribute
  private Node pixelArraySpecification;

  //optional attribute
  private BufferedImage bufferedImage;

  //constructor
  private MutableImage(final Matrix<IColor> pixels) {

    setWidth(pixels.getColumnCount());
    setHeight(pixels.getRowCount());

    this.pixels = pixels;
  }

  //static method
  public static MutableImage fromAnyImage(final IImage image) {
    return withPixels(image.getPixels());
  }

  //static method
  public static MutableImage fromBytes(final byte[] bytes) {
    return fromBufferedImage(GlobalBufferedImageTool.fromBytes(bytes));
  }

  //static method
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

  //static method
  public static MutableImage fromFile(final String filePath) {

    final var bufferedImage = GlobalBufferedImageTool.fromFile(filePath);

    return fromBufferedImage(bufferedImage);
  }

  //static method
  public static MutableImage fromResource(final String path) {
    return fromBytes(RunningJar.getResourceAsBytes(path));
  }

  //static method
  public static MutableImage fromSpecification(final INode<?> specification) {

    if (specification.containsChildNodeWithHeader(JPG_STRING)) {

      final var lJPGString = specification.getStoredFirstChildNodeWithHeader(JPG_STRING).getSingleChildNodeHeader();

      return fromBytes(Base64.getDecoder().decode(lJPGString.substring(lJPGString.indexOf(',') + 1)));
    }

    final var image = MutableImage.withWidthAndHeightAndWhiteColor(
      specification.getStoredFirstChildNodeWithHeader(PascalCaseVariableCatalogue.WIDTH).getSingleChildNodeAsInt(),
      specification.getStoredFirstChildNodeWithHeader(PascalCaseVariableCatalogue.HEIGHT).getSingleChildNodeAsInt());
    image.setPixelArray(specification.getStoredFirstChildNodeThat(a -> a.hasHeader(PIXEL_ARRAY_HEADER)));

    return image;
  }

  //static method
  public static MutableImage fromString(final String string) {
    return fromSpecification(Node.fromString(string));
  }

  //static method
  public static MutableImage withPixels(final IMatrix<IColor> pixels) {
    return new MutableImage(Matrix.fromMatrix(pixels));
  }

  //static method
  public static MutableImage withWidthAndHeightAndColor(final int width, final int height, final Color color) {

    GlobalValidator.assertThat(width).thatIsNamed(LowerCaseVariableCatalogue.WIDTH).isPositive();
    GlobalValidator.assertThat(height).thatIsNamed(LowerCaseVariableCatalogue.HEIGHT).isPositive();
    GlobalValidator.assertThat(color).thatIsNamed(Color.class).isNotNull();

    var pixels = new Matrix<IColor>();

    if (width > 0 && height > 0) {

      final var row = new Color[width];
      for (var i = 0; i < width; i++) {
        row[i] = color;
      }

      for (var i = 1; i <= height; i++) {
        pixels.addRow(ReadContainer.forArray(row));
      }
    }

    return new MutableImage(pixels);
  }

  //static method
  public static MutableImage withWidthAndHeightAndWhiteColor(final int width, final int height) {
    return withWidthAndHeightAndColor(width, height, Color.WHITE);
  }

  //method
  @Override
  public IColor getBottomLeftPixel() {
    return getPixel(1, getHeight());
  }

  //method
  @Override
  public IColor getBottomRightPixel() {
    return getPixel(getWidth(), getHeight());
  }

  //method
  public MutableImage getCopy() {
    return new MutableImage(pixels.getCopy());
  }

  //method
  @Override
  public int getHeight() {
    return height.getValue();
  }

  //method
  @Override
  public IColor getPixel(final int xPosition, final int yPosition) {
    return pixels.getStoredAt1BasedRowIndexAndColumnIndex(yPosition, xPosition);
  }

  //method
  @Override
  public int getPixelCount() {
    return pixels.getCount();
  }

  //method
  @Override
  public Matrix<IColor> getPixels() {
    return pixels.getCopy();
  }

  //method
  @Override
  public MutableImage getSection(final int xPosition, final int yPosition, final int width, final int height) {

    GlobalValidator.assertThat(xPosition).thatIsNamed("x-position").isPositive();
    GlobalValidator.assertThat(xPosition).thatIsNamed("y-position").isPositive();

    GlobalValidator
      .assertThat(width)
      .thatIsNamed(LowerCaseVariableCatalogue.WIDTH)
      .isBetween(0, getWidth() - xPosition + 1);

    GlobalValidator
      .assertThat(height)
      .thatIsNamed(LowerCaseVariableCatalogue.WIDTH)
      .isBetween(0, getHeight() - yPosition + 1);

    final var section = MutableImage.withWidthAndHeightAndWhiteColor(width, height);
    for (var i = 1; i <= width; i++) {
      for (var j = 1; j <= height; j++) {
        section.setPixel(i, j, getPixel(xPosition + i - 1, yPosition + j - 1));
      }
    }

    return section;
  }

  //method
  @Override
  public IColor getTopLeftPixel() {
    return getPixel(1, 1);
  }

  //method
  @Override
  public IColor getTopRightPixel() {
    return getPixel(getWidth(), 1);
  }

  //method
  @Override
  public int getWidth() {
    return width.getValue();
  }

  //method
  @Override
  public void reset() {

    deletePixelArraySpecificationAndBufferedImage();

    final var pixelCount = getPixelCount();
    for (var i = 1; i <= pixelCount; i++) {
      pixels.setAt(i, Color.WHITE);
    }
  }

  //method
  public void saveAsPNG() {
    try {
      ImageIO.write(toBufferedImage(), "PNG", new File(StringCatalogue.EMPTY_STRING));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //method
  @Override
  public MutableImage setPixel(int xPosition, int yPosition, final IColor color) {

    deletePixelArraySpecificationAndBufferedImage();

    pixels.setAt1BasedRowIndexAndColumnIndex(yPosition, xPosition, color);

    return this;
  }

  //method
  public void setPixelArray(final INode<?> pixelArray) {

    final var lPixelArray = pixelArray.getStoredChildNodes();

    GlobalValidator.assertThat(lPixelArray.getCount()).thatIsNamed("number of pixels")
      .isEqualTo(getPixelCount());

    deletePixelArraySpecificationAndBufferedImage();

    var i = 1;
    for (final var p : lPixelArray) {
      this.pixels.setAt(i, Color.fromString(p.getHeader()));
      i++;
    }
  }

  //method
  public void setPixelArray(final Iterable<Color> pixelArray) {

    final var lPixelArray = ReadContainer.forIterable(pixelArray);

    GlobalValidator.assertThat(lPixelArray.getCount()).thatIsNamed("number of pixels")
      .isEqualTo(getPixelCount());

    deletePixelArraySpecificationAndBufferedImage();

    var i = 1;
    for (final var p : lPixelArray) {
      pixels.setAt(i, p);
      i++;
    }
  }

  //method
  @Override
  public BufferedImage toBufferedImage() {

    generateBufferedImageIfNeeded();

    return bufferedImage;
  }

  //method
  @Override
  public Image toImmutableImage() {
    return Image.withPixels(pixels);
  }

  //method
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

  //method
  @Override
  public String toJPGString() {
    return Base64.getEncoder().encodeToString(toJPG());
  }

  //method
  @Override
  public MutableImage toLeftRotatedImage() {
    return new MutableImage(pixels.toLeftRotatedMatrix());
  }

  //method
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

  //method
  @Override
  public String toPNGString() {
    return Base64.getEncoder().encodeToString(toPNG());
  }

  //method
  //method
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

  //method
  @Override
  public MutableImage toRightRotatedImage() {
    return new MutableImage(pixels.toRightRotatedMatrix());
  }

  //method
  @Override
  public MutableImage toScaledImage(final double factor) {

    GlobalValidator.assertThat(factor).thatIsNamed(LowerCaseVariableCatalogue.FACTOR).isPositive();

    return toScaledImage(factor, factor);
  }

  //method
  @Override
  public MutableImage toScaledImage(final double widthFactor, final double heightFactor) {

    GlobalValidator.assertThat(widthFactor).thatIsNamed("width factor").isPositive();
    GlobalValidator.assertThat(heightFactor).thatIsNamed("height factor").isPositive();

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

  //method
  @Override
  public IMutableImage<?> withAlphaValue(final double alphaValue) {

    final var lPixels = new Matrix<IColor>();
    for (final var r : pixels.getRows()) {
      lPixels.addRow(r.to(p -> p.withFloatingPointAlphaValue(alphaValue)));
    }

    return new MutableImage(lPixels);
  }

  //method
  @Override
  public IMutableImage<?> withWidthAndHeight(final int width, final int height) {
    return toScaledImage((double) width / getWidth(), (double) height / getHeight());
  }

  //method
  private BufferedImage createBufferedImage() {

    final var lBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
    for (var y = 0; y < getHeight(); y++) {
      for (var x = 0; x < getWidth(); x++) {

        final var pixel = pixels.getStoredAt1BasedRowIndexAndColumnIndex(y + 1, x + 1);

        lBufferedImage.setRGB(x, y, pixel.toAlphaRedGreenBlueInt());
      }
    }

    return lBufferedImage;
  }

  //method
  private BufferedImage createJPGBufferedImage() {

    final var lBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    for (var y = 0; y < getHeight(); y++) {
      for (var x = 0; x < getWidth(); x++) {

        final var pixel = pixels.getStoredAt1BasedRowIndexAndColumnIndex(y + 1, x + 1);

        lBufferedImage.setRGB(x, y, pixel.toAlphaRedGreenBlueInt());
      }
    }

    return lBufferedImage;
  }

  //method
  private Node createPixelArraySpecification() {
    return Node.withHeaderAndChildNodes(
      PIXEL_ARRAY_HEADER,
      pixels.to(p -> Node.withHeader(p.toHexadecimalStringWithAlphaValue())));
  }

  //method
  private void deletePixelArraySpecificationAndBufferedImage() {
    pixelArraySpecification = null;
    bufferedImage = null;
  }

  //method
  private void generateBufferedImageIfNeeded() {
    if (bufferedImage == null) {
      bufferedImage = createBufferedImage();
    }
  }

  //method
  private void generatePixelArraySpecificationIfNeeded() {
    if (pixelArraySpecification == null) {
      pixelArraySpecification = createPixelArraySpecification();
    }
  }

  //method
  private Node getPixelArraySpecification() {

    generatePixelArraySpecificationIfNeeded();

    return pixelArraySpecification;
  }

  //method
  private void setHeight(final int height) {

    GlobalValidator.assertThat(height).thatIsNamed(LowerCaseVariableCatalogue.HEIGHT).isPositive();

    this.height.setValue(height);
  }

  //method
  private void setWidth(final int width) {

    GlobalValidator.assertThat(width).thatIsNamed(LowerCaseVariableCatalogue.WIDTH).isPositive();

    this.width.setValue(width);
  }
}
