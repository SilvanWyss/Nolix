//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Headerable;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.MutableElement;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.MutableOptionalWidgetProperty;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.widget.Label;

//class
public final class AccordionTab extends MutableElement<AccordionTab>
implements Clearable, Headerable<AccordionTab>, IMutableElement<AccordionTab> {
	
	//constants
	public static final String DEFAULT_HEADER = StringCatalogue.DEFAULT_STRING;
	public static final boolean DEFAULT_EXPANSION_FLAG = false;
	
	//constants
	private static final String HEADER_HEADER = PascalCaseCatalogue.HEADER;
	private static final String EXPANDED_FLAG_HEADER = "Expanded";
	
	//static method
	public static AccordionTab fromSpecification(final BaseNode specification) {
		
		final var tab = new AccordionTab();
		tab.resetFrom(specification);
		
		return tab;
	}
	
	//attribute
	private final MutableValue<String> header =
	new MutableValue<>(
		HEADER_HEADER,
		DEFAULT_HEADER,
		this::setHeader,
		BaseNode::getOneAttributeHeader,
		h -> Node.withHeaderAndAttribute(PascalCaseCatalogue.HEADER, getHeader())
	);
	
	//attribute
	private final MutableValue<Boolean> expansionFlag =
	new MutableValue<>(
		EXPANDED_FLAG_HEADER,
		DEFAULT_EXPANSION_FLAG,
		this::setExpansionFlag,
		BaseNode::getOneAttributeAsBoolean,
		Node::withAttribute
	);
	
	//attributes
	private VerticalStack mainVerticalStack = new VerticalStack();
	private final HorizontalStack headerHorizontalStack = new HorizontalStack();
	private final Label headerLabel = new Label();
	private final MutableOptionalWidgetProperty widget = new MutableOptionalWidgetProperty(this::setWidget);
	private final SingleContainer widgetContainer = new SingleContainer();
	
	//optional attribute
	private Accordion parentAccordion;
	
	//constructor
	public AccordionTab() {
		reset();
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		internalAddOrChangeAttribute(attribute);
	}
	
	//method
	public boolean belongsToAccordion() {
		return (parentAccordion != null);
	}
	
	//method
	@Override
	public void clear() {
		
		assertDoesNotBelongToAccordion();
		
		if (containsAny()) {
			clearWhenNotEmpty();
		}
	}
	
	//method
	@Override
	public String getHeader() {
		return header.getValue();
	}
	
	//method
	public Accordion getParentAccordion() {
		
		assertBelongsToAccordion();
		
		return parentAccordion;
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		return widget.getRefWidget();
	}
	
	//method
	public boolean isCollapsed() {
		return !isExpanded();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return widget.isEmpty();
	}
	
	//method
	public boolean isExpanded() {
		return expansionFlag.getValue();
	}
	
	//method
	@Override
	public void reset() {
		
		mainVerticalStack.reset();
		headerHorizontalStack.reset();
		headerLabel.reset();
		widgetContainer.reset();
		
		mainVerticalStack.addWidget(headerHorizontalStack, widgetContainer);
		headerHorizontalStack.addWidget(headerLabel);
		
		headerLabel
		.setCustomCursorIcon(CursorIcon.HAND)
		.setLeftMouseButtonPressAction(this::noteHeaderLabelLeftMouseButtonPress);
		
		setHeader(DEFAULT_HEADER);
		clear();
		collapse();
	}
	
	//method
	@Override
	public AccordionTab setHeader(final String header) {
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		assertDoesNotBelongToAccordion();
		
		this.header.setValue(header);
		headerLabel.setText(header);
		
		return asConcrete();
	}
	
	//method
	public AccordionTab setWidget(final Widget<?, ?> widget) {
		
		assertDoesNotBelongToAccordion();
		
		this.widget.setWidget(widget);
		widgetContainer.setWidget(widget);
		
		return this;
	}
	
	//method
	void collapse() {
		expansionFlag.setValue(Boolean.FALSE);
		widgetContainer.setCollapsed();
	}

	//method
	void expand() {
		expansionFlag.setValue(Boolean.TRUE);
		widgetContainer.setExpanded();
	}
	
	//method
	VerticalStack getRefMainVerticalStack() {
		return mainVerticalStack;
	}
		
	//method
	void recalculate() {
		
		headerHorizontalStack.getRefLook().setFrom(parentAccordion.getRefTabHeaderLook());
		
		final var naturalContentAreaWidth = getParentAccordion().getNaturalContentAreaWidth();
		if (naturalContentAreaWidth > 0) {
			headerHorizontalStack.setMinWidth(naturalContentAreaWidth);
		}
			
		mainVerticalStack.recalculate();
	}
	
	//method
	void setParentAccordion(final Accordion parentAccordion) {
		
		Validator.assertThat(parentAccordion).thatIsNamed("parent accordion").isNotNull();
		
		this.parentAccordion = parentAccordion;
	}
	
	//method
	private void assertBelongsToAccordion() {
		if (!belongsToAccordion()) {
			throw new ArgumentDoesNotBelongToParentException(this, Accordion.class);
		}
	}
	
	//method
	private void assertDoesNotBelongToAccordion() {
		if (belongsToAccordion()) {
			throw new ArgumentBelongsToParentException(this, getParentAccordion());
		}
	}
	
	//method
	private void clearWhenNotEmpty() {
		mainVerticalStack.removeWidget(getRefWidget());
		widget.clear();
	}
	
	//method
	private void noteHeaderLabelLeftMouseButtonPress() {
		
		//Handles the case that the current AccordionTab is collapsed.
		if (isCollapsed()) {
			getParentAccordion().expandTab(this);
			
		//Handles the case that the current AccordionTab is expanded.
		} else {
			getParentAccordion().collapseTab(this);
		}
	}
	
	//method
	private void setExpansionFlag(final boolean expanded) {
		if (!expanded) {
			collapse();
		} else {
			expand();
		}
	}
}
