//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.constant.MultiVariableNameCatalogue;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2018-08-13
 * @lines 450
 */
public final class Accordion extends ContainerWidget<Accordion, AccordionLook> {
	
	//constants
	public static final AccordionExpansionBehavior DEFAULT_EXPANSION_BEHAVIOR = AccordionExpansionBehavior.SINGLE;
	public static final Color DEFAULT_TAB_HEADER_BACKGROUND_COLOR =	Color.LIGHT_GREY;
	
	//attribute
	private final MutableValue<AccordionExpansionBehavior> expansionBehavior =
	new MutableValue<>(
		AccordionExpansionBehavior.TYPE_NAME,
		DEFAULT_EXPANSION_BEHAVIOR,
		this::setExpansionBehavior,
		AccordionExpansionBehavior::fromSpecification,
		AccordionExpansionBehavior::getSpecification
	);
	
	//attribute
	private final MultiValue<AccordionTab> tabs =
	new MultiValue<>(
		PascalCaseNameCatalogue.TAB,
		this::addTab,
		AccordionTab::fromSpecification,
		AccordionTab::getSpecification
	);
	
	//attribute
	private final VerticalStack accordionVerticalStack = new VerticalStack();
	
	//constructor
	/**
	 * Creates a new {@link Accordion}.
	 */
	public Accordion() {
		
		addChildWidget(accordionVerticalStack);
		accordionVerticalStack.reset();
		
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
		getRefBaseLook().setTabHeaderBackgroundColor(DEFAULT_TAB_HEADER_BACKGROUND_COLOR);
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
		
		//Asserts that the given tab is not null.
		Validator
		.assertThat(tab)
		.thatIsNamed(VariableNameCatalogue.TAB)
		.isNotNull();
		
		tab.setParentAccordion(this);
		tabs.add(tab);
		accordionVerticalStack.addWidget(tab.getRefTabVerticalStack());
		
		if (expandsAtLeastOneTabWhenNotEmpty() && getTabCount() < 2) {
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
		
		//Asserts that the given tabs is not null.
		Validator.assertThat(tabs).thatIsNamed(MultiVariableNameCatalogue.TABS).isNotNull();
		
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
		
		//Asserts that the given tabs is not null.
		Validator.assertThat(tabs).thatIsNamed(MultiVariableNameCatalogue.TABS).isNotNull();
		
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
	 * Collapses the tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion collapse() {
		
		getRefTabs().forEach(AccordionTab::collapse);
		
		return this;
	}
	
	//method
	/**
	 * Expands the tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion expand() {
		
		getRefTabs().forEach(AccordionTab::expand);
		
		return this;
	}
	
	//method
	/**
	 * @return true if the current {@link Accordion} expands at least one tab when it is not empty.
	 */
	public boolean expandsAtLeastOneTabWhenNotEmpty() {
		
		//Enumerates the expansion behavior of the current accordion.
		switch (getExpansionBehavior()) {
			case SINGLE:
			case MULTI:
				return true;
			default:
				return false;
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
	 * @return the tabs of the current {@link Accordion}.
	 */
	public IContainer<AccordionTab> getRefTabs() {
		return tabs;
	}
	
	//method
	/**
	 * The number of tabs of an {@link Accordion}
	 * does not need to equal the number of widgets of a {@link Accordion},
	 * because an {@link Accordion} can contain tabs that are empty.
	 * 
	 * @return the number of tabs of the current {@link Accordion}.
	 */
	public int getTabCount() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return tabs.getElementCount();
	}
	
	//method
	/**
	 * Sets the expansion behavior of the current {@link Accordion}.
	 * 
	 * @param expansionBehavior
	 * @return the current {@link Accordion}.
	 * @throws ArgumentIsNullException if the given expansion behavior is null.
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
	protected void fillUpContainerWidgetAttributesInto(final LinkedList<Node> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
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
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
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
		getRefTabs().forEach(t -> t.preparePaint(getRefLook()));
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
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
		clear();
	}
	
	//method
	/**
	 * Lets the current {@link Accordion} collapse the given tab.
	 * 
	 * @param tab
	 */
	void collapse(final AccordionTab tab) {
		if (!expandsAtLeastOneTabWhenNotEmpty()	|| getRefTabs().getCount(AccordionTab::isExpanded) > 1) {
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
		
		//Enumerates the expansion behavior of the current accordion.
		switch (getExpansionBehavior()) {
			case SINGLE_OR_NONE:
			case SINGLE:
				getRefTabs().forEach(AccordionTab::collapse);
				break;
			default:
		}
		
		tab.expand();
	}
}
