//package declaration
package ch.nolix.element.containerWidget;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.mutableAttributeAPI.Headerable;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.elementAPI.IMutableElement;
import ch.nolix.element.elementEnum.ContentPosition;
import ch.nolix.element.widget.Label;

//class
/**
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 350
 */
public class TabContainerTab extends Element<TabContainerTab>
implements Clearable<TabContainerTab>, Headerable<TabContainerTab>, IMutableElement<TabContainerTab> {

	//constant
	public static final String DEFAULT_HEADER = PascalCaseNameCatalogue.DEFAULT;
	
	//attribute
	private final MutableProperty<String> header =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEADER,
		h -> setHeader(h),
		BaseNode::getOneAttributeHeader,
		h -> new Node(PascalCaseNameCatalogue.HEADER, h)
	);
	
	//method
	/**
	 * @param specification
	 * @return a new {@link TabContainerTab} from the given specification.
	 */
	public static TabContainerTab fromSpecification(final BaseNode specification) {
		
		final var tab = new TabContainerTab();
		tab.reset(specification);
		
		return tab;
	}
	
	//attribute
	private boolean selected = false;
	
	//attribute
	private final Label menuItem =
	new Label()
	.setContentPosition(ContentPosition.Center)
	.setCustomCursorIcon(CursorIcon.Hand);
	
	//optional attributes
	private TabContainer parentTabContainer;
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
		if (LayerGUI.canCreateWidgetFrom(attribute)) {
			setWidget(LayerGUI.createWidgetFrom(attribute));
		}
		
		//Handles the case that the given attribute does not specify a widget.
		else {
			
			//Calls method of the base class.
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainerTab} belongs to a {@link TabContainer}.
	 */
	public boolean belongsToTabContainer() {
		return (parentTabContainer != null);
	}
	
	//method
	/**
	 * Removes the widget of the current {@link TabContainerTab}.
	 * 
	 * @return the current {@link TabContainerTab}.
	 */
	@Override
	public TabContainerTab clear() {
		
		widget = null;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current tab container tab contains a widget.
		if (containsAny()) {
			attributes.addAtEnd(getRefWidget().getSpecification());
		}
		
		return attributes;
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
	public TabContainerTab reset() {
		
		setHeader(DEFAULT_HEADER);
		clear();
		
		return this;
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
		
		//Asserts that the given header is not null or empty.
		Validator.assertThat(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current TabContainerTab.
		this.header.setValue(header);
		
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
	 * if the given widget belongs to another {@link ILayerGUI}
	 * than the current {@link TabContainerTab}.
	 */
	public TabContainerTab setWidget(final Widget<?, ?> widget) {
		
		//Asserts that the given widget is not null.
		Validator.assertThat(widget).isOfType(Widget.class);
		
		if (belongsToTabContainer()) {
			parentTabContainer.internalAddChildWidget(widget);
		}
		
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
	 * Sets the tab container the current {@link TabContainerTab} will belong to.
	 * 
	 * @param parentTabContainer
	 * @throws ArgumentIsNullException if the given parent tab container is null.
	 */
	void setParentTabContainer(final TabContainer parentTabContainer) {
		
		//Asserts that the given parent tab container is not null.
		Validator
		.assertThat(parentTabContainer)
		.thatIsNamed("parent tab container")
		.isNotNull();
		
		//Sets the parent tab container of this tab container tab.
		this.parentTabContainer = parentTabContainer;
	}
	
	//method
	/**
	 * @throws InvalidStateExpceiton
	 * if the current {@link TabContainerTab} is empty.
	 */
	private void supposeIsNotEmpty() {
		
		//Asserts that the current tab container tab is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
