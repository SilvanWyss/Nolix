//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity2.Property;
import ch.nolix.element.core.NonNegativeInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 100
 */
public final class ConsoleLook extends BorderWidgetLook<ConsoleLook> {
		
	//default values
	public static final int DEFAULT_WIDTH = ValueCatalogue.BIG_WIDGET_WIDTH;
	public static final int DEFAULT_HEIGHT = ValueCatalogue.BIG_WIDGET_HEIGHT;
	
	//attribute
	private final Property<NonNegativeInteger> widthProperty =
	new Property<NonNegativeInteger>(
		PascalCaseNameCatalogue.WIDTH,
		new NonNegativeInteger(DEFAULT_WIDTH),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<NonNegativeInteger> heightProperty =
	new Property<NonNegativeInteger>(
		PascalCaseNameCatalogue.HEIGHT,
		new NonNegativeInteger(DEFAULT_HEIGHT),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//method
	/**
	 * @return the recursive or default height of the current {@link ConsoleLook}.
	 */
	public int getRecursiveOrDefaultHeight() {
		return heightProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	/**
	 * @return the recursive or default width of the current {@link ConsoleLook}.
	 */
	public int getRecursiveOrDefaultWidth() {
		return widthProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	/**
	 * Removes the height of the current {@link ConsoleLook}.
	 * 
	 * @return the current {@link ConsoleLook}.
	 */
	public ConsoleLook removeHeight() {
		
		heightProperty.removeValue();
		
		return this;
	}
	
	//method
	/**
	 * Removes the width of the current {@link ConsoleLook}.
	 * 
	 * @return the current {@link ConsoleLook}.
	 */
	public ConsoleLook removeWidth() {
		
		widthProperty.removeValue();
		
		return this;
	}
	
	//method
	/**
	 * Sets the height of the current {@link ConsoleLook}.
	 * 
	 * @param height
	 * @return the current {@link ConsoleLook}.
	 * @throws NegativeArgumentException if the given height is negative.
	 */
	public ConsoleLook setHeight(final int height) {
		
		heightProperty.setValue(new NonNegativeInteger(height));
		
		return this;
	}
	
	//method
	/**
	 * Sets the width of the current {@link ConsoleLook}.
	 * 
	 * @param width
	 * @return the current {@link ConsoleLook}.
	 * @throws NegativeArgumentException if the given width is negative.
	 */
	public ConsoleLook setWidth(final int width) {
		
		widthProperty.setValue(new NonNegativeInteger(width));
		
		return this;
	}
}
