//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.entity2.Property;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.BackgroundColorGradient;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 140
 * @param <BWS> The type of a background widget structure.
 */
public abstract class BackgroundWidgetLook<BWS extends BackgroundWidgetLook<BWS>>
extends WidgetLook<BWS> {

	//default values
	public static final BackgroundColor DEFAULT_BACKGROUND_COLOR = new BackgroundColor(Color.WHITE_INT);
	public static final BackgroundColorGradient DEFAULT_BACKGROUND_COLOR_GRADIENT = new BackgroundColorGradient();
	
	//constants
	private static final String BACKGROUND_COLOR_HEADER = "BackgroundColor";
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	
	//optional attribute
	private final Property<Color> backgroundColor =
	new Property<Color>(
		BACKGROUND_COLOR_HEADER,
		DEFAULT_BACKGROUND_COLOR,
		s -> Color.createFromSpecification(s)
	);

	//optional attribute
	private final Property<ColorGradient> backgroundColorGradient =
	new Property<ColorGradient>(
		BACKGROUND_COLOR_GRADIENT_HEADER,
		DEFAULT_BACKGROUND_COLOR_GRADIENT,
		s -> ColorGradient.createFromSpecification(s)
	);
	
	//method
	/**
	 * @return the active background color of this background widget structure.
	 */
	public final Color getActiveBackgroundColor() {
		return backgroundColor.getRecursiveValueOrDefault();
	}
	
	//method
	/**
	 * @return the active background color gradient of this background widget structure.
	 */
	public final ColorGradient getActiveBackgroundColorGradient() {
		return backgroundColorGradient.getRecursiveValueOrDefault();
	}
	
	//method
	/**
	 * @return true if this background widget structure has a recursive background color.
	 */
	public final boolean hasRecursiveBackgroundColor() {
		return backgroundColor.hasRecursiveValue();
	}
	
	//method
	/**
	 * @return true if this background widget structure has a recursive background color gradient.
	 */
	public final boolean hasRecursiveBackgroundColorGradient() {
		return backgroundColorGradient.hasRecursiveValue();
	}
	
	//method
	/**
	 * Removes the background color of this background widget structure.
	 * 
	 * @return this background color widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final BWS removeBackgroundColor() {
		
		backgroundColor.removeValue();
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Removes the background color gradient of this background widget structure.
	 * 
	 * @return this background color widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final BWS removeBackgroundColorGradient() {
		
		backgroundColorGradient.removeValue();
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the background color of this background widget structure.
	 * Removes the background color gradient of this background widget structure.
	 * 
	 * @param backgroundColor
	 * @return this background color widget structure.
	 * @throws NullArgumentException if the given background color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBackgroundColor(final Color backgroundColor) {
		
		this.backgroundColor.setValue(new BackgroundColor(backgroundColor.getValue()));
		removeBackgroundColorGradient();
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the background color gradient of this background widget structure.
	 * Removes the background color of this background widget structure.
	 * 
	 * @param backgroundColorGradient
	 * @return this background color widget structure.
	 * @throws NullArgumentException if the given background color gradient is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		this.backgroundColorGradient.setValue(
			new BackgroundColorGradient(
				backgroundColorGradient.getDirection(),
				backgroundColorGradient.getColor1(),
				backgroundColorGradient.getColor2()
			)
		);
		
		removeBackgroundColor();
		
		return (BWS)this;
	}
}
