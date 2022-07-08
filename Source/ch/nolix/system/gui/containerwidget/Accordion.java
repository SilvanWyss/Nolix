//package declaration
package ch.nolix.system.gui.containerwidget;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.element.mutableelement.SubElement;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * @author Silvan Wyss
 * @date 2018-08-13
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
		Node::fromEnum
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
		
		GlobalValidator.assertThat(tab).thatIsNamed(LowerCaseCatalogue.TAB).isNotNull();
		
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
		
		GlobalValidator.assertThat(tabs).thatIsNamed(PluralLowerCaseCatalogue.TABS).isNotNull();
		
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
		
		GlobalValidator.assertThat(tabs).thatIsNamed(PluralLowerCaseCatalogue.TABS).isNotNull();
		
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
		return tabs.getRefValues();
	}
	
	//method
	/**
	 * The number of tabs of a {@link Accordion} does not need to equal
	 * the number of widgets of a {@link Accordion} because the tabs of a {@link Accordion} can be empty.
	 * 
	 * @return the number of tabs of the current {@link Accordion}.
	 */
	public int getTabCount() {
		return getRefTabs().getElementCount();
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
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		
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
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
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
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final IPainter painter, final AccordionLook accordionLook) {
		//Does nothing.
	}
	
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
	protected void resetBorderWidgetConfiguration() {
		getRefTabHeaderLook().reset();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetContainerWidget() {
		
		mainVerticalStack.reset();
		getRefActiveLook().addChild(getRefTabHeaderLook());		
		getRefTabHeaderLook()
		.setTextColorForState(WidgetLookState.BASE, Color.GREY)
		.setTextColorForState(WidgetLookState.HOVER, Color.BLACK);
		
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
	}
	
	//method
	private void assertCanCollapseAllTabs() {
		if (!canCollapseAllTabs()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot collapse all tabs");
		}
	}
	
	//method
	private void assertCanExpandAllTabs() {
		if (!canExpandAllTabs()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot expand all tabs");
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
