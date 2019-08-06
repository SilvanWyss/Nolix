//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.core.attributeAPI.Headerable;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.GUI_API.ILayerGUI;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.elementEnums.ContentPosition;
import ch.nolix.element.widgets.Label;

//class
/**
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 350
 */
public class TabContainerTab extends Element<TabContainerTab>
implements Clearable<TabContainerTab>, Headerable<TabContainerTab>, IMutableElement<TabContainerTab> {

	//default value
	public static final String DEFAULT_HEADER = PascalCaseNameCatalogue.DEFAULT;
	
	//attribute
	private final MutableProperty<String> header =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEADER,
		h -> setHeader(h),
		s -> s.getOneAttributeAsString(),
		h -> new DocumentNode(PascalCaseNameCatalogue.HEADER, h)
	);
	
	//method
	/**
	 * @param specification
	 * @return a new {@link TabContainerTab} from the given specification.
	 */
	public static TabContainerTab createFromSpecification(final DocumentNodeoid specification) {
		
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
		menuItem.setKeepsFocus();
		reset();
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab} with the given header.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
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
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if the given widget is null.
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
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
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
	public List<DocumentNode> getAttributes() {
		
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
	 * @throws ArgumentMissesAttributeException if the current {@link TabContainerTab} does not have a header.
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
		
		//Checks if the current tab container tab contains a widget.
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
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	@Override
	public TabContainerTab setHeader(final String header) {
		
		//Checks if the given header is not null or empty.
		Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
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
	 * @throws NullArgumentException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another {@link ILayerGUI}
	 * than the current {@link TabContainerTab}.
	 */
	public TabContainerTab setWidget(final Widget<?, ?> widget) {
		
		//Checks if the given widget is not null.
		Validator.suppose(widget).isOfType(Widget.class);
		
		if (belongsToTabContainer()) {
			widget.setParentWidget(getParentTabContainer());
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
		getRefMenuItem().setNormal();
		
		return this;
	}
	
	//package-visible method
	/**
	 * @return the menu item of the current {@link TabContainerTab}.
	 */
	Label getRefMenuItem() {
		return menuItem;
	}
	
	//package-visible method
	/**
	 * Sets the tab container the current {@link TabContainerTab} will belong to.
	 * 
	 * @param parentTabContainer
	 * @throws NullArgumentException if the given parent tab container is null.
	 */
	void setParentTabContainer(final TabContainer parentTabContainer) {
		
		//Checks if the given parent tab container is not null.
		Validator
		.suppose(parentTabContainer)
		.thatIsNamed("parent tab container")
		.isNotNull();
		
		//Sets the parent tab container of this tab container tab.
		this.parentTabContainer = parentTabContainer;
	}
	
	//method
	/**
	 * @return the {@link TabContainer} the current {@link TabContainerTab} belongs.
	 * @throws InvalidArgumentException if the current {@link TabContainerTab}
	 * does not belong to a {@link TabContainer}.
	 */
	private TabContainer getParentTabContainer() {
		
		//Checks if the current tab container tab belongs to a tab container.
		supposeBelongsToTabContainer();
		
		return parentTabContainer;
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link TabContainerTab}
	 * does not belong to a {@link TabContainer}.
	 */
	private void supposeBelongsToTabContainer() {
		
		//Checks if the current tab container tab belongs to a tab container.
		if (!belongsToTabContainer()) {
			throw new InvalidArgumentException(
				this,
				"does not belong to a tab container"
			);
		}
	}
	
	//method
	/**
	 * @throws InvalidStateExpceiton
	 * if the current {@link TabContainerTab} is empty.
	 */
	private void supposeIsNotEmpty() {
		
		//Checks if the current tab container tab is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
