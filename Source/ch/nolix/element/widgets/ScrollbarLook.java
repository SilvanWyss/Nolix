//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.color.Color;

//class
public final class ScrollbarLook extends Element<ScrollbarLook> implements IMutableElement<ScrollbarLook> {
	
	//default values
	public static final Color DEFAULT_SCROLLBAR_COLOR = Color.LIGHT_GREY;
	public static final Color DEFAULT_SCROLLBAR_CURSOR_COLOR = Color.DARK_GREY;
	
	//constant
	public static final String TYPE_NAME = "ScrollbarLook";
	
	//constants
	private static final String SCROLLBAR_COLOR_HEADER = "ScrollbarColor";
	private static final String SCROLLBAR_CURSOR_COLOR_HEADER = "ScrollbarCursorColor";
	
	//static method
	public static ScrollbarLook fromSpecification(
		final BaseNode specification
	) {
		
		final var scrollbarLook = new ScrollbarLook();
		scrollbarLook.reset(specification);
		
		return scrollbarLook;
	}
	
	//attribute
	private final MutableProperty<Color> scrollbarColor =
	new MutableProperty<>(
			SCROLLBAR_COLOR_HEADER,
		c -> setScrollbarColor(c),
		s -> Color.fromSpecification(s),
		c -> c.getSpecification()
	);
	
	//attribute
	private final MutableProperty<Color> scrollbarCursorColor =
	new MutableProperty<>(
		SCROLLBAR_CURSOR_COLOR_HEADER,
		c -> setScrollbarCursorColor(c),
		s -> Color.fromSpecification(s),
		c -> c.getSpecification()
	);
	
	//constructor
	public ScrollbarLook() {
		reset();
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	public Color getScrollbarColor() {
		return scrollbarColor.getValue();
	}
	
	//method
	public Color getScrollbarCursorColor() {
		return scrollbarCursorColor.getValue();
	}
	
	//method
	@Override
	public ScrollbarLook reset() {
		
		setScrollbarColor(DEFAULT_SCROLLBAR_COLOR);
		setScrollbarCursorColor(DEFAULT_SCROLLBAR_CURSOR_COLOR);
		
		return this;
	}
	
	//method
	public ScrollbarLook setScrollbarColor(final Color scrollbarColor) {
		
		this.scrollbarColor.setValue(scrollbarColor);
		
		return this;
	}
	
	//method
	public ScrollbarLook setScrollbarCursorColor(final Color scrollbarCursorColor) {
		
		this.scrollbarCursorColor.setValue(scrollbarCursorColor);
		
		return this;
	}
}
