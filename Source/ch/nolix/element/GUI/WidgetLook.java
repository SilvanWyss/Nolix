//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity2.Entity;
import ch.nolix.core.entity2.Property;
import ch.nolix.core.skillInterfaces.IFluentObject;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.textFormat.Font;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A {@link WidgetLook} stores the state-dependent attributes of a {@link Widget}.
 * 
 * For each attribute A, a {@link WidgetLook} provides, a {@link WidgetLook} has a method getRecursiveOrDefaultA().
 * Step 1: If the {@link WidgetLook} has a value for A,
 * 			getRecursiveOrDefaultA() must return thevalue.
 * Step 2: If the {@link WidgetLook} has a base look,
 * 			getRecursiveOrDefaultA()  must return getRecursiveOrDefaultA() of the base structure.
 * Step 3: getRecursiveOrDefaultA() must return the default value for A.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 290
 * @param <WL> The type of a {@link WidgetLook}.
 */
public abstract class WidgetLook<WL extends WidgetLook<WL>>
extends Entity<WL>
implements IFluentObject<WL>, Specified {
	
	//default values
	public static final Font DEFAULT_TEXT_FONT = Font.Verdana;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldTextFlag";
	
	//attribute
	private final Property<Font> font =
	new Property<Font>(
		Font.TYPE_NAME,
		DEFAULT_TEXT_FONT,
		s -> Font.createFromSpecification(s)
	);
	
	//attribute
	private final Property<Boolean> boldTextFlag =
	new Property<Boolean>(
		BOLD_TEXT_FLAG_HEADER,
		new Boolean(false),
		s -> Boolean.createFromSpecification(s)
	);
	
	//attribute
	private final Property<PositiveInteger> textSize =
	new Property<PositiveInteger>(
		PascalCaseNameCatalogue.TEXT_SIZE,
		new PositiveInteger(DEFAULT_TEXT_SIZE),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<Color> textColor =
	new Property<Color>(
		PascalCaseNameCatalogue.TEXT_COLOR,
		DEFAULT_TEXT_COLOR,
		s -> Color.createFromSpecification(s)
	);
	
	//method
	/**
	 * @return the recursive or default bold text flag
	 * of the current {@link WidgetLook}.
	 */
	public final boolean getRecursiveOrDefaultBoldTextFlag() {
		return boldTextFlag.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	/**
	 * @return the recursive or default text color
	 * of the current {@link WidgetLook}.
	 */
	public final Color getRecursiveOrDefaultTextColor() {
		return textColor.getRecursiveOrDefaultValue();
	}
	
	//method
	/**
	 * @return the recursive or default text font
	 * of the current {@link WidgetLook}.
	 */
	public final Font getRecursiveOrDefaultTextFont() {
		return font.getRecursiveOrDefaultValue();
	}
		
	//method
	/**
	 * @return the recursive or default text size
	 * of the current {@link WidgetLook}.
	 */
	public final int getRecursiveOrDefaultTextSize() {
		return textSize.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	/**
	 * @return the type of the current {@link WidgetLook}.
	 */
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return true if the current {@link WidgetLook} has a bold text flag.
	 */
	public final boolean hasRecursiveBoldTextFlag() {
		return boldTextFlag.hasRecursiveValue();
	}
	
	//method
	/**
	 * Removes the text color of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextColor() {
		
		textColor.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the text font of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextFont() {
		
		font.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the text size of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextSize() {
		
		textSize.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the bold text flag of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeBoldTextFlag() {
		
		boldTextFlag.removeValue();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the bold text to the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL setBoldTextFlag(final boolean boldTextFlag) {
		
		this.boldTextFlag.setValue(new Boolean(boldTextFlag));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the text color of the current {@link WidgetLook}.
	 * 
	 * @param textColor
	 * @return the current {@link WidgetLook}.
	 * @throws NullArgumentException if the given text color is not an instance.
	 */
	public final WL setTextColor(final Color textColor) {
		
		this.textColor.setValue(textColor);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the text font of the current {@link WidgetLook}.
	 * 
	 * @param font
	 * @return the current {@link WidgetLook}.
	 * @throws NullArgumentException if the given text font is not an instance.
	 */
	public final WL setTextFont(final Font font) {
		
		this.font.setValue(font);
		
		return getInstance();
	}
		
	//method
	/**
	 * Sets the text size of the current {@link WidgetLook}.
	 * 
	 * @param textSize
	 * @return the current {@link WidgetLook}.
	 * @throws NonPositiveArgumentException if the given text size is not positive.
	 */
	public final WL setTextSize(final int textSize) {
		
		this.textSize.setValue(new PositiveInteger(textSize));
		
		return getInstance();
	}
	
	//method
	/**
	 * @return the base look of the current {@link WidgetLook}.
	 * @throws UnexistingAttributeException
	 * if the current {@link WidgetLook} has no base look.
	 */
	protected final WL getRefBaseLook() {
		
		//Checks if the current widget look has a base look.
		supposeHasBaseLook();
		
		return getRefBaseEntity();
	}
	
	//method
	/**
	 * @return true if the current {@link WidgetLook} has a base look.
	 */
	protected final boolean hasBaseLook() {
		return hasBaseEntity();
	}
	
	//package-visible method
	/**
	 * Sets the base look of the current {@link WidgetLook}.
	 * 
	 * @param baseLook
	 * @throws NullArgumentException if the given base look is not an instance.
	 */
	final void setBaseLook(final WL baseLook) {
		
		//Checks if the given base look is an instance.
		Validator
		.suppose(baseLook)
		.thatIsNamed("base look")
		.isInstance();
		
		setBaseEntity(baseLook);		
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException
	 * if the current {@link WidgetLook} has no base look.
	 */
	private void supposeHasBaseLook() {
		if (!hasBaseLook()) {
			throw new UnexistingAttributeException(this, "base look");
		}
	}
}
