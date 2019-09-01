//package declaration
package ch.nolix.element.GUI;

import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.layerElement.LayerElement;
import ch.nolix.element.layerElement.LayerProperty;
import ch.nolix.element.textFormat.Font;

//abstract class
/**
 * A {@link WidgetLook} stores the state-dependent attributes of a {@link Widget}.
 * 
 * For each attribute A, a {@link WidgetLook} provides, a {@link WidgetLook} has a method getRecursiveOrDefaultA().
 * Step 1: If the {@link WidgetLook} has a value for A,
 * 			getRecursiveOrDefaultA() must return thevalue.
 * Step 2: If the {@link WidgetLook} has a base look,
 * 			getRecursiveOrDefaultA() must return getRecursiveOrDefaultA() of the base structure.
 * Step 3: getRecursiveOrDefaultA() must return the default value for A.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 290
 * @param <WL> The type of a {@link WidgetLook}.
 */
public abstract class WidgetLook<WL extends WidgetLook<WL>> extends LayerElement<WL> {
	
	//default values
	public static final Font DEFAULT_TEXT_FONT = Font.Verdana;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldTextFlag";
	
	//attribute
	private final LayerProperty<Font> font =
	new LayerProperty<>(
		Font.TYPE_NAME,
		DEFAULT_TEXT_FONT,
		s -> Font.fromSpecification(s),
		f -> f.getSpecification()
	);
	
	//attribute
	private final LayerProperty<Boolean> boldTextFlag =
	new LayerProperty<>(
		BOLD_TEXT_FLAG_HEADER,
		new Boolean(false),
		s -> Boolean.fromSpecification(s),
		bts -> bts.getSpecification()
	);
	
	//attribute
	private final LayerProperty<PositiveInteger> textSize =
	new LayerProperty<>(
		PascalCaseNameCatalogue.TEXT_SIZE,
		new PositiveInteger(DEFAULT_TEXT_SIZE),
		s -> PositiveInteger.fromSpecification(s),
		ts -> ts.getSpecification()
	);
	
	//attribute
	private final LayerProperty<Color> textColor =
	new LayerProperty<>(
		PascalCaseNameCatalogue.TEXT_COLOR,
		DEFAULT_TEXT_COLOR,
		s -> Color.fromSpecification(s),
		tc -> tc.getSpecification()
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
	@Override
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the text font of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextFont() {
		
		font.removeValue();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the text size of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextSize() {
		
		textSize.removeValue();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the bold text flag of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeBoldTextFlag() {
		
		boldTextFlag.removeValue();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the bold text to the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL setBoldTextFlag(final boolean boldTextFlag) {
		
		this.boldTextFlag.setValue(new Boolean(boldTextFlag));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the text color of the current {@link WidgetLook}.
	 * 
	 * @param textColor
	 * @return the current {@link WidgetLook}.
	 * @throws ArgumentIsNullException if the given text color is null.
	 */
	public final WL setTextColor(final Color textColor) {
		
		this.textColor.setValue(textColor);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the text font of the current {@link WidgetLook}.
	 * 
	 * @param font
	 * @return the current {@link WidgetLook}.
	 * @throws ArgumentIsNullException if the given text font is null.
	 */
	public final WL setTextFont(final Font font) {
		
		this.font.setValue(font);
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the base look of the current {@link WidgetLook}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link WidgetLook} does not have a base look.
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
	 * @throws ArgumentIsNullException if the given base look is null.
	 */
	final void setBaseLook(final WL baseLook) {
		
		//Checks if the given base look is not null.
		Validator
		.suppose(baseLook)
		.thatIsNamed("base look")
		.isNotNull();
		
		setBaseEntity(baseLook);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link WidgetLook} does not have a base look.
	 */
	private void supposeHasBaseLook() {
		if (!hasBaseLook()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "base look");
		}
	}
}
