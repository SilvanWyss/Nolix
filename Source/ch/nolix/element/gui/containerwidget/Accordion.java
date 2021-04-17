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
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.base.SubElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-08-13
 * @lines 510
 */
public final class Accordion extends ContainerWidget<Accordion, AccordionLook> {
	
	//constant
	public static final AccordionExpansionBehavior DEFAULT_EXPANSION_BEHAVIOR = AccordionExpansionBehavior.OPEN_ONE_TAB;
	
	//constants
	private static final String EXPANSION_BEHAVIOUR_HEADER = "ExpansionBehavior";
	private static final String TAB_HEADER_LOOK_HEADER = "TabHeaderLook";
	
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
		PascalCaseCatalogue.TAB,
		this::addTab,
		AccordionTab::fromSpecification,
		AccordionTab::getSpecification
	);
	
	//attributes
	private final VerticalStack accordionVerticalStack = new VerticalStack();
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
		accordionVerticalStack.addWidget(tab.getRefTabVerticalStack());
		
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
		return addTab(new AccordionTab(header, widget));
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
		accordionVerticalStack.clear();
	}
	
	//method
	/**
	 * Collapses all tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion collapseAllTabs() {
		
		assertCanCollapseAllTabs();
		
		getRefTabs().forEach(AccordionTab::collapse);
		
		return this;
	}
	
	//method
	/**
	 * Expands all tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion expandAllTabs() {
		
		assertCanExpandAllTabs();
				
		getRefTabs().forEach(AccordionTab::expand);
		
		return this;
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
		list.addAtEnd(accordionVerticalStack);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		return accordionVerticalStack.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		return accordionVerticalStack.getWidth();
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
	protected void paintContentArea(final AccordionLook accordionLook, final IPainter painter) {}
	
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
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
	}
	
	//method
	/**
	 * Lets the current {@link Accordion} collapse the given tab if
	 * the current {@link Accordion} can collapse at least 1 tab.
	 * 
	 * @param tab
	 */
	void collapseIfPossible(final AccordionTab tab) {
		if (canCollapseAnExpandedTab()) {
			tab.collapse();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Accordion} expand the given tab.
	 * 
	 * @param tab
	 */
	void expand(final AccordionTab tab) {
		if (!tab.isExpanded()) {
			expandWhenNotExpanded(tab);
		}
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
		return !mustExpandAtLeastOneTabWhenNotEmpty() || getRefTabs().getCount(AccordionTab::isExpanded) > 1;
	}
	
	//method
	private boolean canExpandAllTabs() {
		return (getTabCount() < 2 || !mustExpandAtMostOneTab());
	}
	
	//method
	private void expandWhenNotExpanded(final AccordionTab tab) {
		
		if (mustExpandAtMostOneTab()) {
			collapseAllTabs();
		}
						
		tab.expand();
	}
}
