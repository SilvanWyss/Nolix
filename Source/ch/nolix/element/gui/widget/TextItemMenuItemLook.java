//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.layerelement.LayerElement;
import ch.nolix.element.layerelement.LayerProperty;

//class
public final class TextItemMenuItemLook extends LayerElement<TextItemMenuItemLook> {
	
	//constant
	public static final String TYPE_NAME = "ItemLook";
	
	//constants
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
		
	//static method
	public static TextItemMenuItemLook fromSpecification(
		final BaseNode specification
	) {
		
		final var selectionMenuItemLook = new TextItemMenuItemLook();
		selectionMenuItemLook.resetFrom(specification);
		
		return selectionMenuItemLook;
	}

	//attribute
	private final LayerProperty<Color> backgroundColor =
	new LayerProperty<>(
		PascalCaseCatalogue.BACKGROUND_COLOR,
		DEFAULT_BACKGROUND_COLOR,
		Color::fromSpecification,
		Color::getSpecification
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
	public Color getRecursiveOrDefaultBackgroundColor() {
		return backgroundColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public Color getRecursiveOrDefaultTextColor() {
		return textColor.getRecursiveOrDefaultValue();
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
	public TextItemMenuItemLook setBackgroundColor(final Color backgroundColor) {
		
		this.backgroundColor.setValue(backgroundColor);
		
		return this;
	}
	
	//method
	public TextItemMenuItemLook setTextColor(final Color textColor) {
		
		this.textColor.setValue(textColor);
		
		return this;
	}
	
	//method
	@Override
	protected void resetLayerElement() {}
}
