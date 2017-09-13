//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.TextColor;
import ch.nolix.element.data.TextSize;
import ch.nolix.element.entity.Entity;
import ch.nolix.element.entity.Property;
import ch.nolix.element.font.FontFamily;

//abstract class
/**
 * A widget structure stores state-dependent attributes of a widget.
 * All attributes a widget structure can have are optional.
 * 
 * For each attribute A, a widget structure has a method hasRecursiveA().
 * A method hasRecursiveA() must have the following scheme.
 * Step 1: If the widget structure has a value, the hasRecursiveA() must return true.
 * Step 2: If the widget structure has a normal structure, hasRecursiveA()
 *         must return hasRecursiveA() of the normal structure.
 * Step 3: hasRecursiveA() must return false.
 * 
 * For each attribute A, a widget structure has a method getActiveA().
 * Step 1: If the widget structure has a value, getActiveA must return that value.
 * Step 2: If the widget structure has a normal structure, getActiveA()
 *         must return getActiveA() of the normal structure.
 * Step 3: If the widget structure has a condition for a smart default value
 *         and the condition is fulfilled,
 *         getActiveA() must return the smart default value.
 * Step 4: getActiveA() must return a default value.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 * @param <WS> The type of a widget structure.
 */
public abstract class WidgetStructure<WS extends WidgetStructure<WS>>
extends Entity {
	
	//attribute
	private final Property<FontFamily> fontFamily
	= new Property<FontFamily>(
		"FontFamily",
		FontFamily.Verdana,
		s -> FontFamily.valueOf(s)
	);
	
	//attribute
	private final Property<TextSize> textSize
	= new Property<TextSize>(
		TextSize.TYPE_NAME,
		new TextSize(20),
		s -> new TextSize(StringHelper.toInt(s))
	);
	
	//attribute
	private final Property<TextColor> textColor
	= new Property<TextColor>(
		"TextColor",
		new TextColor(),
		s -> new TextColor(s)
	);
	
	//optional attribute
	private WS normalStructure;
	
	//method
	public FontFamily getActiveFontFamily() {
		return fontFamily.getActiveValue();
	}
	
	//method
	public final Color getActiveTextColor() {
		return textColor.getActiveValue();
	}
	
	//method
	public final int getActiveTextSize() {
		return textSize.getActiveValue().getValue();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final WS removeTextColor() {
		
		textColor.clear();
		
		return (WS)this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final WS removeTextSize() {
		
		textSize.clear();
		
		return (WS)this;
	}
	
	//method
		@SuppressWarnings("unchecked")
	public final WS setFontFamily(final FontFamily fontFamily) {
		
		this.fontFamily.setValue(fontFamily);
		
		return (WS)this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final WS setTextColor(final Color textColor) {
		
		this.textColor.setValue(new TextColor(textColor.getValue()));
		
		return (WS)this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final WS setTextSize(final int textSize) {
		
		this.textSize.setValue(new TextSize(textSize));
		
		return (WS)this;
	}
	
	//method
	/**
	 * @return the normal structure of this widget structure.
	 * @throws UnexistingAttributeException if this widget structure has no normal structure.
	 */
	protected final WS getRefNormalStructure() {
		
		//Checks if this widget structure has a normal structure.
		if (!hasNormalStructure()) {
			throw new UnexistingAttributeException(this, "normal structure");
		}
		
		return normalStructure;
	}
	
	//method
	/**
	 * @return true if this widget structure has a normal structure.
	 */
	protected final boolean hasNormalStructure() {
		return (normalStructure != null);
	}
	
	//package-visible method
	/**
	 * Sets the normal structure of this widget structure.
	 * 
	 * @param normalStructure
	 * @throws NullArgumentException if the given normal structure is null.
	 */
	final void setNormalStructure(final WS normalStructure) {
		
		//Checks if the given normal structure is not null.
		Validator.supposeThat(normalStructure).thatIsNamed("normal structure").isNotNull();
		
		//Sets the normal structure of this widget structure.
		this.normalStructure = normalStructure;
		
		setBaseEntity(normalStructure);
	}
}
