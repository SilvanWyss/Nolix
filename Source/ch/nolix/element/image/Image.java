//package declaration
package ch.nolix.element.image;

//Java imports
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import javax.imageio.ImageIO;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.containers.Matrix;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.Property;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class Image extends Element<Image> implements IMutableElement<Image> {

	//constant
	public static final String TYPE_NAME = "Image";
	
	//constant
	private static final String PIXEL_ARRAY_HEADER = "PixelArray";
	
	//static method
	public static Image fromSpecification(final BaseNode specification) {
		
		final var image =
		new Image(
			specification.getRefFirstAttribute(a -> a.hasHeader(PascalCaseNameCatalogue.WIDTH)).toInt(),
			specification.getRefFirstAttribute(a -> a.hasHeader(PascalCaseNameCatalogue.HEIGHT)).toInt()
		);
		
		image.addOrChangeAttribute(specification.getRefFirstAttribute(a -> a.hasHeader(PIXEL_ARRAY_HEADER)));
		
		return image;
	}
	
	//static method
	public static Image fromString(final String string) {
		return fromSpecification(Node.fromString(string));
	}

	//attribute
	private final Matrix<Color> pixels;
	
	//attribute
	private final Property<NonNegativeInteger> width =
	new Property<>(
		PascalCaseNameCatalogue.WIDTH,
		w -> setWidth(w.getValue()),
		s -> NonNegativeInteger.fromSpecification(s),
		w -> w.getSpecification()
	);
	
	//attribute
	private final Property<NonNegativeInteger> height =
	new Property<>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h.getValue()),
		s -> NonNegativeInteger.fromSpecification(s),
		h -> h.getSpecification()
	);
	
	//constructor
	public Image(final int width, final int height) {
		
		setWidth(width);
		setHeight(height);
		
		pixels = new Matrix<>();
		
		if (width > 0 && height > 0) {
			
			final var row = new Color[width];
			for (var i = 0; i < width; i++) {
				row[i] = Color.WHITE;
			}
			
			for (var i = 1; i <= height; i++) {
				pixels.addRow(row);
			}
		}
	}
	
	//constructor
	public Image(final int width, final int height, final Color color) {
		
		this(width, height);
		
		for (var i = 1; i <= getPixelCount(); i++) {
			pixels.setAt(i, color);
		}
	}
	
	//constructor
	private Image(final Matrix<Color> pixels) {
		
		setWidth(pixels.getColumnCount());
		setHeight(pixels.getRowCount());
		
		this.pixels = pixels;
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case PIXEL_ARRAY_HEADER:
				setPixelArray(attribute.getRefAttributes().to(a -> Color.fromSpecification(a)));
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public List<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
				
		final var pixelArraySpecification =	new Node(PIXEL_ARRAY_HEADER);
		pixels.forEach(p -> pixelArraySpecification.addAttribute(p.getHexadecimalValueAlwaysWithAlphaValue()));
		attributes.addAtEnd(pixelArraySpecification);
				
		return attributes;
	}
	
	//method
	public int getHeight() {
		return height.getValue().getValue();
	}
	
	//method
	public Color getPixel(final int xPosition, final int yPosition) {
		return pixels.getRefAt(yPosition, xPosition);
	}
	
	//method
	public int getPixelCount() {
		return pixels.getSize();
	}
	
	//method
	public int getWidth() {
		return width.getValue().getValue();
	}
	
	//method
	@Override
	public Image reset() {
		
		final var pixelCount = getPixelCount();
		for (var i = 1; i <= pixelCount; i++) {
			pixels.setAt(i, Color.WHITE);
		}
		
		return this;
	}
	
	//method
	public void saveAsPNG() {
		try {
			ImageIO.write(toBufferedImage(), "PNG", new File(StringCatalogue.EMPTY_STRING));
		}
		catch (final IOException IOException) {
			throw new IOError(IOException);
		}
	}
	
	//method
	public Image setPixel(int xPosition, int yPosition, final Color color) {
		
		pixels.setAt(yPosition, xPosition, color);
		
		return this;
	}
	
	//method
	public Image setPixelArray(final Iterable<Color> pixels) {
		
		final var pixelContainer = new ReadContainer<Color>(pixels);
		
		Validator
		.suppose(pixelContainer.getSize())
		.thatIsNamed("number of pixels")
		.isEqualTo(getPixelCount());
		
		var i = 1;
		for (final var p : pixels) {
			this.pixels.setAt(i, p);
			i++;
		}
		
		return this;
	}
	
	//method
	public BufferedImage toBufferedImage() {
		
		final var bufferedImage =
		new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		final var raster = bufferedImage.getRaster();
		
		for (var y = 0; y < getHeight(); y++) {
			for (var x = 0; x < getWidth(); x++) {
				
				final var pixel = pixels.getRefAt(y + 1, x + 1);
				
				//TODO: raster.setSample(x, y, 0, pixel.getAlphaValue())
				raster.setSample(x, y, 0, pixel.getRedValue());
				raster.setSample(x, y, 1, pixel.getGreenValue());
				raster.setSample(x, y, 2, pixel.getBlueValue());
			}
		}
		
		return bufferedImage;
	}
	
	//method
	public byte[] toJPG() {
		return to("jpg");
	}
	
	//method
	public Image toLeftRotatedImage() {
		return new Image(pixels.toLeftRotatedMatrix());
	}
	
	//method
	public byte[] toPNG() {
		return to("png");
	}
	
	//method
	public Image toRightRotatedImage() {
		return new Image(pixels.toRightRotatedMatrix());
	}
	
	//method
	private void setHeight(final int height) {
		this.height.setValue(new NonNegativeInteger(height));
	}
	
	//method
	private void setWidth(final int width) {
		this.width.setValue(new NonNegativeInteger(width));
	}
	
	//method
	private byte[] to(final String fileFormat) {
		
		final var byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			ImageIO.write(toBufferedImage(), fileFormat, byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		}
		catch (final IOException IOException) {
			throw new IOError(IOException);
		}
	}
}
