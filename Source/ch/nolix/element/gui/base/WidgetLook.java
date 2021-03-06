//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.element.color.Color;
import ch.nolix.element.layerelement.LayerElement;
import ch.nolix.element.layerelement.LayerProperty;
import ch.nolix.element.textformat.Font;

//class
/**
 * A {@link WidgetLook} stores the state-dependent attributes of a {@link Widget}.
 * 
 * For each attribute A, a {@link WidgetLook} has a getRecursiveOrDefaultA method.
 * Step 1: If the {@link WidgetLook} has a value for A,
 *         the getRecursiveOrDefaultA method must return the value.
 * Step 2: If the {@link WidgetLook} has a base look,
 *         the getRecursiveOrDefaultA method must return getRecursiveOrDefaultA() of the base structure.
 * Step 3: The getRecursiveOrDefaultA method must return the default value for A.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 290
 * @param <WL> is the type of a {@link WidgetLook}.
 */
public abstract class WidgetLook<WL extends WidgetLook<WL>> extends LayerElement<WL> {
	
	//constants
	public static final Font DEFAULT_TEXT_FONT = Font.VERDANA;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldTextFlag";
	
	//attribute
	private final LayerProperty<Font> font =
	new LayerProperty<>(
		Font.TYPE_NAME,
		DEFAULT_TEXT_FONT,
		Font::fromSpecification,
		Font::getSpecification
	);
	
	//attribute
	private final LayerProperty<Boolean> boldTextFlag =
	new LayerProperty<>(
		BOLD_TEXT_FLAG_HEADER,
		Boolean.FALSE,
		BaseNode::toBoolean,
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<Integer> textSize =
	new LayerProperty<>(
		PascalCaseCatalogue.TEXT_SIZE,
		DEFAULT_TEXT_SIZE,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<Color> textColor =
	new LayerProperty<>(
		PascalCaseCatalogue.TEXT_COLOR,
		DEFAULT_TEXT_COLOR,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//method
	/**
	 * @return the recursive or default bold text flag of the current {@link WidgetLook}.
	 */
	public final boolean getRecursiveOrDefaultBoldTextFlag() {
		return boldTextFlag.getRecursiveOrDefaultValue();
	}
	
	//method
	/**
	 * @return the recursive or default text color of the current {@link WidgetLook}.
	 */
	public final Color getRecursiveOrDefaultTextColor() {
		return textColor.getRecursiveOrDefaultValue();
	}
	
	//method
	/**
	 * @return the recursive or default text font of the current {@link WidgetLook}.
	 */
	public final Font getRecursiveOrDefaultTextFont() {
		return font.getRecursiveOrDefaultValue();
	}
		
	//method
	/**
	 * @return the recursive or default text size of the current {@link WidgetLook}.
	 */
	public final int getRecursiveOrDefaultTextSize() {
		return textSize.getRecursiveOrDefaultValue();
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the text font of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextFont() {
		
		font.removeValue();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the text size of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeTextSize() {
		
		textSize.removeValue();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the bold text flag of the current {@link WidgetLook}.
	 * 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL removeBoldTextFlag() {
		
		boldTextFlag.removeValue();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the bold text to the current {@link WidgetLook}.
	 * 
	 * @param boldTextFlag 
	 * @return the current {@link WidgetLook}.
	 */
	public final WL setBoldTextFlag(final boolean boldTextFlag) {
		
		this.boldTextFlag.setValue(boldTextFlag);
		
		return asConcrete();
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
		
		return asConcrete();
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
		
		return asConcrete();
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
		
		//Asserts that the given textSize is positive.
		Validator.assertThat(textSize).thatIsNamed(LowerCaseCatalogue.TEXT_SIZE).isPositive();
		
		this.textSize.setValue(textSize);
		
		return asConcrete();
	}
	
	//method
	/**
	 * @return the base look of the current {@link WidgetLook}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link WidgetLook} does not have a base look.
	 */
	protected final WL getRefBaseLook() {
		
		//Asserts that the current widget look has a base look.
		supposeHasBaseLook();
		
		return getRefBaseElement();
	}
	
	//method
	/**
	 * @return true if the current {@link WidgetLook} has a base look.
	 */
	protected final boolean hasBaseLook() {
		return hasBaseElement();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetLayerElement() {}
	
	//method
	/**
	 * Sets the base look of the current {@link WidgetLook}.
	 * 
	 * @param baseLook
	 * @throws ArgumentIsNullException if the given base look is null.
	 */
	final void setBaseLook(final WL baseLook) {
		
		//Asserts that the given base look is not null.
		Validator
		.assertThat(baseLook)
		.thatIsNamed("base look")
		.isNotNull();
		
		setBaseElement(baseLook);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link WidgetLook} does not have a base look.
	 */
	private void supposeHasBaseLook() {
		if (!hasBaseLook()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "base look");
		}
	}
}
