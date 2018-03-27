//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.entity2.Entity;
import ch.nolix.core.entity2.Property;
import ch.nolix.core.enums.TextStyle;
import ch.nolix.core.interfaces.IFluentObject;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.font.TextFont;
import ch.nolix.element.intData.TextSize;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;

//abstract class
/**
 * A widget structure stores the state-dependent attributes of a widget.
 * All attributes of a widget structure are optional.
 * 
 * For each attribute A, a widget structure has a method getActiveA().
 * Step 1: If the widget structure has a value, getActiveA() must return that value.
 * Step 2: If the widget structure has a base structure, getActiveA()
 *         must return getActiveA() of the base structure.
 * Step 3: getActiveA() must return a default value.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 240
 * @param <WS> The type of a widget structure.
 */
public abstract class WidgetStructure<WS extends WidgetStructure<WS>>
extends Entity<WS>
implements IFluentObject<WS> {
	
	//default values
	public static final TextFont DEFAULT_TEXT_FONT = TextFont.Verdana;
	public static final TextStyle DEFAULT_TEXT_STYLE = TextStyle.Default;
	public static final int DEFAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constants
	private static final String TEXT_SIZE_HEADER = "TextSize";
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attribute
	private final Property<TextFont> textFont =
	new Property<TextFont>(
		TextFont.TYPE_NAME,
		DEFAULT_TEXT_FONT,
		s -> TextFont.createFromSpecification(s)
	);
			
	//attribute
	private final Property<TextStyle> textStyle =
	new Property<TextStyle>(
		TextStyle.TYPE_NAME,
		DEFAULT_TEXT_STYLE,
		s -> TextStyle.createFromSpecification(s)
	);
	
	//attribute
	private final Property<PositiveInteger> textSize =
	new Property<PositiveInteger>(
		TEXT_SIZE_HEADER,
		new TextSize(DEFAULT_TEXT_SIZE),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<Color> textColor =
	new Property<Color>(
		TEXT_COLOR_HEADER,
		DEFAULT_TEXT_COLOR,
		s -> Color.createFromSpecification(s)
	);
	
	//optional attribute
	//private WS baseStructure;
	
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
	 * @return the active text style of this widget structure.
	 */
	public final TextStyle getActiveTextStyle() {
		return textStyle.getActiveValue();
	}
	
	//method
	/**
	 * Removes the text color of this widget structure.
	 * 
	 * @return this widget structure.
	 */
	public final WS removeTextColor() {
		
		textColor.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the text font of this widget structure.
	 * 
	 * @return this widget structure.
	 */
	public final WS removeTextFont() {
		
		textFont.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the text size of this widget structure.
	 * 
	 * @return this widget structure.
	 */
	public final WS removeTextSize() {
		
		textSize.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the text color of this widget structure.
	 * 
	 * @param textColor
	 * @return this widget structure.
	 * @throws NullArgumentException if the given text color is null.
	 */
	public final WS setTextColor(final Color textColor) {
		
		this.textColor.setValue(textColor);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the text font of this widget structure.
	 * 
	 * @param textFont
	 * @return this widget structure.
	 */
	public final WS setTextFont(final TextFont textFont) {
		
		this.textFont.setValue(textFont);
		
		return getInstance();
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
		
		return getRefBaseEntity();
	}
	
	//method
	/**
	 * @return true if this widget structure has a base structure.
	 */
	protected final boolean hasNormalStructure() {
		return hasBaseEntity();
	}
	
	//package-visible method
	/**
	 * Sets the base structure of this widget structure.
	 * 
	 * @param baseStructure
	 * @throws NullArgumentException if the given base structure is null.
	 */
	final void setBaseStructure(final WS baseStructure) {
		setBaseEntity(baseStructure);		
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
