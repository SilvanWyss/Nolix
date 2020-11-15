//package declaration
package ch.nolix.element.graphic;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.pair.Pair;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.smartElementAPI.ISmartMutableElement;

//class
public final class Background extends Element<Background> implements ISmartMutableElement<Background> {
	
	//constant
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//constants
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	private static final String BACKGROUND_IMAGE_HEADER = "BackgroundImage";
	
	//attribute
	private final MutableOptionalProperty<Color> backgroundColor =
	new MutableOptionalProperty<>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		this::setBackgroundColor,
		Color::fromSpecification
	);
	
	//attribute
	private final MutableOptionalProperty<ColorGradient> backgroundColorGradient =
	new MutableOptionalProperty<>(
		BACKGROUND_COLOR_GRADIENT_HEADER,
		this::setBackgroundColorGradient,
		ColorGradient::fromSpecification
	);
	
	//attribute
	private final MutableOptionalProperty<Pair<Image, ImageApplication>> backgroundImage =
	new MutableOptionalProperty<>(
		BACKGROUND_IMAGE_HEADER,
		this::setBackgroundImage,
		s ->
		new Pair<>(
			Image.fromSpecification(s.getRefAttributeAt(1)),
			ImageApplication.fromSpecification(s.getRefAttributeAt(2))
		),
		bi -> Node.withAttribute(bi.getRefElement1().getSpecification(), bi.getRefElement2().getSpecification())
	);
	
	//constructor
	public Background() {
		reset();
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	public Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	public ColorGradient getBackgroundColorGradient() {
		return backgroundColorGradient.getValue();
	}
	
	//method
	public Image getBackgroundImage() {
		return backgroundImage.getValue().getRefElement1();
	}
	
	//method
	public ImageApplication getBackgroundImageApplication() {
		return backgroundImage.getValue().getRefElement2();
	}
	
	//method
	public boolean hasBackgroundColor() {
		return backgroundColor.containsAny();
	}
	
	//method
	public boolean hasBackgroundColorGradient() {
		return backgroundColorGradient.containsAny();
	}
	
	//method
	public boolean hasBackgroundImage() {
		return backgroundImage.containsAny();
	}
		
	//method
	@Override
	public Background reset() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		return this;
	}
	
	//method
	public void setBackgroundColor(final Color backgroundColor) {
		
		clear();
		
		this.backgroundColor.setValue(backgroundColor);
	}
	
	//method
	public void setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		clear();
		
		this.backgroundColorGradient.setValue(backgroundColorGradient);
	}
	
	//method
	public void setBackgroundImage(final Image image, final ImageApplication imageApplication) {
		setBackgroundImage(new Pair<>(image, imageApplication));
	}
	
	//method
	private void clear() {
		backgroundColor.clear();
		backgroundColorGradient.clear();
		backgroundImage.clear();
	}
	
	//method
	private void setBackgroundImage(final Pair<Image, ImageApplication> backgroundImage) {
		
		clear();
		
		this.backgroundImage.setValue(backgroundImage);
	}
}
