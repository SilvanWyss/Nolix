//package declaration
package ch.nolix.element.image;

//Java import
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Matrix;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.entity.Property;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.primitive.validator2.Validator;

//class
public final class Image extends MutableElement<Image> {

	//constant
	public static final String TYPE_NAME = "Image";
	
	//constant
	private static final String PIXEL_ARRAY_HEADER = "PixelArray";
	
	//static method
	public static Image createFromSpecification(final Specification specification) {
		
		final var image =
		new Image(
			specification.getRefFirstAttribute(a -> a.hasHeader(PascalCaseNameCatalogue.WIDTH)).toInt(),
			specification.getRefFirstAttribute(a -> a.hasHeader(PascalCaseNameCatalogue.HEIGHT)).toInt()
		);
		
		image.addOrChangeAttribute(specification.getRefFirstAttribute(a -> a.hasHeader(PIXEL_ARRAY_HEADER)));
		
		return image;
	}

	//attribute
	private final Matrix<Color> pixels = new Matrix<Color>();
	
	//attribute
	private final Property<NonNegativeInteger> width =
	new Property<NonNegativeInteger>(
		PascalCaseNameCatalogue.WIDTH,
		w -> setWidth(w.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<NonNegativeInteger> height =
	new Property<NonNegativeInteger>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//constructor
	public Image(final int width, final int height) {
		
		setWidth(width);
		setHeight(height);
		
		if (width > 0 && height > 0) {
			
			final var row = new Color[width];
			for (var i = 0; i < width; i++) {
				row[i] = Color.WHITE;
			}
			
			for (var i = 1; i <= height; i++) {
				pixels.addRow(row);
			}
		}
		
		approveProperties();
	}
	
	//constructor
	public Image(final int width, final int height, final Color color) {
		
		this(width, height);
		
		for (var i = 1; i <= getPixelCount(); i++) {
			pixels.set(i, color);
		}
	}
	
	//method
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case PIXEL_ARRAY_HEADER:
				setPixelArray(attribute.getRefAttributes().to(a -> Color.createFromSpecification(a)));
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}	
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		{
			final var pixelArraySpecification =
			StandardSpecification.createSpecificationWithHeader(PIXEL_ARRAY_HEADER);
			
			for (final var p : pixels) {
				pixelArraySpecification.addAttribute(p.getHexadecimalValue(true));
			}
			
			attributes.addAtEnd(pixelArraySpecification);
		}
		
		return attributes;
	}
	
	//method
	public BufferedImage getBufferedImage() {
		
		final var bufferedImage =
		new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		final var raster = bufferedImage.getRaster();
		
		for (var y = 0; y < getHeight(); y++) {
			for (var x = 0; x < getWidth(); x++) {
				
				final var pixel = pixels.getRefAt(y + 1, x + 1);
				
				//raster.setSample(x, y, 0, pixel.getAlphaValue());
				raster.setSample(x, y, 0, pixel.getRedValue());
				raster.setSample(x, y, 1, pixel.getGreenValue());
				raster.setSample(x, y, 2, pixel.getBlueValue());
			}
		}
		
		return bufferedImage;
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
		return pixels.getElementCount();
	}
	
	//method
	public int getWidth() {
		return width.getValue().getValue();
	}
	
	//method
	public Image reset() {
		
		final var pixelCount = getPixelCount();
		for (var i = 1; i <= pixelCount; i++) {
			pixels.set(i, Color.WHITE);
		}
		
		return this;
	}
	
	//method
	public Image setPixel(int xPosition, int yPosition, final Color color) {
		
		pixels.set(yPosition, xPosition, color);
		
		return this;
	}
	
	//method
	public Image setPixelArray(final Iterable<Color> pixels) {
		
		final var pixelContainer = new ReadContainer<Color>(pixels);
		
		Validator
		.suppose(pixelContainer.getElementCount())
		.thatIsNamed("number of pixels")
		.isEqualTo(getPixelCount());
		
		var i = 1;
		for (final var p : pixels) {
			this.pixels.set(i, p);
			i++;
		}
		
		return this;
	}
	
	//method
	private void setHeight(final int height) {
		this.height.setValue(new NonNegativeInteger(height));
	}
	
	//method
	private void setWidth(final int width) {
		this.width.setValue(new NonNegativeInteger(width));
	}
}
