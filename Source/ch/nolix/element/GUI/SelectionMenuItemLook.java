//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity2.Entity;
import ch.nolix.core.entity2.Property;
import ch.nolix.core.specification.Specification;
import ch.nolix.element.color.Color;

//class
public final class SelectionMenuItemLook
extends Entity<SelectionMenuItemLook> {
	
	//default values
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
		
	//static method
	public static SelectionMenuItemLook createFromSpecification(
		final Specification specification
	) {
		
		final var selectionMenuItemLook = new SelectionMenuItemLook();
		selectionMenuItemLook.reset(specification);
		
		return selectionMenuItemLook;
	}

	//attribute
	private final Property<Color> backgroundColorProperty =
	new Property<Color>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		DEFAULT_BACKGROUND_COLOR,
		s -> Color.createFromSpecification(s)
	);
	
	//attribute
	private final Property<Color> textColorProperty =
	new Property<Color>(
		PascalCaseNameCatalogue.TEXT_COLOR,
		DEFAULT_TEXT_COLOR,
		s -> Color.createFromSpecification(s)
	);
	
	//method
	public Color getRecursiveOrDefaultBackgroundColor() {
		return backgroundColorProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public Color getRecursiveOrDefaultTextColor() {
		return textColorProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	public boolean hasRecursiveBackgroundColor() {
		return backgroundColorProperty.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveTextColor() {
		return textColorProperty.hasRecursiveValue();
	}
	
	//method
	public SelectionMenuItemLook setBackgroundColor(final Color backgroundColor) {
		
		backgroundColorProperty.setValue(backgroundColor);
		
		return this;
	}
	
	//method
	public SelectionMenuItemLook setTextColor(final Color textColor) {
		
		textColorProperty.setValue(textColor);
		
		return this;
	}
}
