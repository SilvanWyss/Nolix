//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementAPI.IMutableElement;

//class
public final class BorderWidgetScrollBarLook extends Element<BorderWidgetScrollBarLook> implements IMutableElement<BorderWidgetScrollBarLook> {
	
	//constants
	public static final Color DEFAULT_SCROLLBAR_COLOR = Color.LIGHT_GREY;
	public static final Color DEFAULT_SCROLLBAR_CURSOR_COLOR = Color.DARK_GREY;
	
	//constant
	public static final String TYPE_NAME = "ScrollbarLook";
	
	//constants
	private static final String SCROLLBAR_COLOR_HEADER = "ScrollbarColor";
	private static final String SCROLLBAR_CURSOR_COLOR_HEADER = "ScrollbarCursorColor";
	
	//static method
	public static BorderWidgetScrollBarLook fromSpecification(
		final BaseNode specification
	) {
		
		final var scrollbarLook = new BorderWidgetScrollBarLook();
		scrollbarLook.reset(specification);
		
		return scrollbarLook;
	}
	
	//attribute
	private final MutableProperty<Color> scrollbarColor =
	new MutableProperty<>(
			SCROLLBAR_COLOR_HEADER,
		c -> setScrollbarColor(c),
		Color::fromSpecification,
		c -> c.getSpecification()
	);
	
	//attribute
	private final MutableProperty<Color> scrollbarCursorColor =
	new MutableProperty<>(
		SCROLLBAR_CURSOR_COLOR_HEADER,
		c -> setScrollbarCursorColor(c),
		Color::fromSpecification,
		c -> c.getSpecification()
	);
	
	//constructor
	public BorderWidgetScrollBarLook() {
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
	public BorderWidgetScrollBarLook reset() {
		
		setScrollbarColor(DEFAULT_SCROLLBAR_COLOR);
		setScrollbarCursorColor(DEFAULT_SCROLLBAR_CURSOR_COLOR);
		
		return this;
	}
	
	//method
	public BorderWidgetScrollBarLook setScrollbarColor(final Color scrollbarColor) {
		
		this.scrollbarColor.setValue(scrollbarColor);
		
		return this;
	}
	
	//method
	public BorderWidgetScrollBarLook setScrollbarCursorColor(final Color scrollbarCursorColor) {
		
		this.scrollbarCursorColor.setValue(scrollbarCursorColor);
		
		return this;
	}
}
