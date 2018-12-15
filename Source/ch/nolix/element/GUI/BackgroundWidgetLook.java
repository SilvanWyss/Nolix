//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity2.Property;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 170
 * @param <BWL> The type of a {@link BackgroundWidgetLook}.
 */
public abstract class BackgroundWidgetLook<BWL extends BackgroundWidgetLook<BWL>>
extends WidgetLook<BWL> {

	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//default value
	public static final ColorGradient DEFAULT_BACKGROUND_COLOR_GRADIENT
	= ColorGradient.VERTICAL_BLACK_WHITE_COLOR_GRADIENT;
	
	//default value
	public static final Image DEFAULT_BACKGROUND_IMAGE = new Image(200, 100, Color.BLACK);
	
	//constants
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	private static final String BACKGROUND_IMAGE_HEADER = "BackgroundImage";
	
	//attribute
	private final Property<Color> backgroundColor =
	new Property<Color>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		DEFAULT_BACKGROUND_COLOR,
		s -> Color.createFromSpecification(s),
		bc -> bc.getSpecification()
	);

	//attribute
	private final Property<ColorGradient> backgroundColorGradient =
	new Property<ColorGradient>(
		BACKGROUND_COLOR_GRADIENT_HEADER,
		DEFAULT_BACKGROUND_COLOR_GRADIENT,
		s -> ColorGradient.createFromSpecification(s),
		cg -> cg.getSpecification()
	);
	
	//attribute
	private final Property<Image> backgroundImage =
	new Property<Image>(
		BACKGROUND_IMAGE_HEADER,
		DEFAULT_BACKGROUND_IMAGE,
		s -> Image.createFromSpecification(s),
		bi -> bi.getSpecification()
	);
	
 //method
 /**
 * @return the recursive or default background color
 * of the current {@link BackgroundWidgetLook}.
 */
 public final Color getRecursiveOrDefaultBackgroundColor() {
 return backgroundColor.getRecursiveOrDefaultValue();
 }
 
 //method
 /**
 * @return the recursive or default background color gradient
 * of the current {@link BackgroundWidgetLook}.
 */
 public final ColorGradient getRecursiveOrDefaultBackgroundColorGradient() {
 return backgroundColorGradient.getRecursiveOrDefaultValue();
 }
 
 //method
 /**
 * @return the recursive or default background image
 * of the current {@link BackgroundWidgetLook}.
 */
 public final Image getRecursiveOrDefaultBackgroundImage() {
 return backgroundImage.getRecursiveOrDefaultValue();
 }
 
 //method
 /**
 * @return true if the current {@link BackgroundWidgetLook}
 * has a recursive background color.
 */
 public final boolean hasRecursiveBackgroundColor() {
 return backgroundColor.hasRecursiveValue();
 }
 
 //method
 /**
 * @return true if the current {@link BackgroundWidgetLook}
 * has a recursive background color gradient.
 */
 public final boolean hasRecursiveBackgroundColorGradient() {
 return backgroundColorGradient.hasRecursiveValue();
 }
 
 //method
 /**
 * @return true if the current {@link BackgroundWidgetLook}
 * has a recursive background image.
 */
 public final boolean hasRecursiveBackgroundImage() {
 	return backgroundImage.hasRecursiveValue();
 }
 
 //method
 /**
 * Removes any background of the current {@link BackgroundWidgetLook}.
 * 
 * @return the current {@link BackgroundWidgetLook}.
 */
 public final BWL removeAnyBackground() {
 	
 	backgroundColor.removeValue();
 	backgroundColorGradient.removeValue();
 	backgroundImage.removeValue();
 	
 	return asConcreteType();
 }
 
 //method
 /**
 * Sets the background color of the current {@link BackgroundWidgetLook}.
 * Removes any former background of the current {@link BackgroundWidgetLook}.
 * 
 * @param backgroundColor
 * @return the current {@link BackgroundWidgetLook}.
 * @throws NullArgumentException if the given background color is null.
 */
 public final BWL setBackgroundColor(final Color backgroundColor) {
 	
 	removeAnyBackground();
 	this.backgroundColor.setValue(backgroundColor);
 	
 	return asConcreteType();
 }
 
 //method
 /**
 * Sets the background color gradient of the current {@link BackgroundWidgetLook}.
 * Removes any former background of the current {@link BackgroundWidgetLook}.
 * 
 * @param backgroundColorGradient
 * @return the current {@link BackgroundWidgetLook}.
 * @throws NullArgumentException if the given background color is null.
 */
 public final BWL setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
 	
 	removeAnyBackground();
 	this.backgroundColorGradient.setValue(backgroundColorGradient);
 	
 	return asConcreteType();
 }
 
 /**
 * Sets the background image of the current {@link BackgroundWidgetLook}.
 * Removes any former background of the current {@link BackgroundWidgetLook}.
 * 
 * @param backgroundImage
 * @return the current {@link BackgroundWidgetLook}.
 * @throws NullArgumentException if the given background image is null.
 */
 public final BWL setBackgroundImage(final Image backgroundImage) {
 		
 	removeAnyBackground();
 	this.backgroundImage.setValue(backgroundImage);
 	
 	return asConcreteType();
 }
}
