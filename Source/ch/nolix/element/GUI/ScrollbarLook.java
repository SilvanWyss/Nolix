//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.Entity;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.color.Color;

//class
public final class ScrollbarLook extends Entity {
	
	//default values
	public static final Color DEFAULT_COLOR = Color.LIGHT_GREY;
	public static final Color DEFAULT_CURSOR_COLOR = Color.DARK_GREY;
	
	//constant
	public static final String TYPE_NAME = "ScrollbarLook";
	
	//constant
	private static final String CURSOR_COLOR_HEADER = "CursorColor";
	
	//attribute
	private final MutableProperty<Color> color =
	new MutableProperty<Color>(
		PascalCaseNameCatalogue.COLOR,
		c -> setColor(c),
		s -> Color.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<Color> cursorColor =
	new MutableProperty<Color>(
		CURSOR_COLOR_HEADER,
		c -> setColor(c),
		s -> Color.createFromSpecification(s)
	);
	
	//constructor
	public ScrollbarLook() {
		reset();
		approveProperties();
	}
	
	//method
	public Color getColor() {
		return color.getValue();
	}
	
	//method
	public Color getCursorColor() {
		return cursorColor.getValue();
	}
	
	//method
	public String getType() {
		return TYPE_NAME;
	}
	
	//method
	public ScrollbarLook reset() {
		
		setColor(DEFAULT_COLOR);
		setCursorColor(DEFAULT_CURSOR_COLOR);
		
		return this;
	}
	
	//method
	public ScrollbarLook setColor(final Color color) {
		
		this.color.setValue(color);
		
		return this;
	}
	
	//method
	public ScrollbarLook setCursorColor(final Color scrollbarCursorColor) {
		
		this.cursorColor.setValue(scrollbarCursorColor);
		
		return this;
	}
}
