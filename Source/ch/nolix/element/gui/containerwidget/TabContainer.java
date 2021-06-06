//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.math.Calculator;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.SubElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelLook;

//class
/**
 * @author Silvan Wyss
 * @date 2016-05-01
 * @lines 500
 */
public final class TabContainer extends ContainerWidget<TabContainer, TabContainerLook> {
	
	//constants
	private static final String MENU_ITEM_LOOK_HEADER = "MenuItemLook";
	private static final String TAB_HEADER = PascalCaseCatalogue.TAB;
	
	//attributes
	private final VerticalStack mainVerticalStack = new VerticalStack();
	private final HorizontalStack menu = new HorizontalStack();
	private final SubElement<LabelLook> menuItemLook = new SubElement<>(MENU_ITEM_LOOK_HEADER, new LabelLook());
	private final SingleContainer currentTabContainer = new SingleContainer();
	
	//attribute
	private final MultiValue<TabContainerTab> tabs =
	new MultiValue<>(
		TAB_HEADER,
		this::addTab,
		TabContainerTab::fromSpecification,
		TabContainerTab::getSpecification
	);
	
	//optional attribute
	private Label nextMenuItemLabel;
	
	//constructor
	/**
	 * Creates a new {@link TabContainer}.
	 */
	public TabContainer() {
		reset();
	}
	
	//method
	/**
	 * Adds a new tab to the current {@link TabContainer} that has the given header and widget.
	 * 
	 * @param header
	 * @param widget
	 * @return the current {@link TabContainer}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given widget is null.
	 * @throws InvalidArgumentException if the given tab belongs already to a {@link TabContainer}.
	 */
	public TabContainer addTab(final String header, final Widget<?, ?> widget) {
		return addTab(new TabContainerTab().setHeader(header).setWidget(widget));
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
		
		//Adds the given tab to the current TabContainer.
		tabs.add(tab);
		menu.addWidget(tab.getRefMenuItem());
		
		//Selects the given tab if its the first tab of the current TabContainer.
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
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public TabContainer addTab(final TabContainerTab... tabs) {
		
		//Calls other method
		return addTabs(ReadContainer.forArray(tabs));
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link TabContainer}.
	 * 
	 * @param tabs
	 * @return the current {@link TabContainer}.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public TabContainer addTabs(final Iterable<TabContainerTab> tabs) {
		
		tabs.forEach(this::addTab);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		tabs.clear();
		menu.clear();
		currentTabContainer.clear();
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainer} contains a selected tab.
	 */
	public boolean containsSelectedTab() {
		return getRefTabs().contains(TabContainerTab::isSelected);
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainer} contains a selected tab with a {@link Widget}.
	 */
	public boolean containsSelectedTabWithWidget() {
		return (containsSelectedTab() && getRefSelectedTab().containsAny());
	}
	
	//method
	/**
	 * @return the  look of the menu items of the current {@link TabContainer}.
	 */
	public LabelLook getRefMenuItemLook() {
		return menuItemLook.getSubElement();
	}
	
	//method
	/**
	 * @return the selected tab of the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException if* the current {@link TabContainer} does not contain
	 * a selected tab.
	 */
	public TabContainerTab getRefSelectedTab() {
		return getRefTabs().getRefFirst(TabContainerTab::isSelected);
	}
	
	//method
	/**
	 * @return the selected {@link Widget} of the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainer} does not contain
	 * a selected {@link Widget}.
	 */
	public Widget<?, ?> getRefSelectedWidget() {
		return getRefSelectedTab().getRefWidget();
	}
	
	//method
	/**
	 * @param header
	 * @return the tab with the given header from the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainer} does not contain
	 * a tab with the given header.
	 */
	public TabContainerTab getRefTabByHeader(final String header) {
		return getRefTabs().getRefFirst(t -> t.hasHeader(header));
	}
	
	//method
	/**
	 * @return the tabs of the current {@link TabContainer}.
	 */
	public ReadContainer<TabContainerTab> getRefTabs() {
		return ReadContainer.forIterable(tabs);
	}
	
	//method
	/**
	 * Lets the given lookMutator access the look of the menu items of the current {@link Widget}.
	 * 
	 * @param lookMutator
	 * @return the current {@link TabContainer}.
	 */
	public TabContainer onMenuItemLook(final IElementTaker<LabelLook> lookMutator) {
		
		lookMutator.run(getRefMenuItemLook());
		
		return this;
	}
	
	//method
	/**
	 * Selects the tab of the current {@link TabContainer} that has the given header.
	 * 
	 * @param header
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainer} does not contain
	 * a tab with the given header.
	 */
	public void selectTab(final String header) {
		selectTab(getRefTabByHeader(header));
	}
	
	//method
	/**
	 * Lets the current {@link TabContainer} select the given tab.
	 * 
	 * @param tab
	 */
	public void selectTab(final TabContainerTab tab) {
		
		//Handles the case that the given tab is not already selected.
		if (!tab.isSelected()) {
			selectTabWhenNotSelected(tab);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
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
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		
		//Iterates the tabs of the current TabContainer.
		for (final var t: getRefTabs()) {
			
			//Handles the case that the current tab contains a Widget.
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
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(mainVerticalStack);
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		
		var height = menu.getHeight();
		
		//Handles the case that the current TabContainer contains tabs.
		if (containsAny()) {
			height += getRefTabs().getMaxInt(TabContainerTab::getHeight);
		}
		
		return height;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		
		//Handles the case that the current TabContainer does not contain a tabs.
		if (isEmpty()) {
			return menu.getWidth();
			
		//Handles the case that the current TabContainer contains tabs.
		} else {
			return Calculator.getMax(menu.getWidth(), getRefTabs().getMaxInt(TabContainerTab::getWidth));
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {		
		nextMenuItemLabel = (Label)menu.getChildWidgets().getRefFirstOrNull(Widget::isUnderCursor);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {		
		if (nextMenuItemLabel != null) {
			if (!nextMenuItemLabel.isUnderCursor()) {
				nextMenuItemLabel = null;
			} else {
				selectTab(getRefTabs().getRefFirst(t -> t.getRefMenuItem() == nextMenuItemLabel));
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final IPainter painter, final TabContainerLook tabContainerLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
		for (final var t : getRefTabs()) {
			
			t.getRefMenuItem().getRefLook().setFrom(getRefMenuItemLook());
			
			if (t.isSelected() && t.containsAny()) {
				currentTabContainer.setWidget(t.getRefWidget());
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfiguration() {
		
		getRefMenuItemLook().reset();
		
		getRefMenuItemLook().setBackgroundColorForState(WidgetLookState.HOVER, Color.LIGHT_GREY);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetContainerWidget() {
		
		mainVerticalStack.reset();
		menu.reset();
		
		currentTabContainer.reset();
		
		mainVerticalStack.addWidget(menu, currentTabContainer);
		getRefLook().addChild(getRefMenuItemLook());
	}
	
	//method
	/**
	 * Lets the current {@link TabContainer} select the given tab for the case when
	 * the given tab is not already selected.
	 * 
	 * @param tab
	 */
	private void selectTabWhenNotSelected(final TabContainerTab tab) {
		
		//Handles the case that the current TabContainer contains a selected tab.
		if (containsSelectedTab()) {
			getRefSelectedTab().unselect();
		}
		
		//Selects the given tab.
		tab.select();
		
		if (tab.containsAny()) {
			currentTabContainer.setWidget(tab.getRefWidget());
		}
	}
}
