//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.node.BaseNode;
import ch.nolix.element.color.Color;
import ch.nolix.element.layerElement.LayerElement;
import ch.nolix.element.layerElement.LayerProperty;

//class
public final class ItemMenuItemLook
extends LayerElement<ItemMenuItemLook> {
	
	//constant
	public static final String TYPE_NAME = "ItemLook";
	
	//default values
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
		
	//static method
	public static ItemMenuItemLook createFromSpecification(
		final BaseNode specification
	) {
		
		final var selectionMenuItemLook = new ItemMenuItemLook();
		selectionMenuItemLook.reset(specification);
		
		return selectionMenuItemLook;
	}

	//attribute
	private final LayerProperty<Color> backgroundColor =
	new LayerProperty<>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		DEFAULT_BACKGROUND_COLOR,
		s -> Color.createFromSpecification(s),
		bc -> bc.getSpecification()
	);
	
	//attribute
	private final LayerProperty<Color> textColor =
	new LayerProperty<>(
		PascalCaseNameCatalogue.TEXT_COLOR,
		DEFAULT_TEXT_COLOR,
		s -> Color.createFromSpecification(s),
		tc -> tc.getSpecification()
	);
	
	//method
	public Color getRecursiveOrDefaultBackgroundColor() {
		return backgroundColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public Color getRecursiveOrDefaultTextColor() {
		return textColor.getRecursiveOrDefaultValue();
	}
	
	//method
	@Override
	public String getType() {
		return TYPE_NAME;
	}
	
	//method
	public boolean hasRecursiveBackgroundColor() {
		return backgroundColor.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveTextColor() {
		return textColor.hasRecursiveValue();
	}
	
	//method
	public ItemMenuItemLook setBackgroundColor(final Color backgroundColor) {
		
		this.backgroundColor.setValue(backgroundColor);
		
		return this;
	}
	
	//method
	public ItemMenuItemLook setTextColor(final Color textColor) {
		
		this.textColor.setValue(textColor);
		
		return this;
	}
}
