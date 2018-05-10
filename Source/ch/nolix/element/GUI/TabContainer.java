//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidStateException.EmptyStateException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A {@link TabContainer} is clearable.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 100
 */
public final class TabContainer
extends Container<TabContainer, TabContainerLook>
implements Clearable<TabContainer> {

	//constant
	public static final String TYPE_NAME = "TabContainer";

	//attribute
	private final HorizontalStack menu = new HorizontalStack();
	
	//multi-attribute
	private final List<TabContainerTab> tabs = new List<TabContainerTab>();
	
	//constructor
	/**
	 * Creates a new empty tab container.
	 */
	public TabContainer() {
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainer} with the given tabs.
	 * 
	 * @param tabs
	 * @throws NullArgumentException if the given tab container is null.
	 * @throws NullArgumentException if one of the given tabs is null.
	 */
	public TabContainer(final Iterable<TabContainerTab> tabs) {
		
		//Calls other constructor.
		this();
		
		addTabs(tabs);
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainer} with the given tabs.
	 * 
	 * @param tabs
	 * @throws NullArgumentException if the given tab container is null.
	 * @throws NullArgumentException if one of the given tabs is null.
	 */
	public TabContainer(final TabContainerTab... tabs) {
		
		//Calls other constructor.
		this(new ReadContainer<TabContainerTab>(tabs));
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link TabContainer}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.TAB:
				addTab(TabContainerTab.createFromSpecification(attribute));
				break;
			default:		
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}	
	}
	
	//method
	/**
	 * Adds the given tab to the current {@link TabContainer}.
	 * 
	 * @param tab
	 * @return the current {@link TabContainer}.
	 * @throws NullArgumentException if the given tab is null.
	 */
	public TabContainer addTab(final TabContainerTab tab) {
		
		Validator
		.suppose(tab)
		.thatIsNamed(PascalCaseNameCatalogue.TAB)
		.isNotNull();
		
		final var menuItemLabel = new Label();
		menuItemLabel.setText(tab.getHeader());
		menuItemLabel.setCursorIcon(CursorIcon.Hand);
		menu.addWidget(menuItemLabel);
		
		tab.setParentTabContainer(this, menuItemLabel);
		tabs.addAtEnd(tab);
		
		if (getRefTabs().containsOne()) {
			selectTab(tab.getHeader());
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link TabContainer}.
	 * 
	 * @param tabs
	 * @return the current {@link TabContainer}.
	 */
	public TabContainer addTab(TabContainerTab... tabs) {
		return addTabs(new ReadContainer<TabContainerTab>(tabs));
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link TabContainer}.
	 * 
	 * @param tabs
	 * @return the current {@link TabContainer}.
	 * @throws NullArgumentException if the given tab container is null.
	 */
	public TabContainer addTabs(final Iterable<TabContainerTab> tabs) {
		
		//Checks if the given tabs is not null.
		Validator
		.suppose(tabs)
		.thatIsNamed("tabs container")
		.isNotNull();
		
		tabs.forEach(t -> addTab(t));
		
		return this;
	}
	
	//method
	/**
	 * Removes all tabs of the current {@link TabContainer}.
	 * 
	 * @return the current {@link TabContainer}.
	 */
	public TabContainer clear() {
		
		tabs.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainer} contains a selected widget.
	 */
	public boolean containsSelectedWidget() {
		return (containsAny() && getRefSelectedTab().containsAny());
	}
		
	//method
	/**
	 * @return the attributes of the current {@link TabContainer}.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Iterates the tabs of the current tab container.
		for (final var t : getRefTabs()) {
			attributes.addAtEnd(t.getSpecificationAs(PascalCaseNameCatalogue.TAB));
		}
				
		return attributes;
	}
	
	//TODO: Add getActiveCursorIcon method to Widget.
	//method
	/**
	 * @return the cursor icon of the current {@link TabContainer}.
	 */
	public CursorIcon getActiveCursorIcon() {
		
		//Extracts the menu item under the cursor if there exists one.
		final var menuItemUnderCursor =
		menu.getRefWidgets().getRefFirstOrNull(mi -> mi.isUnderCursor());
		
		//Handles the case that there is a menu item under the cursor.
		if (menuItemUnderCursor != null) {
			return menuItemUnderCursor.getCursorIcon();
		}
		
		//Handles the case that there is no menu item under the cursor.
			//Calls method of the base class.
			return super.getCursorIcon();
	}
	
	//method
	/**
	 * @return the selected tab of the current {@link TabContainer}.
	 * @throws EmptyArgumentException if the current {@link TabContainer} contains no tab.
	 */
	public TabContainerTab getRefSelectedTab() {
		
		supposeIsNotEmpty();
		
		return getRefTabs().getRefFirst(t -> t.isSelected());
	}
	
	//method
	public Widget<?, ?> getRefSelectedWidget() {
		return getRefSelectedTab().getRefWidget();
	}
	
	//method
	/**
	 * @return the widgets of the current {@link TabContainer}.
	 */
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		
		final var widgets = new List<Widget<?, ?>>();
		
		//Iterates the tabs of the current tab container.
		for (final var t: getRefTabs()) {
			if (t.containsAny()) {
				widgets.addAtEnd(t.getRefWidget());
			}
		}
		
		return new ReadContainer<>(widgets);
	}
	
	//method
	public boolean isEmpty() {
		return getRefTabs().isEmpty();
	}
	
	//method
	/**
	 * Lets the current {@link TabContainer} note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		final var selectedMenuItem = (Label)menu.getRefWidgets().getRefFirstOrNull(mi -> mi.isUnderCursor());
		
		if (selectedMenuItem != null) {
			selectTab(selectedMenuItem.getText());
		}
	}
	
	//method
	/**
	 * Lets the current {@link TabContainer} note a mouse move.
	 */
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		menu.getRefWidgets().forEach(w -> w.noteAnyMouseMove());
	}
	
	//method
	/**
	 * Selects the tab of the current {@link TabContainer} that has the given header.
	 * 
	 * @param header
	 * @return the current {@link TabContainer}.
	 * @throws UnexistingAttributeException
	 * if the current {@link TabContainer} contains no tab with the given header.
	 */
	public TabContainer selectTab(final String header) {
		
		if (getRefTabs().contains(t -> t.isSelected())) {
			getRefSelectedTab().unselect();
		}
		
		getRefTabs().getRefFirst(t -> t.hasHeader(header)).select();
		
		return this;
	}
	
	//method
	/**
	 * Sets the cursor position of the current {@link TabContainer} from the parent container.
	 * 
	 * @param cursorXPositionOnParentContainer
	 * @param cursorYPositionOnParentContainer
	 */
	public void setCursorPositionFromParentContainer(final int cursorXPositionOnParentContainer, final int relativeMouseYPosition) {
		
		//Calls method of the base class.
		super.setCursorPositionFromParentContainer(cursorXPositionOnParentContainer, relativeMouseYPosition);
		
		//TODO: Correct cursor position.
		menu.setCursorPositionFromParentContainer(
			getCursorXPositionOnScrollArea(),
			getCursorYPositionOnScrollArea()
		);
	}
	
	//method
	/**
	 * @return a new widget look for the current {@link TabContainer}.
	 */
	protected TabContainerLook createWidgetLook() {
		return new TabContainerLook();
	}
	
	//method
	/**
	 * @return the current height of the content of the current {@link TabContainer}
	 */
	protected final int getContentHeight() {
		
		var height = menu.getHeight();
		
		height += getRefCurrentLook().getRecursiveOrDefaultMenuMargin();
		
		if (containsAny()) {
			height += getRefTabs().getMaxInt(t -> t.getHeight());
		}
		
		return height;
	}

	//method
	/**
	 * @return the current width of the content of the current {@link TabContainer}
	 */
	protected final int getContentWidth() {
		
		if (containsAny()) {
			return Calculator.getMax(
				menu.getWidth(),
				getRefTabs().getMaxInt(t -> t.getWidth())
			);
		}
		
		return menu.getWidth();
	}
	
	/**
	 * Paints the current {@link TabContainer} using the given rectangle structure and given painter.
	 * 
	 * @param rectangleStructure
	 * @param painter
	 */
	protected void paintContent(
		final TabContainerLook tabContainerLook,
		final IPainter painter
	) {
		menu.setElementMargin(tabContainerLook.getRecursiveOrDefaultMenuItemMargin());
		
		menu.paintUsingPositionOnContainer(painter);
		
		if (containsAny() && getRefSelectedTab().containsAny()) {
			getRefSelectedWidget().paintUsingPositionOnContainer(painter);
		}
	}
	
	//method
	/**
	 * Sets the relative position of the current {@link TabContainer}.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected void setPositionOnParent(
		final int relaitveXPosition,
		final int relativeYPosition
	) {
		
		//Calls method of the base class.
		super.setPositionOnParent(relaitveXPosition, relativeYPosition);
		
		menu.setPositionOnParent(
			getContentXPosition() - getViewAreaXPositionOnScrollArea(),
			getContentYPosition() - getViewAreaYPositionOnScrollArea()
		);
		
		if (containsSelectedWidget()) {
			getRefSelectedWidget().setPositionOnParent(
				getContentXPosition()
				- getViewAreaXPositionOnScrollArea(),
				getContentYPosition()
				+ menu.getHeight()
				+ getRefCurrentLook().getRecursiveOrDefaultMenuMargin()
				- getViewAreaYPositionOnScrollArea()
			);
		}
	}
	
	//method
	/**
	 * @return the tabs of the current {@link TabContainer}.
	 */
	private ReadContainer<TabContainerTab> getRefTabs() {
		return new ReadContainer<TabContainerTab>(tabs);
	}
	
	//method
	/**
	 * @throws EmptyStateException if the current {@link TabContainer} contains no tab.
	 */
	private void supposeIsNotEmpty() {
		
		//Checks if the current tab container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
	}
}
