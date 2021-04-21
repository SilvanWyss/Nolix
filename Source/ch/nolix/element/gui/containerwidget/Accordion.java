//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PluralLowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.base.SubElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-08-13
 * @lines 540
 */
public final class Accordion extends ContainerWidget<Accordion, AccordionLook> {
	
	//constant
	public static final AccordionExpansionBehavior DEFAULT_EXPANSION_BEHAVIOR = AccordionExpansionBehavior.OPEN_ONE_TAB;
	
	//constants
	private static final String EXPANSION_BEHAVIOUR_HEADER = "ExpansionBehavior";
	private static final String TAB_HEADER = PascalCaseCatalogue.TAB;
	private static final String TAB_HEADER_LOOK_HEADER = "TabHeaderLook";
	
	//attribute
	private final VerticalStack mainVerticalStack = new VerticalStack();
	
	//attribute
	private final MutableValue<AccordionExpansionBehavior> expansionBehavior =
	new MutableValue<>(
		EXPANSION_BEHAVIOUR_HEADER,
		DEFAULT_EXPANSION_BEHAVIOR,
		this::setExpansionBehavior,
		AccordionExpansionBehavior::fromSpecification,
		AccordionExpansionBehavior::getSpecification
	);
	
	//attribute
	private final MultiValue<AccordionTab> tabs =
	new MultiValue<>(
		TAB_HEADER,
		this::addTab,
		AccordionTab::fromSpecification,
		AccordionTab::getSpecification
	);
	
	//attribute
	private final SubElement<StackLook> tabHeaderLook = new SubElement<>(TAB_HEADER_LOOK_HEADER, new StackLook());
	
	//constructor
	/**
	 * Creates a new {@link Accordion}.
	 */
	public Accordion() {
		reset();
	}
	
	//method
	/**
	 * Adds the given tab to the current {@link Accordion}.
	 * 
	 * @param tab
	 * @return the current {@link Accordion}.
	 * @throws ArgumentIsNullException if the given tab is null.
	 */
	public Accordion addTab(final AccordionTab tab) {
		
		Validator.assertThat(tab).thatIsNamed(LowerCaseCatalogue.TAB).isNotNull();
		
		tab.setParentAccordion(this);
		tabs.add(tab);
		mainVerticalStack.addWidget(tab.getRefMainVerticalStack());
		
		if (mustExpandAtLeastOneTabWhenNotEmpty() && getTabCount() < 2) {
			tab.expand();
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link Accordion}.
	 * 
	 * @param tabs
	 * @return the current {@link Accordion}.
	 * @throws ArgumentIsNullException if the given tabs is null.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public Accordion addTab(final AccordionTab... tabs) {
		
		Validator.assertThat(tabs).thatIsNamed(PluralLowerCaseCatalogue.TABS).isNotNull();
		
		return addTabs(ReadContainer.forArray(tabs));
	}
	
	//method
	/**
	 * Adds a new tab to the current {@link Accordion}. The new tab will have the given header and widget.
	 * 
	 * @param header
	 * @param widget
	 * @return the current {@link Accordion}.
	 */
	public Accordion addTab(final String header, final Widget<?, ?> widget) {
		return addTab(new AccordionTab().setHeader(header).setWidget(widget));
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link Accordion}.
	 * 
	 * @param tabs
	 * @return the current {@link Accordion}.
	 * @throws ArgumentIsNullException if the given tabs is null.
	 * @throws ArgumentIsNullException if one of the given tabs is null.
	 */
	public Accordion addTabs(final Iterable<AccordionTab> tabs) {
		
		Validator.assertThat(tabs).thatIsNamed(PluralLowerCaseCatalogue.TABS).isNotNull();
		
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
		mainVerticalStack.clear();
	}
	
	//method
	/**
	 * Collapses all tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion collapseAllTabs() {
		
		assertCanCollapseAllTabs();
		
		getRefTabs().forEach(this::collapseTab);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link Accordion} collapse the given tab if
	 * the current {@link Accordion} can collapse at least 1 tab.
	 * 
	 * @param tab
	 */
	public void collapseTab(final AccordionTab tab) {
		if (canCollapseAnExpandedTab()) {
			tab.collapse();
		}
	}

	//method
	/**
	 * Expands all tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion expandAllTabs() {
		
		assertCanExpandAllTabs();
				
		getRefTabs().forEach(this::expandTab);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link Accordion} expand the given tab.
	 * 
	 * @param tab
	 */
	public void expandTab(final AccordionTab tab) {
		if (!tab.isExpanded()) {
			expandWhenNotExpanded(tab);
		}
	}

	//method
	/**
	 * @return the expansion behavior of the current {@link Accordion}.
	 */
	public AccordionExpansionBehavior getExpansionBehavior() {
		return expansionBehavior.getValue();
	}
		
	//method
	/**
	 * @return the look of the headers of the tabs of the current {@link Accordion}.
	 */
	public StackLook getRefTabHeaderLook() {
		return tabHeaderLook.getSubElement();
	}
	
	//method
	/**
	 * @return the tabs of the current {@link Accordion}.
	 */
	public IContainer<AccordionTab> getRefTabs() {
		return tabs;
	}
	
	//method
	/**
	 * The number of tabs of a {@link Accordion} does not need to equal
	 * the number of widgets of a {@link Accordion} because the tabs of a {@link Accordion} can be empty.
	 * 
	 * @return the number of tabs of the current {@link Accordion}.
	 */
	public int getTabCount() {
		return tabs.getElementCount();
	}
	
	//method
	/**
	 * @return true if the current {@link Accordion} must expand at least one tab for the case
	 * when the current {@link Accordion} is not empty.
	 */
	public boolean mustExpandAtLeastOneTabWhenNotEmpty() {
		
		//Enumerates the expansion behavior of the current Accordion.
		switch (getExpansionBehavior()) {
			case OPEN_ONE_TAB:
			case OPEN_SEVERAL_TABS:
				return true;
			default:
				return false;
		}
	}
	
	//method
	/**
	 * @return true if the current {@link Accordion} must expand at most one tab.
	 */
	public boolean mustExpandAtMostOneTab() {
		
		//Enumerates the expansion behavior of the current Accordion.
		switch (getExpansionBehavior()) {
			case OPEN_ONE_TAB_OR_NONE:
			case OPEN_ONE_TAB:
				return true;
			default:
				return false;
		}
	}
	
	//method
	/**
	 * Lets the given lookMutator access the look of the tab header of the current {@link Widget}.
	 * 
	 * @param lookMutator
	 * @return the current {@link Accordion}.
	 */
	public Accordion onTabHeaderLook(final IElementTaker<StackLook> lookMutator) {
		
		lookMutator.run(getRefTabHeaderLook());
		
		return this;
	}
	
	//method
	/**
	 * Sets the expansion behavior of the current {@link Accordion}.
	 * 
	 * @param expansionBehavior
	 * @return the current {@link Accordion}.
	 * @throws ArgumentIsNullException if the given expansionBehavior is null.
	 */
	public Accordion setExpansionBehavior(final AccordionExpansionBehavior expansionBehavior) {
		
		this.expansionBehavior.setValue(expansionBehavior);
		
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
	protected AccordionLook createLook() {
		return new AccordionLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		
		//Iterates the tabs of the current Accordion.
		for (final var t : getRefTabs()) {
			
			//Handles the case that the current tab is not empty.
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
		return mainVerticalStack.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		return mainVerticalStack.getWidth();
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
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
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
	protected void paintContentArea(final IPainter painter, final AccordionLook accordionLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
		getRefTabs().forEach(AccordionTab::recalculate);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {
		getRefTabHeaderLook().reset();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetContainerWidget() {
		
		mainVerticalStack.reset();
		getRefLook().addChild(getRefTabHeaderLook());		
		getRefTabHeaderLook()
		.setTextColorForState(WidgetLookState.BASE, Color.GREY)
		.setTextColorForState(WidgetLookState.HOVER, Color.BLACK);
		
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
	}
	
	//method
	private void assertCanCollapseAllTabs() {
		if (!canCollapseAllTabs()) {
			throw new InvalidArgumentException(this, "cannot collapse all tabs");
		}
	}
	
	//method
	private void assertCanExpandAllTabs() {
		if (!canExpandAllTabs()) {
			throw new InvalidArgumentException(this, "cannot expand all tabs");
		}
	}
	
	//method
	private boolean canCollapseAllTabs() {
		return (isEmpty() || !mustExpandAtMostOneTab());
	}
	
	//method
	private boolean canCollapseAnExpandedTab() {
		return (!mustExpandAtLeastOneTabWhenNotEmpty() || getRefTabs().getCount(AccordionTab::isExpanded) > 1);
	}
	
	//method
	private boolean canExpandAllTabs() {
		return (getTabCount() < 2 || !mustExpandAtMostOneTab());
	}
	
	//method
	private void expandWhenNotExpanded(final AccordionTab tab) {
		
		if (mustExpandAtMostOneTab()) {
			getRefTabs().forEach(AccordionTab::collapse);
		}
						
		tab.expand();
	}
}
