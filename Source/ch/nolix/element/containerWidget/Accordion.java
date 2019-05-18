//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.core.constants.MultiVariableNameCatalogue;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.entity.MultiProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.element.widget.Widget;

//class
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 370
 */
public final class Accordion extends ContainerWidget<Accordion, AccordionLook> implements Clearable<Accordion> {
	
	//default value
	public static final AccordionExpansionBehavior DEFAULT_EXPANSION_BEHAVIOR =
	AccordionExpansionBehavior.Single;
	
	//default value
	public static final Color DEFAULT_TAB_HEADER_BACKGROUND_COLOR =
	Color.LIGHT_GREY;
	
	//attribute
	private final MutableProperty<AccordionExpansionBehavior> expansionBehavior =
	new MutableProperty<AccordionExpansionBehavior>(
		AccordionExpansionBehavior.TYPE_NAME,
		eb -> setExpansionBehavior(eb),
		s -> AccordionExpansionBehavior.createFromSpecification(s),
		eb -> eb.getSpecification()
	);
	
	//attribute
	private final MultiProperty<AccordionTab> tabs =
	new MultiProperty<AccordionTab>(
		PascalCaseNameCatalogue.TAB,
		t -> addTab(t),
		s -> AccordionTab.createFromSpecification(s),
		t -> t.getSpecification()
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
	 * Creates a new {@link Accordion} with the given tabs.
	 * 
	 * @param tabs
	 * @throws NullArgumentException if the given tabs is null.
	 * @throws NullArgumetnException if one of the given tabs is null.
	 */
	public Accordion(final AccordionTab... tabs) {
		
		resetAndApplyDefaultConfiguration();
		
		addTab(tabs);
	}
	
	//method
	/**
	 * Adds the given tab to the current {@link Accordion}.
	 * 
	 * @param tab
	 * @return the current {@link Accordion}.
	 * @throws NullArgumentException if the given tab is null.
	 */
	public Accordion addTab(final AccordionTab tab) {
		
		//Checks if the given tab is not null.
		Validator
		.suppose(tab)
		.thatIsNamed(VariableNameCatalogue.TAB)
		.isNotNull();
		
		tab.setParentAccordion(this);
		tabs.addValue(tab);
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
	 * @throws NullArgumentException if the given tabs is null.
	 * @throws NullArgumetnException if one of the given tabs is null.
	 */
	public Accordion addTab(final AccordionTab... tabs) {
		
		//Checks if the given tabs is not null.
		Validator
		.suppose(tabs)
		.thatIsNamed(MultiVariableNameCatalogue.TABS)
		.isNotNull();
		
		return addTabs(new ReadContainer<AccordionTab>(tabs));
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link Accordion}.
	 * 
	 * @param tabs
	 * @return the current {@link Accordion}.
	 * @throws NullArgumentException if the given tabs is null.
	 * @throws NullArgumetnException if one of the given tabs is null.
	 */
	public Accordion addTabs(final Iterable<AccordionTab> tabs) {
		
		//Checks if the given tabs is not null.
		Validator
		.suppose(tabs)
		.thatIsNamed(MultiVariableNameCatalogue.TABS)
		.isNotNull();
		
		tabs.forEach(t -> addTab(t));
		
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
		
		getRefTabs().forEach(t -> t.collapse());
		
		return this;
	}
	
	//method
	/**
	 * Expands the tabs of the current {@link Accordion}.
	 * 
	 * @return the current {@link Accordion}.
	 */
	public Accordion expand() {
		
		getRefTabs().forEach(t -> t.expand());
		
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
	public ReadContainer<AccordionTab> getRefTabs() {
		return tabs.getRefValues();
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
		return getRefTabs().getSize();
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
	public void recalculate() {
						
		getRefTabs().forEach(t -> t.preparePaint(getRefCurrentLook()));
		
		accordionVerticalStack.recalculate();
		
		//Calls method of the base class.
		super.recalculate();
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
	 * @throws NullArgumentException if the given expansion behavior is null.
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
	protected AccordionLook createWidgetLook() {
		return new AccordionLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		list.addAtEnd(accordionVerticalStack);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {
		
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
	
	//package-visible method
	/**
	 * Lets the current {@link Accordion} collapse the given tab.
	 * 
	 * @param tab
	 */
	void collapse(final AccordionTab tab) {
		if (!expandsAtLeastOneTabWhenNotEmpty()	|| getRefTabs().getCount(t -> t.isExpanded()) > 1) {
			tab.collapse();
		}
 	}
	
	//package-visible method
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
				getRefTabs().forEach(t -> t.collapse());
				break;
			default:
		}
		
		tab.expand();
	}
}
