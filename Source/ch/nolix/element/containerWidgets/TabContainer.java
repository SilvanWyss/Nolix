//package declaration
package ch.nolix.element.containerWidgets;

import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;

//class
/**
 * A {@link TabContainer} is clearable.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 520
 */
public final class TabContainer
extends ContainerWidget<TabContainer, TabContainerLook>
implements Clearable<TabContainer> {

	//constant
	public static final String TYPE_NAME = "TabContainer";
	
	//TODO: Use a mainWidget.
	//attribute
	private final HorizontalStack menu = new HorizontalStack();
	
	//optional attribute
	private Label nextMenuItemLabel;
	
	//multi-attribute
	private final List<TabContainerTab> tabs = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link TabContainer}.
	 */
	public TabContainer() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainer} with the given tabs.
	 * 
	 * @param tabs
	 * @throws ArgumentIsNullException if the given tabs is null.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public TabContainer(final Iterable<TabContainerTab> tabs) {
		
		resetAndApplyDefaultConfiguration();
		
		addTabs(tabs);
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainer} with the given tabs.
	 * 
	 * @param tabs
	 * @throws ArgumentIsNullException if the given tabs is null.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public TabContainer(final TabContainerTab... tabs) {
		
		//Calls other constructor.
		this(new ReadContainer<TabContainerTab>(tabs));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.TAB:
				addTab(TabContainerTab.fromSpecification(attribute));
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
	 * @throws ArgumentIsNullException if the given tabs is null.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public TabContainer addTabs(final Iterable<TabContainerTab> tabs) {
		
		//Checks if the given tabs is not null.
		Validator
		.suppose(tabs)
		.thatIsNamed("tabs")
		.isNotNull();
		
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
	 * @throws ArgumentIsNullException if the given tab is null.
	 * @throws InvalidArgumentException if the given tab belongs already to a {@link TabContainer}.
	 */
	public TabContainer addTab(final TabContainerTab tab) {
		
		//Checks if the given tab is not null.
		Validator
		.suppose(tab)
		.thatIsNamed(PascalCaseNameCatalogue.TAB)
		.isNotNull();
		
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
	 * @throws ArgumentIsNullException if the given tabs is null.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public TabContainer addTab(TabContainerTab... tabs) {
		return addTabs(new ReadContainer<TabContainerTab>(tabs));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
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
	@Override
	public List<Node> getAttributes() {
		
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
	 * @return the selected tab of the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link TabContainer} does not contain a selected tab.
	 */
	public TabContainerTab getRefSelectedTab() {
		return getRefTabs().getRefFirst(t -> t.isSelected());
	}
	
	//method
	/**
	 * @return the selected widget of the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainer}
	 * does not contain a selected widget.
	 */
	public Widget<?, ?> getRefSelectedWidget() {
		return getRefSelectedTab().getRefWidget();
	}
	
	//method
	/**
	 * @param header
	 * @return the tab with the given header from the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainer}
	 * does not contain a tab with the given header.
	 */
	public TabContainerTab getRefTabByHeader(final String header) {
		return getRefTabs().getRefFirst(t -> t.hasHeader(header));
	}
	
	//method
	/**
	 * @return the tabs of the current {@link TabContainer}.
	 */
	public ReadContainer<TabContainerTab> getRefTabs() {
		return new ReadContainer<>(tabs);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return getRefTabs().isEmpty();
	}
	
	@Override
	public void noteLeftMouseButtonPressOnViewAreaWhenEnabled() {		
		nextMenuItemLabel = (Label)menu.getChildWidgets().getRefFirstOrNull(mi -> mi.isUnderCursor());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseWhenEnabled() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonReleaseWhenEnabled();
		
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
	@Override
	public void recalculate() {
		
		//Calls method of the base class.
		super.recalculate();	
		
		//Handles the case that the current tab container contains a selected widget.
		if (containsSelectedTabWithWidget()) {
			getRefSelectedWidget().setPositionOnParent(
				0,				
				menu.getHeight()
				+ getRefLook().getRecursiveOrDefaultMenuMargin()
			);
		}
	}
	
	//method
	/**
	 * Selects the tab of the current {@link TabContainer} that has the given header.
	 * 
	 * @param header
	 * @return the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link TabContainer} does not contain a tab with the given header.
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
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
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
	@Override
	protected TabContainerLook createLook() {
		return new TabContainerLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		
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
	@Override
	protected void fillUpPaintableWidgets(final List<Widget<?, ?>> list) {
		
		list.addAtEnd(menu);
		
		if (containsSelectedTabWithWidget()) {
			list.addAtEnd(getRefSelectedWidget());
		}
	}
	

	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getContentAreaHeight() {
		
		var height = menu.getHeight();
		
		height += getRefLook().getRecursiveOrDefaultMenuMargin();
		
		//Handles the case that the current tab container contains tabs.
		if (containsAny()) {
			height += getRefTabs().getMaxByInt(t -> t.getHeight());
		}
		
		return height;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getContentAreaWidth() {
		
		//Handles the case that the current tab container does not contain a tabs.
		if (isEmpty()) {
			return menu.getWidth();
		}
		
		//Handles the case that the current tab container contains tabs.
		else {
			return
			Calculator.getMax(
				menu.getWidth(),
				getRefTabs().getMaxByInt(t -> t.getWidth())
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
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
	}
	
	//package-visible method
	void internal_addChildWidget(final Widget<?, ?> childWidget) {
		
		//Calls other method.
		addChildWidget(childWidget);
	}
}
