//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.MutableElement;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.gui.color.Color;

//class
public final class BorderWidgetScrollBarLook extends MutableElement<BorderWidgetScrollBarLook> {
	
	//constants
	public static final Color DEFAULT_SCROLL_BAR_COLOR = Color.LIGHT_GREY;
	public static final Color DEFAULT_SCROLL_CURSOR_COLOR = Color.DARK_GREY;
	
	//constants
	private static final String SCROLL_BAR_COLOR_HEADER = "ScrollBarColor";
	private static final String SCROLL_CURSOR_COLOR_HEADER = "ScrollCursorColor";
	
	//static method
	public static BorderWidgetScrollBarLook fromSpecification(final INode<?> specification) {
		
		final var scrollBarLook = new BorderWidgetScrollBarLook();
		scrollBarLook.resetFromSpecification(specification);
		
		return scrollBarLook;
	}
	
	//attribute
	private final MutableValue<Color> scrollBarColor =
	new MutableValue<>(
		SCROLL_BAR_COLOR_HEADER,
		DEFAULT_SCROLL_BAR_COLOR,
		this::setScrollBarColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final MutableValue<Color> scrollBarCursorColor =
	new MutableValue<>(
		SCROLL_CURSOR_COLOR_HEADER,
		DEFAULT_SCROLL_CURSOR_COLOR,
		this::setScrollCursorColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//constructor
	public BorderWidgetScrollBarLook() {
		reset();
	}
	
	//method
	public Color getScrollBarColor() {
		return scrollBarColor.getValue();
	}
	
	//method
	public Color getScrollCursorColor() {
		return scrollBarCursorColor.getValue();
	}
	
	//method
	@Override
	public void reset() {
		setScrollBarColor(DEFAULT_SCROLL_BAR_COLOR);
		setScrollCursorColor(DEFAULT_SCROLL_CURSOR_COLOR);
	}
	
	//method
	public BorderWidgetScrollBarLook setScrollBarColor(final Color scrollBarColor) {
		
		this.scrollBarColor.setValue(scrollBarColor);
		
		return this;
	}
	
	//method
	public BorderWidgetScrollBarLook setScrollCursorColor(final Color scrollBarCursorColor) {
		
		this.scrollBarCursorColor.setValue(scrollBarCursorColor);
		
		return this;
	}
}
