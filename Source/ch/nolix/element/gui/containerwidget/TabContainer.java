//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PluralLowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.math.Calculator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.widget.Label;

//class
/**
 * @author Silvan Wyss
 * @date 2016-05-01
 * @lines 550
 */
public final class TabContainer extends ContainerWidget<TabContainer, TabContainerLook> {
	
	//constant
	public static final String TYPE_NAME = "TabContainer";
	
	//TODO: Use a mainWidget.
	//attribute
	private final HorizontalStack menu = new HorizontalStack();
	
	//optional attribute
	private Label nextMenuItemLabel;
	
	//multi-attribute
	private final LinkedList<TabContainerTab> tabs = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link TabContainer}.
	 */
	public TabContainer() {
		
		//TODO
		/*
		getRefLook()
		.setBaseMenuItemLook(
			new OldTabContainerMenuItemLook()
			.setMinWidth(100)
			.setPadding(5)
		)
		.setHoverMenuItemLook(
			new OldTabContainerMenuItemLook()
			.setBackgroundColor(Color.LIGHT_GREY)
			.setPadding(5)
		)
		.setSelectionMenuItemLook(
			new OldTabContainerMenuItemLook()
			.setBackgroundColor(Color.DARK_GREY)
			.setPadding(5)
		);
		*/
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseCatalogue.TAB:
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
		
		//Asserts that the given tabs is not null.
		Validator.assertThat(tabs).thatIsNamed(PluralLowerCaseCatalogue.TABS).isNotNull();
		
		//Iterates the given tabs.
		tabs.forEach(this::addTab);
		
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
		
		//Asserts that the given tab is not null.
		Validator.assertThat(tab).thatIsNamed(PascalCaseCatalogue.TAB).isNotNull();
		
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
		return addTabs(ReadContainer.forArray(tabs));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		tabs.clear();
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainer}
	 * contains a selected tab.
	 */
	public boolean containsSelectedTab() {
		return getRefTabs().contains(TabContainerTab::isSelected);
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
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Iterates the tabs of the current TabContainer.
		for (final var t : getRefTabs()) {
			list.addAtEnd(t.getSpecificationAs(PascalCaseCatalogue.TAB));
		}
	}
	
	//method
	/**
	 * @return the selected tab of the current {@link TabContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link TabContainer} does not contain a selected tab.
	 */
	public TabContainerTab getRefSelectedTab() {
		return getRefTabs().getRefFirst(TabContainerTab::isSelected);
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
		return ReadContainer.forIterable(tabs);
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
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		
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
	protected int getNaturalContentAreaHeight() {
		
		var height = menu.getHeight();
		
		//TODO: height += getRefLook().getMenuMargin();
		
		//Handles the case that the current tab container contains tabs.
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
		
		//Handles the case that the current tab container does not contain a tabs.
		if (isEmpty()) {
			return menu.getWidth();
			
		//Handles the case that the current tab container contains tabs.
		} else {
			return
			Calculator.getMax(
				menu.getWidth(),
				getRefTabs().getMaxInt(TabContainerTab::getWidth)
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
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
	protected void paintContentArea(
		final TabContainerLook tabContainerLook,
		final IPainter painter
	) {
		
		//TODO: menu.setElementMargin(tabContainerLook.getRecursiveOrDefaultMenuItemMargin());
		
		//TODO
		/*
		final var baseMenuItemLook = tabContainerLook.getRefRecursiveOrDefaultBaseMenuItemLook();
		final var hoverMenuItemLook = tabContainerLook.getRefRecursiveOrDefaultHoverMenuItemLook();
		final var selectedMenuItemLook = tabContainerLook.getRefRecursiveOrDefaultSelectionMenuItemLook();
		
		for (final var t : getRefTabs()) {
			
			final var label = t.getRefMenuItem();
			label.setText(t.getHeader());
			
			if (baseMenuItemLook.hasMinWidth()) {
				label.setMinWidth(baseMenuItemLook.getOwnOrDefaultMinWidth());
			}
			
			label.getRefBaseLook().reset();
			
			label
			.getRefBaseLook()
			.setPaddings(baseMenuItemLook.getOwnOrDefaultPadding())
			.setTextSize(baseMenuItemLook.getOwnOrDefaultTextSize())
			.setTextColor(baseMenuItemLook.getOwnOrDefaultTextColor());
			
			if (baseMenuItemLook.hasBackgroundColor()) {
				label
				.getRefBaseLook()
				.setBackgroundColor(baseMenuItemLook.getOwnOrDefaultBackgroundColor());
			}
			
			label.getRefHoverLook().reset();
			
			label
			.getRefHoverLook()
			.setPaddings(hoverMenuItemLook.getOwnOrDefaultPadding())
			.setTextSize(hoverMenuItemLook.getOwnOrDefaultTextSize())
			.setTextColor(hoverMenuItemLook.getOwnOrDefaultTextColor());
			
			if (hoverMenuItemLook.hasBackgroundColor()) {
				label
				.getRefHoverLook()
				.setBackgroundColor(hoverMenuItemLook.getOwnOrDefaultBackgroundColor());
			}
			
			label.getRefFocusLook().reset();
			
			label
			.getRefFocusLook()
			.setPaddings(selectedMenuItemLook.getOwnOrDefaultPadding())
			.setTextSize(selectedMenuItemLook.getOwnOrDefaultTextSize())
			.setTextColor(selectedMenuItemLook.getOwnOrDefaultTextColor());
			
			if (selectedMenuItemLook.hasBackgroundColor()) {
				label
				.getRefFocusLook()
				.setBackgroundColor(selectedMenuItemLook.getOwnOrDefaultBackgroundColor());
			}
		}
		*/
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
				
		//Handles the case that the current tab container contains a selected widget.
		if (containsSelectedTabWithWidget()) {
			getRefSelectedWidget().setPositionOnParent(
				0,				
				menu.getHeight()
				//TODO + getRefLook().getRecursiveOrDefaultMenuMargin()
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetContainerWidget() {
		clear();
	}
}
