//package declaration
package ch.nolix.element.gui.image;

//own imports
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.element.base.MutableElement;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.color.ColorGradient;

//class
public final class Background extends MutableElement<Background> {
	
	//constant
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	//constants
	private static final String COLOR_HEADER = "Color";
	private static final String COLOR_GRADIENT_HEADER = "ColorGradient";
	private static final String IMAGE_HEADER = "Image";
	
	//static method
	public static Background fromSpecification(final BaseNode specification) {
		
		final var background = new Background();
		background.resetFrom(specification);
		
		return background;
	}
	
	//attribute
	private final MutableOptionalValue<Color> color =
	new MutableOptionalValue<>(
		COLOR_HEADER,
		this::setColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<ColorGradient> colorGradient =
	new MutableOptionalValue<>(
		COLOR_GRADIENT_HEADER,
		this::setColorGradient,
		ColorGradient::fromSpecification,
		ColorGradient::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<Pair<MutableImage, ImageApplication>> mutableImage =
	new MutableOptionalValue<>(
		IMAGE_HEADER,
		this::setImage,
		s ->
		new Pair<>(
			MutableImage.fromSpecification(s.getRefAttributeAt(1)),
			ImageApplication.fromSpecification(s.getRefAttributeAt(2))
		),
		bi -> Node.withAttribute(bi.getRefElement1().getSpecification(), bi.getRefElement2().getSpecification())
	);
	
	//constructor
	public Background() {
		reset();
	}
	
	//method
	public Color getColor() {
		return color.getValue();
	}
	
	//method
	public ColorGradient getColorGradient() {
		return colorGradient.getValue();
	}
	
	//method
	public MutableImage getImage() {
		return mutableImage.getValue().getRefElement1();
	}
	
	//method
	public ImageApplication getImageApplication() {
		return mutableImage.getValue().getRefElement2();
	}
	
	//method
	public boolean isColor() {
		return color.hasValue();
	}
	
	//method
	public boolean isColorGradient() {
		return colorGradient.hasValue();
	}
	
	//method
	public boolean isImage() {
		return mutableImage.hasValue();
	}
		
	//method
	@Override
	public void reset() {
		setColor(DEFAULT_COLOR);
	}
	
	//method
	public void setColor(final Color color) {
		
		clear();
		
		this.color.setValue(color);
	}
	
	//method
	public void setColorGradient(final ColorGradient backgroundColorGradient) {
		
		clear();
		
		this.colorGradient.setValue(backgroundColorGradient);
	}
	
	//method
	public void setImage(final MutableImage mutableImage, final ImageApplication imageApplication) {
		setImage(new Pair<>(mutableImage, imageApplication));
	}
	
	//method
	private void clear() {
		color.clear();
		colorGradient.clear();
		mutableImage.clear();
	}
	
	//method
	private void setImage(final Pair<MutableImage, ImageApplication> backgroundImage) {
		
		clear();
		
		this.mutableImage.setValue(backgroundImage);
	}
}
