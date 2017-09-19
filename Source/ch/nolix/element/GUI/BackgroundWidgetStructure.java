//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.data.BackgroundColor;

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
	private static final BackgroundColor DEFAULT_BACKGROUND_COLOR = new BackgroundColor(Color.WHITE_INT);
	
	//optional attribute
	private BackgroundColor backgroundColor;
	
	//method
	/**
	 * Adds or changes the given attribute to this background widget structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case BackgroundColor.TYPE_NAME:
				setBackgroundColor(new Color(attribute.getOneAttributeToString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Removes the properties of this background widget structure fully.
	 */
	public void clearProperties() {
		
		//Calls method of the base class.
		super.clearProperties();
		
		removeBackgroundColor();
	}
	
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
		//has no background color but a base structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBackgroundColor();
		}
		
		//Handles the case if this background widget structure
		//has no background color and no base structure.
		return DEFAULT_BACKGROUND_COLOR;
	}
	
	//method
	/**
	 * @return the attributes of this background widget structure fully.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this background widget structure has a background color.
		if (hasBackgroundColor()) {
			attributes
			.addAtEnd(backgroundColor.getSpecification());
		}
		
		return attributes;
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
		//has no background color but a base structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasRecursiveBackgroundColor();
		}
		
		//Handles the case if this background widget structure
		//has no background color and no base structure.
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
		this.backgroundColor = new BackgroundColor(backgroundColor.getValue());
		
		return (BWS)this;
	}
}
