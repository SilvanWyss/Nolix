//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link TabContainer} is clearable.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 550
 */
public final class TabContainer
extends Container<TabContainer, TabContainerLook>
implements Clearable<TabContainer> {

	//constant
	public static final String TYPE_NAME = "TabContainer";

	//attribute
	private final HorizontalStack menu = new HorizontalStack();
	
	//optional attribute
	private Label nextMenuItemLabel;
	
	//multi-attribute
	private final List<TabContainerTab> tabs = new List<TabContainerTab>();
	
	//constructor
	/**
	 * Creates a new {@link TabContainer}.
	 */
	public TabContainer() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainer} with the given tabs.
	 * 
	 * @param tabs
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumentException if one of the given tabs is not an instance.
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
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumentException if one of the given tabs is not an instance.
	 */
	public TabContainer(final TabContainerTab... tabs) {
		
		//Calls other constructor.
		this(new ReadContainer<TabContainerTab>(tabs));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
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
	 * Adds the given tabs to the current {@link TabContainer}.
	 * 
	 * @param tabs
	 * @return the current {@link TabContainer}.
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumentException if one of the given tabs is not an instance.
	 */
	public TabContainer addTabs(final Iterable<TabContainerTab> tabs) {
		
		//Checks if the given tabs is an instance.
		Validator
		.suppose(tabs)
		.thatIsNamed("tabs")
		.isInstance();
		
		//Iterates the given tabs.
		tabs.forEach(t -> addTab(t));
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tab to the current {@link TabContainer}.
	 * 
	 * @param tab
	 * @return the current {@link TabContainer}.
	 * @throws NullArgumentException if the given tab is not an instance.
	 * @throws InvalidStateException if the given tab belongs already to a {@link TabContainer}.
	 */
	public TabContainer addTab(final TabContainerTab tab) {
		
		//Checks if the given tab is an instance.
		Validator
		.suppose(tab)
		.thatIsNamed(PascalCaseNameCatalogue.TAB)
		.isInstance();
		
		//Adds the given tab to the current tab container.
		tab.setParentTabContainer(this);
		tabs.addAtEnd(tab);
		menu.addWidget(tab.getRefMenuItem());
		
		//Selects the one tab of the current tab container
		//if the current tab container contains 1 tab.
		if (getRefTabs().containsOne()) {
			selectTab(tab);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link TabContainer}.
	 * 
	 * @param tabs
	 * @return the current {@link TabContainer}.
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumentException if one of the given tabs is not an instance.
	 */
	public TabContainer addTab(TabContainerTab... tabs) {
		return addTabs(new ReadContainer<TabContainerTab>(tabs));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public TabContainer clear() {
		
		tabs.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainer}
	 * contains a selected tab.
	 */
	public boolean containsSelectedTab() {
		return getRefTabs().contains(t -> t.isSelected());
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainer}
	 * contains a selected tab with a widget.
	 */
	public boolean containsSelectedTabWithWidget() {
		return (containsSelectedTab() && getRefSelectedTab().containsAny());
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Iterates the tabs of the current tab container.
		for (final var t : getRefTabs()) {
			attributes.addAtEnd(t.getSpecificationAs(PascalCaseNameCatalogue.TAB));
		}
				
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public CursorIcon getContentAreaCursorIcon() {
		
		//Extracts the menu item under the cursor if there exists one.
		final var menuItemUnderCursor =
		menu.getRefWidgets().getRefFirstOrNull(mi -> mi.isUnderCursor());
		
		//Handles the case that there exists a menu item under the cursor.
		if (menuItemUnderCursor != null) {
			return menuItemUnderCursor.getCustomCursorIcon();
		}
		
		//Handles the case that there is no menu item under the cursor.
			//Calls method of the base class.
			return super.getCustomCursorIcon();
	}
	
	//method
	/**
	 * @return the selected tab of the current {@link TabContainer}.
	 * @throws UnexistingAttributeException
	 * if the current {@link TabContainer} contains no selected tab.
	 */
	public TabContainerTab getRefSelectedTab() {
		return getRefTabs().getRefFirst(t -> t.isSelected());
	}
	
	//method
	/**
	 * @return the selected widget of the current {@link TabContainer}.
	 * @throws UnexistingAttributeException if the current {@link TabContainer}
	 * contains no selected widget.
	 */
	public Widget<?, ?> getRefSelectedWidget() {
		return getRefSelectedTab().getRefWidget();
	}
	
	//method
	/**
	 * @param header
	 * @return the tab with the given header from the current {@link TabContainer}.
	 * @throws UnexistingAttributeException if the current {@link TabContainer}
	 * contains no tab with the given header.
	 */
	public TabContainerTab getRefTabByHeader(final String header) {
		return getRefTabs().getRefFirst(t -> t.hasHeader(header));
	}
	
	//method
	/**
	 * @return the tabs of the current {@link TabContainer}.
	 */
	public ReadContainer<TabContainerTab> getRefTabs() {
		return new ReadContainer<TabContainerTab>(tabs);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean isEmpty() {
		return getRefTabs().isEmpty();
	}
	
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		nextMenuItemLabel = (Label)menu.getRefWidgets().getRefFirstOrNull(mi -> mi.isUnderCursor());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void noteLeftMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonRelease();
		
		if (nextMenuItemLabel != null) {
			if (!nextMenuItemLabel.isUnderCursor()) {
				nextMenuItemLabel = null;
			}
			else {
				selectTab(
					getRefTabs().getRefFirst(
						t -> t.getRefMenuItem() == nextMenuItemLabel
					)
				);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
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
		
		selectTab(getRefTabByHeader(header));
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link TabContainer} select the given tab.
	 * 
	 * @param tab
	 * @return the {@link TabContainer}.
	 */
	public TabContainer selectTab(final TabContainerTab tab) {
		
		//Handles the case that the given tab is not already selected.
		if (!tab.isSelected()) {
			
			//Handles the case that the current tab container contains a selected tab.
			if (containsSelectedTab()) {
				getRefSelectedTab().unselect();
			}			
			
			//Selects the given tab.
			tab.select();
		}
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void setCursorPositionOnContentArea(
		final int cursorXPositionOnContent,
		final int cursorYPositionOnContent
	) {
		menu.setCursorPositionOnContentArea(
			cursorXPositionOnContent,
			cursorYPositionOnContent
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		getRefBaseLook()
		.setBaseMenuItemLook(
			new TabContainerMenuItemLook()
			.setMinWidth(100)
			.setPadding(5)
		)
		.setHoverMenuItemLook(
			new TabContainerMenuItemLook()
			.setBackgroundColor(Color.LIGHT_GREY)
			.setPadding(5)
		)
		.setSelectionMenuItemLook(
			new TabContainerMenuItemLook()
			.setBackgroundColor(Color.DARK_GREY)
			.setPadding(5)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected TabContainerLook createWidgetLook() {
		return new TabContainerLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {
				
		//For a better performance, this implementation does not use all comfortable methods.
			//Iterates the tabs of the current tab container.
			for (final var t: tabs) {
				
				//Handles the case that the current tab contains a widget.
				if (t.containsAny()) {
					list.addAtEnd(t.getRefWidget());
				}
			}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected final int getContentAreaHeight() {
		
		var height = menu.getHeight();
		
		height += getRefCurrentLook().getRecursiveOrDefaultMenuMargin();
		
		//Handles the case that the current tab container contains tabs.
		if (containsAny()) {
			height += getRefTabs().getMaxInt(t -> t.getHeight());
		}
		
		return height;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	protected final int getContentAreaWidth() {
		
		//Handles the case that the current tab container contains no tabs.
		if (isEmpty()) {
			return menu.getWidth();
		}
		
		//Handles the case that the current tab container contains tabs.
		else {
			return
			Calculator.getMax(
				menu.getWidth(),
				getRefTabs().getMaxInt(t -> t.getWidth())
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void paintContentArea(
		final TabContainerLook tabContainerLook,
		final IPainter painter
	) {
		
		menu.setElementMargin(tabContainerLook.getRecursiveOrDefaultMenuItemMargin());
		
		final var baseMenuItemLook = tabContainerLook.getRefRecursiveOrDefaultBaseMenuItemLook();
		final var hoverMenuItemLook = tabContainerLook.getRefRecursiveOrDefaultHoverMenuItemLook();
		final var selectedMenuItemLook = tabContainerLook.getRefRecursiveOrDefaultSelectionMenuItemLook();
		
		for (final var t : getRefTabs()) {
			
			final var label = t.getRefMenuItem();
			label.setText(t.getHeader());
			
			if (baseMenuItemLook.hasMinWidth()) {
				label.setMinWidth(baseMenuItemLook.getOwnOrDefaultMinWidth());
			}

			label
			.getRefBaseLook()
			.reset()
			.setPaddings(baseMenuItemLook.getOwnOrDefaultPadding())
			.setTextSize(baseMenuItemLook.getOwnOrDefaultTextSize())
			.setTextColor(baseMenuItemLook.getOwnOrDefaultTextColor());
			
			if (baseMenuItemLook.hasBackgroundColor()) {
				label
				.getRefBaseLook()
				.setBackgroundColor(baseMenuItemLook.getOwnOrDefaultBackgroundColor());
			}
			
			label
			.getRefHoverLook()
			.reset()
			.setPaddings(hoverMenuItemLook.getOwnOrDefaultPadding())
			.setTextSize(hoverMenuItemLook.getOwnOrDefaultTextSize())
			.setTextColor(hoverMenuItemLook.getOwnOrDefaultTextColor());
			
			if (hoverMenuItemLook.hasBackgroundColor()) {
				label
				.getRefHoverLook()
				.setBackgroundColor(hoverMenuItemLook.getOwnOrDefaultBackgroundColor());
			}
			
			label
			.getRefFocusLook()
			.reset()
			.setPaddings(selectedMenuItemLook.getOwnOrDefaultPadding())
			.setTextSize(selectedMenuItemLook.getOwnOrDefaultTextSize())
			.setTextColor(selectedMenuItemLook.getOwnOrDefaultTextColor());
			
			if (selectedMenuItemLook.hasBackgroundColor()) {
				label
				.getRefFocusLook()
				.setBackgroundColor(selectedMenuItemLook.getOwnOrDefaultBackgroundColor());
			}
		}
		
		menu.paintUsingPositionOnParent(painter);
		
		if (containsSelectedTabWithWidget()) {
			getRefSelectedWidget().paintUsingPositionOnParent(painter);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void setPositionOnParent(
		final int xPositionOnParent,
		final int yPositionOnParent
	) {
		
		//Calls method of the base class.
		super.setPositionOnParent(xPositionOnParent, yPositionOnParent);
		
		menu.setPositionOnParent(0, 0);
		
		//Handles the case that the current tab container contains a selected widget.
		if (containsSelectedTabWithWidget()) {
			getRefSelectedWidget().setPositionOnParent(
				0,				
				menu.getHeight()
				+ getRefCurrentLook().getRecursiveOrDefaultMenuMargin()
			);
		}
	}
}
