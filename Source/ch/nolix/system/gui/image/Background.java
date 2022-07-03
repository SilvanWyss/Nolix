//package declaration
package ch.nolix.system.gui.image;

//own imports
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.MutableElement;
import ch.nolix.system.element.MutableOptionalValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;

//class
public final class Background extends MutableElement<Background> {
	
	//constant
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	//constants
	private static final String COLOR_HEADER = "Color";
	private static final String COLOR_GRADIENT_HEADER = "ColorGradient";
	private static final String IMAGE_HEADER = "Image";
	
	//static method
	public static Background fromSpecification(final INode<?> specification) {
		
		final var background = new Background();
		background.resetFromSpecification(specification);
		
		return background;
	}
	
	//attribute
	private final MutableOptionalValue<IColor> color =
	new MutableOptionalValue<>(
		COLOR_HEADER,
		this::setColor,
		Color::fromSpecification,
		IColor::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IColorGradient> colorGradient =
	new MutableOptionalValue<>(
		COLOR_GRADIENT_HEADER,
		this::setColorGradient,
		ColorGradient::fromSpecification,
		IColorGradient::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<Pair<IImage, ImageApplication>> mutableImage =
	new MutableOptionalValue<>(
		IMAGE_HEADER,
		this::setImage,
		s ->
		new Pair<>(
			MutableImage.fromSpecification(s.getRefChildNodeAt1BasedIndex(1)),
			ImageApplication.fromSpecification(s.getRefChildNodeAt1BasedIndex(2))
		),
		bi -> Node.withChildNode(bi.getRefElement1().getSpecification(), bi.getRefElement2().getSpecification())
	);
	
	//constructor
	public Background() {
		reset();
	}
	
	//method
	public IColor getColor() {
		return color.getValue();
	}
	
	//method
	public IColorGradient getColorGradient() {
		return colorGradient.getValue();
	}
	
	//method
	public IImage getImage() {
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
	public void setColor(final IColor color) {
		
		clear();
		
		this.color.setValue(color);
	}
	
	//method
	public void setColorGradient(final IColorGradient backgroundColorGradient) {
		
		clear();
		
		this.colorGradient.setValue(backgroundColorGradient);
	}
	
	//method
	public void setImage(final IImage mutableImage, final ImageApplication imageApplication) {
		setImage(new Pair<>(mutableImage, imageApplication));
	}
	
	//method
	private void clear() {
		color.clear();
		colorGradient.clear();
		mutableImage.clear();
	}
	
	//method
	private void setImage(final Pair<IImage, ImageApplication> backgroundImage) {
		
		clear();
		
		this.mutableImage.setValue(backgroundImage);
	}
}
