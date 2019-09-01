//package declaration
package ch.nolix.element.widgets;

import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.node.BaseNode;
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
	public static ItemMenuItemLook fromSpecification(
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
		s -> Color.fromSpecification(s),
		bc -> bc.getSpecification()
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
