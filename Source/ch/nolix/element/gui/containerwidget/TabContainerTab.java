//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Headerable;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.widget.Label;

//class
/**
 * @author Silvan Wyss
 * @date 2016-05-01
 * @lines 360
 */
public final class TabContainerTab extends Element<TabContainerTab>
implements Clearable, Headerable<TabContainerTab>, IMutableElement<TabContainerTab> {
	
	//constant
	public static final String DEFAULT_HEADER = PascalCaseCatalogue.DEFAULT;
	
	//method
	/**
	 * @param specification
	 * @return a new {@link TabContainerTab} from the given specification.
	 */
	public static TabContainerTab fromSpecification(final BaseNode specification) {
		
		final var tab = new TabContainerTab();
		tab.resetFrom(specification);
		
		return tab;
	}
	
	//attribute
	private final MutableValue<String> header =
	new MutableValue<>(
		PascalCaseCatalogue.HEADER,
		DEFAULT_HEADER,
		this::setHeader,
		BaseNode::getOneAttributeHeader,
		Node::withAttribute
	);
		
	//attribute
	private boolean selected;
	
	//attribute
	private final Label menuItem = new Label().setContentPosition(ContentPosition.CENTER).setCustomCursorIcon(CursorIcon.HAND);
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab}.
	 */
	public TabContainerTab() {
		reset();
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab} with the given header.
	 * 
	 * @param header
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 */
	public TabContainerTab(final String header) {
		
		//Calls other constructor.
		this();
		
		//Sets the header of the current tab container tab.
		setHeader(header);
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab} with the given header and widget.
	 * 
	 * @param header
	 * @param widget
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws ArgumentIsNullException if the given widget is null.
	 */
	public TabContainerTab(final String header, final Widget<?,? > widget) {
		
		//Calls other constructor.
		this(header);
		
		//Sets the widget of the current tab container tab.
		setWidget(widget);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Handles the case that the given attribute specifies a widget.
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			setWidget(WidgetGUI.createWidgetFrom(attribute));
			
		//Handles the case that the given attribute does not specify a widget.
		} else {
			
			//Calls method of the base class.
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Removes the widget of the current {@link TabContainerTab}.
	 */
	@Override
	public void clear() {
		widget = null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current TabContainerTab contains a Widget.
		if (containsAny()) {
			list.addAtEnd(getRefWidget().getSpecification());
		}
	}
	
	//method
	/**
	 * @return the header of the current {@link TabContainerTab}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainerTab} does not have a header.
	 */
	@Override
	public String getHeader() {
		return header.getValue();
	}
	
	//method
	/**
	 * @return the height of the current {@link TabContainerTab}.
	 */
	public int getHeight() {
		
		//Handles the case that the current tab container tab does not contain a widget.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current tab container tab contains a widget.
		return getRefWidget().getHeight();
	}
	
	//method
	/**
	 * @return the widget of the current {@link TabContainerTab}
	 * @throws InvalidArgumentException
	 * if the current {@link TabContainerTab} is empty.
	 */
	public Widget<?, ?> getRefWidget() {
		
		//Asserts that the current tab container tab contains a widget.
		supposeIsNotEmpty();
		
		return widget;
	}
	
	//method
	/**
	 * @return the width of the current tab container tab.
	 */
	public int getWidth() {
		
		//Handles the case that the current tab container tab does not contain a widget.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current tab container tab contains a widget.
		return getRefWidget().getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainerTab} is selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		
		setHeader(DEFAULT_HEADER);
		
		clear();
	}
	
	//method
	/**
	 * Selects the current {@link TabContainerTab}.
	 * 
	 * @return the current {@link TabContainerTab}.
	 */
	public TabContainerTab select() {
		
		selected = true;
		
		getRefMenuItem().setFocused();
		
		return this;
	}
	
	//method
	/**
	 * @param header
	 * @return the current {@link TabContainerTab}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	@Override
	public TabContainerTab setHeader(final String header) {
		
		//Asserts that the given header is not null or blank.
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current TabContainerTab.
		this.header.setValue(header);
		menuItem.setText(header);
		
		return this;
	}
		
	//method
	/**
	 * Sets the widget of the current {@link TabContainerTab}.
	 * 
	 * @param widget
	 * @return the current {@link TabContainerTab}.
	 * @throws ArgumentIsNullException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another {@link WidgetGUI} than the current {@link TabContainerTab}.
	 */
	public TabContainerTab setWidget(final Widget<?, ?> widget) {
		
		//Asserts that the given widget is not null.
		Validator.assertThat(widget).isOfType(Widget.class);
		
		//Sets the widget of the current tab container tab.
		this.widget = widget;
		
		return this;
	}
	
	//method
	/**
	 * Unselects the current {@link TabContainerTab}.
	 * 
	 * @return the current {@link TabContainerTab}.
	 */
	public TabContainerTab unselect() {
		
		selected = false;
		
		getRefMenuItem()
		.setUnfocused()
		.setUnhovered();
		
		return this;
	}
	
	//method
	/**
	 * @return the menu item of the current {@link TabContainerTab}.
	 */
	Label getRefMenuItem() {
		return menuItem;
	}
	
	//method
	/**
	 * @throws EmptyArgumentException if the current {@link TabContainerTab} is empty.
	 */
	private void supposeIsNotEmpty() {
		
		//Asserts that the current tab container tab is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
