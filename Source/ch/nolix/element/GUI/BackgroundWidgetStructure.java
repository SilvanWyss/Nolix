//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 150
 * @param <BWS> The type of a background widget structure.
 */
public abstract class BackgroundWidgetStructure<BWS extends BackgroundWidgetStructure<BWS>>
extends WidgetStructure<BWS> {

	//default value
	private static final Color DEFAULT_ACTIVE_BACKGROUND_COLOR = new Color(Color.WHITE);
	
	//attribute header
	private final static String BACKGROUND_COLOR_HEADER = "BackgroundColor";
	
	//optional attribute
	private Color backgroundColor;
	
	//method
	/**
	 * @return the active background color of this background widget structure.
	 */
	public final Color getActiveBackgroundColor() {
		
		//Handles the case if this  background widget structure has a background color.
		if (hasBackgroundColor()) {
			return backgroundColor.getCopy();
		}
		
		//Handles the case if this background widget structure
		//has no background color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBackgroundColor();
		}
		
		//Handles the case if this background widget structure
		//has no background color and no normal structure.
		return DEFAULT_ACTIVE_BACKGROUND_COLOR;
	}
	
	//method
	/**
	 * @return true if this background widget structure has a background color.
	 */
	public final boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @return true if this background widget structure has a recursive background color.
	 */
	public final boolean hasRecursiveBackgroundColor() {
		
		//Handles the case if this background widget structure has a background color.
		if (hasBackgroundColor()) {
			return true;
		}
		
		//Handles the case if this background widget structure
		//has no background color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasRecursiveBackgroundColor();
		}
		
		//Handles the case if this background widget structure
		//has no background color and no normal structure.
		return false;
	}
	
	//method
	/**
	 * Removes the background color of this background widget structure.
	 */
	public final void removeBackgroundColor() {
		backgroundColor = null;
	}
	
	//method
	/**
	 * Sets the background color of this background color widget structure.
	 * 
	 * @param backgroundColor
	 * @return this background color widget structure.
	 * @throws NullArgumentException if the given background color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBackgroundColor(final Color backgroundColor) {
		
		//Checks if the given background color is not null.
		Validator.supposeThat(backgroundColor).thatIsNamed("background color").isNotNull();
		
		//Sets the background color of this background color widget structure.
		this.backgroundColor = backgroundColor;
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this background widget structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case BACKGROUND_COLOR_HEADER:
				setBackgroundColor(new Color(attribute.getOneAttributeToString()));
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);				
		}
	}
	
	//method
	/**
	 * @return the attributes of this background widget structure.
	 */
	protected List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		//Handles the option that this background widget structure has a background color.
		if (hasBackgroundColor()) {
			attributes
			.addAtEnd(backgroundColor.getSpecificationAs(BACKGROUND_COLOR_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * Removes all attributes of this background widget structure.
	 */
	protected void removeAttributes() {
		removeBackgroundColor();
	}
}
