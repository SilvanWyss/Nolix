//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.common.constant.MultiVariableNameCatalogue;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widget.VerticalStack;

//class
/**
 * @author Silvan Wyss
 * @date 2018-08-13
 * @lines 420
 */
public final class Accordion extends ContainerWidget<Accordion, AccordionLook> implements Clearable<Accordion> {
	
	//constants
	public static final AccordionExpansionBehavior DEFAULT_EXPANSION_BEHAVIOR = AccordionExpansionBehavior.Single;
	public static final Color DEFAULT_TAB_HEADER_BACKGROUND_COLOR =	Color.LIGHT_GREY;
	
	//attribute
	private final MutableValue<AccordionExpansionBehavior> expansionBehavior =
	new MutableValue<>(
		AccordionExpansionBehavior.TYPE_NAME,
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
	private final VerticalStack accordionVerticalStack = new VerticalStack().reset();
	
	//constructor
	/**
	 * Creates a new {@link Accordion}.
	 */
	public Accordion() {
		resetAndApplyDefaultConfiguration();
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
	 * @throws NullArgumetnException if one of the given tabs is null.
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
	 * @throws NullArgumetnException if one of the given tabs is null.
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
	public Accordion clear() {
		
		tabs.clear();
		accordionVerticalStack.clear();
		
		return this;
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
			case Single:
			case Multi:
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return getRefTabs().isEmpty();
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Accordion reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
		clear();
		
		return this;
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
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		setExpansionBehavior(DEFAULT_EXPANSION_BEHAVIOR);
		getRefBaseLook().setTabHeaderBackgroundColor(DEFAULT_TAB_HEADER_BACKGROUND_COLOR);
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
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(accordionVerticalStack);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		return accordionVerticalStack.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
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
	protected void recalculateSelfStage2() {
		getRefTabs().forEach(t -> t.preparePaint(getRefLook()));
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
			case SingleOrNone:
			case Single:
				getRefTabs().forEach(AccordionTab::collapse);
				break;
			default:
		}
		
		tab.expand();
	}
}
