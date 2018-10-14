//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.MultiVariableNameCatalogue;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.entity.MutableMultiProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 470
 */
public final class Accordion
extends Container<Accordion, AccordionLook>
implements Clearable<Accordion> {
	
	//default value
	public static final AccordionExpansionBehavior DEFAULT_EXPANSION_BEHAVIOR =
	AccordionExpansionBehavior.Single;
	
	//attribute
	private final MutableProperty<AccordionExpansionBehavior> expansionBehavior =
	new MutableProperty<AccordionExpansionBehavior>(
		AccordionExpansionBehavior.TYPE_NAME,
		eb -> setExpansionBehavior(eb),
		s -> AccordionExpansionBehavior.createFromSpecification(s),
		eb -> eb.getSpecification()
	);
	
	//attribute
	private final MutableMultiProperty<AccordionTab> tabs =
	new MutableMultiProperty<AccordionTab>(
		PascalCaseNameCatalogue.TAB,
		t -> addTab(t),
		s -> AccordionTab.createFromSpecification(s),
		t -> t.getSpecification()
	);
	
	//attribute
	private final VerticalStack accordionVerticalStack = new VerticalStack(false);
	
	//constructor
	/**
	 * Creates a new {@link Accordion}.
	 */
	public Accordion() {
		
		//Calls other constructor.
		this(true);
	}
	
	//method
	/**
	 * Creates a new {@link Accordion} with the given tabs.
	 * 
	 * @param tabs
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumetnException if one of the given tabs is not an instance.
	 */
	public Accordion(final AccordionTab... tabs) {
		
		//Calls other constructor.
		this();
		
		addTab(tabs);
	}
	
	//constructor
	/**
	 * Creates a new {@link Accordion}.
	 */
	public Accordion(final boolean applyUsableConfiguration) {
		
		reset();
		approveProperties();
		
		if (applyUsableConfiguration) {
			applyUsableConfiguration();
		}
	}
	
	//method
	/**
	 * Creates a new {@link Accordion} with the given tabs.
	 * 
	 * @param tabs
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumetnException if one of the given tabs is not an instance.
	 */
	public Accordion(final boolean applyUsableConfiguration, final AccordionTab... tabs) {
		
		//Calls other constructor.
		this(applyUsableConfiguration);
		
		addTab(tabs);
	}
	
	//method
	/**
	 * Adds the given tab to the current {@link Accordion}.
	 * 
	 * @param tab
	 * @return the current {@link Accordion}.
	 * @throws NullArgumentException if the given tab is not an instance.
	 */
	public Accordion addTab(final AccordionTab tab) {
		
		//Checks if the given tab is an instance.
		Validator
		.suppose(tab)
		.thatIsNamed(VariableNameCatalogue.TAB)
		.isInstance();
		
		tab.setParentAccordion(this);
		tabs.addValue(tab);
		accordionVerticalStack.addWidget(tab.getRefTabVerticalStack());
		
		if (
			expandsAtLeastOneTabWhenNotEmpty()
			&& getTabCount() < 2
		) {
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
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumetnException if one of the given tabs is not an instance.
	 */
	public Accordion addTab(final AccordionTab... tabs) {
		
		//Checks if the given tabs is an instance.
		Validator
		.suppose(tabs)
		.thatIsNamed(MultiVariableNameCatalogue.TABS)
		.isInstance();
		
		return addTabs(new ReadContainer<AccordionTab>(tabs));
	}
	
	//method
	/**
	 * Adds the given tabs to the current {@link Accordion}.
	 * 
	 * @param tabs
	 * @return the current {@link Accordion}.
	 * @throws NullArgumentException if the given tabs is not an instance.
	 * @throws NullArgumetnException if one of the given tabs is not an instance.
	 */
	public Accordion addTabs(final Iterable<AccordionTab> tabs) {
		
		//Checks if the given tabs is an instance.
		Validator
		.suppose(tabs)
		.thatIsNamed(MultiVariableNameCatalogue.TABS)
		.isInstance();
		
		tabs.forEach(t -> addTab(t));
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
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
	 * {@inheritDoc}
	 */
	public CursorIcon getContentAreaCursorIcon() {
		return accordionVerticalStack.getCursorIcon();		
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
	public boolean isEmpty() {
		return getRefTabs().isEmpty();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		//Iterates the tabs of the current accordion.
		for (final var t : getRefTabs()) {
			t.getRefHeaderHorizontalStack().noteAnyLeftMouseButtonPressRecursively();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		//Iterates the tabs of the current accordion.
		for (final var t : getRefTabs()) {
			t.getRefHeaderHorizontalStack().noteAnyMouseMoveRecursively();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
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
	 * @throws NullArgumentException if the given expansion behavior is not an instance.
	 */
	public Accordion setExpansionBehavior(final AccordionExpansionBehavior expansionBehavior) {
		
		this.expansionBehavior.setValue(expansionBehavior);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {		
		getRefBaseLook().setTabHeaderBackgroundColor(Color.LIGHT_GREY);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected AccordionLook createWidgetLook() {
		return new AccordionLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {
		
		//Iterates the tabs of the current accordion.
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
	protected int getContentAreaHeight() {
		return accordionVerticalStack.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected int getContentAreaWidth() {
		return accordionVerticalStack.getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void noteSetParent() {
		accordionVerticalStack.setParentWidget(this);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void paintContentArea(
		final AccordionLook borderWidgetLook,
		final IPainter painter
	) {
		
		getRefTabs().forEach(t -> t.preparePaint(borderWidgetLook));
		
		accordionVerticalStack.paint(painter);
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	protected void setCursorPositionOnContentArea(
		final int cursorXPositionOnContent,
		final int cursorYPositionOnContent
	) {
		accordionVerticalStack.setParentCursorPosition(
			cursorXPositionOnContent,
			cursorYPositionOnContent
		);
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
		
		accordionVerticalStack.setPositionOnParent(0, 0);
	}
	
	//package-visible method
	/**
	 * Lets the current {@link Accordion} collapse the given tab.
	 * 
	 * @param tab
	 */
	void collapse(final AccordionTab tab) {
		if (
			!expandsAtLeastOneTabWhenNotEmpty()
			|| getRefTabs().getCount(t -> t.isExpanded()) > 1
		) {
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
			case MultiOrNone:
			case Multi:
				break;
		}
		
		tab.expand();
	}
}
