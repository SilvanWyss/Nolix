//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.data.TextColor;
import ch.nolix.element.data.TextSize;
import ch.nolix.element.entity.Entity;
import ch.nolix.element.entity.Property;
import ch.nolix.element.font.TextFont;

//abstract class
/**
 * A widget structure stores the state-dependent attributes of a widget.
 * All attributes of a widget structure are optional.
 * 
 * For each attribute A, a widget structure has a method hasRecursiveA().
 * A method hasRecursiveA() must have the following scheme.
 * Step 1: If the widget structure has a value, hasRecursiveA() must return true.
 * Step 2: If the widget structure has a base structure, hasRecursiveA()
 *         must return hasRecursiveA() of the base structure.
 * Step 3: hasRecursiveA() must return false.
 * 
 * For each attribute A, a widget structure has a method getActiveA().
 * Step 1: If the widget structure has a value, getActiveA() must return that value.
 * Step 2: If the widget structure has a base structure, getActiveA()
 *         must return getActiveA() of the base structure.
 * Step 3: If the widget structure has a condition for a smart default value
 *         and the condition is fulfilled,
 *         getActiveA() must return the smart default value.
 * Step 4: getActiveA() must return a default value.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 240
 * @param <WS> The type of a widget structure.
 */
public abstract class WidgetStructure<WS extends WidgetStructure<WS>>
extends Entity {
	
	//default values
	public static final TextFont DEFAULT_TEXT_FONT = TextFont.Verdana;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//attribute
	private final Property<TextFont> textFont
	= new Property<TextFont>(
		TextFont.TYPE_NAME,
		DEFAULT_TEXT_FONT,
		s -> TextFont.valueOf(s.getRefOne().toString())
	);
	
	//attribute
	private final Property<TextSize> textSize
	= new Property<TextSize>(
		TextSize.TYPE_NAME,
		new TextSize(DEFAULT_TEXT_SIZE),
		s -> new TextSize(StringHelper.toInt(s.getRefOne().toString()))
	);
	
	//attribute
	private final Property<TextColor> textColor
	= new Property<TextColor>(
		"TextColor",
		new TextColor(DEFAULT_TEXT_COLOR.getValue()),
		s -> new TextColor(s.getRefOne().toString())
	);
	
	//optional attribute
	private WS baseStructure;
	
	//method
	/**
	 * @return the active text color of this widget structure.
	 */
	public final Color getActiveTextColor() {
		return textColor.getActiveValue();
	}
	
	//method
	/**
	 * @return the active text font of this widget structure.
	 */
	public TextFont getActiveTextFont() {
		return textFont.getActiveValue();
	}
		
	//method
	/**
	 * @return the active text size of this widget structure.
	 */
	public final int getActiveTextSize() {
		return textSize.getActiveValue().getValue();
	}
	
	//method
	/**
	 * Removes the text color of this widget structure.
	 * 
	 * @return this widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final WS removeTextColor() {
		
		textColor.clear();
		
		return (WS)this;
	}
	
	//method
	/**
	 * Removes the text font of this widget structure.
	 * 
	 * @return this widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final WS removeTextFont() {
		
		textFont.clear();
		
		return (WS)this;
	}
	
	//method
	/**
	 * Removes the text size of this widget structure.
	 * 
	 * @return this widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final WS removeTextSize() {
		
		textSize.clear();
		
		return (WS)this;
	}
	
	//method
	/**
	 * Sets the text color of this widget structure.
	 * 
	 * @param textColor
	 * @return this widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final WS setTextColor(final Color textColor) {
		
		this.textColor.setValue(new TextColor(textColor.getValue()));
		
		return (WS)this;
	}
	
	//method
	/**
	 * Sets the text font of this widget structure.
	 * 
	 * @param textFont
	 * @return this widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final WS setTextFont(final TextFont textFont) {
		
		this.textFont.setValue(textFont);
		
		return (WS)this;
	}
		
	//method
	/**
	 * Sets the text size of this widget structure.
	 * 
	 * @param textSize
	 * @return this widget structure.
	 */
	@SuppressWarnings("unchecked")
	public final WS setTextSize(final int textSize) {
		
		this.textSize.setValue(new TextSize(textSize));
		
		return (WS)this;
	}
	
	//method
	/**
	 * @return the base structure of this widget structure.
	 * @throws UnexistingAttributeException
	 * if this widget structure has no base structure.
	 */
	protected final WS getRefNormalStructure() {
		
		//Checks if this widget structure has a base structure.
		supposeHasBaseStructure();
		
		return baseStructure;
	}
	
	//method
	/**
	 * @return true if this widget structure has a base structure.
	 */
	protected final boolean hasNormalStructure() {
		return (baseStructure != null);
	}
	
	//package-visible method
	/**
	 * Sets the base structure of this widget structure.
	 * 
	 * @param baseStructure
	 * @throws NullArgumentException if the given base structure is null.
	 */
	final void setBaseStructure(final WS baseStructure) {
		
		//Checks if the given base structure is not null.
		Validator
		.supposeThat(baseStructure)
		.thatIsNamed("base structure")
		.isNotNull();
		
		//Sets the base structure of this widget structure.
		this.baseStructure = baseStructure;
		
		setBaseEntity(getRefNormalStructure());
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException
	 * if this widget structure has no base structure.
	 */
	private void supposeHasBaseStructure() {
		if (!hasNormalStructure()) {
			throw new UnexistingAttributeException(this, "base structure");
		}
	}
}
