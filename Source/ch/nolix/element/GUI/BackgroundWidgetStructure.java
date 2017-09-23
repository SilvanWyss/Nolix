//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.enums.UniDirection;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.BackgroundColorGradient;
import ch.nolix.element.entity.Property;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 140
 * @param <BWS> The type of a background widget structure.
 */
public abstract class BackgroundWidgetStructure<BWS extends BackgroundWidgetStructure<BWS>>
extends WidgetStructure<BWS> {

	//default values
	public static final BackgroundColor DEFAULT_BACKGROUND_COLOR = new BackgroundColor(Color.WHITE_INT);
	public static final BackgroundColorGradient DEFAULT_BACKGROUND_COLOR_GRADIENT = new BackgroundColorGradient();
	
	//optional attributes
		private final Property<BackgroundColor> backgroundColor
		= new Property<BackgroundColor>(
			BackgroundColor.TYPE_NAME,
			DEFAULT_BACKGROUND_COLOR,
			s -> new BackgroundColor(s.getRefOne().toString())
		);

		private final Property<BackgroundColorGradient> backgroundColorGradient
		= new Property<BackgroundColorGradient>(
			BackgroundColorGradient.TYPE_NAME,
			DEFAULT_BACKGROUND_COLOR_GRADIENT,
			s -> {
				return new BackgroundColorGradient(
					UniDirection.valueOf(s.getRefAt(1).toString()),
					new Color(s.getRefAt(2).toString()),
					new Color(s.getRefAt(3).toString())
				);
			}
		);
	
	//method
	/**
	 * @return the active background color of this background widget structure.
	 */
	public final Color getActiveBackgroundColor() {
		return backgroundColor.getActiveValue();
	}
	
	//method
	/**
	 * @return the active background color gradient of this background widget structure.
	 */
	public final BackgroundColorGradient getActiveBackgroundColorGradient() {
		return backgroundColorGradient.getActiveValue();
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
		
		backgroundColor.clear();
		
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
		
		backgroundColorGradient.clear();
		
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
		
		removeBackgroundColorGradient();
		
		return (BWS)this;
	}
}
