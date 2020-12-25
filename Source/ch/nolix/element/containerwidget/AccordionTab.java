//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.mutableattributeapi.Headerable;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.gui.CursorIcon;
import ch.nolix.element.gui.WidgetGUI;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.widget.Label;

//class
public final class AccordionTab extends Element<AccordionTab>
implements Clearable, Headerable<AccordionTab>, IMutableElement<AccordionTab> {
	
	//constant
	public static final String DEFAULT_HEADER = StringCatalogue.DEFAULT_STRING;
	public static final boolean DEFAULT_EXPANSION_STATE = true;
	
	//constant
	private static final String EXPANDED_FLAG_HEADER = "Expanded";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link AccordionTab} from the given specification.
	 */
	public static AccordionTab fromSpecification(final BaseNode specification) {
		
		final var tab = new AccordionTab();
		tab.reset(specification);
		
		return tab;
	}
	
	//attribute
	private final MutableValue<String> header =
	new MutableValue<>(
		PascalCaseNameCatalogue.HEADER,
		DEFAULT_HEADER,
		this::setHeader,
		BaseNode::getOneAttributeHeader,
		h -> Node.withHeaderAndAttribute(PascalCaseNameCatalogue.HEADER, getHeader())
	);
	
	//attribute
	private final MutableValue<Boolean> expanded =
	new MutableValue<>(
		EXPANDED_FLAG_HEADER,
		DEFAULT_EXPANSION_STATE,
		e -> {
			if (e.booleanValue()) {
				expand();
			}
			else {
				collapse();
			}
		},
		BaseNode::getOneAttributeAsBoolean,
		Node::withAttribute
	);
	
	//attributes
	private VerticalStack tabVerticalStack = new VerticalStack();
	private final HorizontalStack headerHorizontalStack = new HorizontalStack();
	private final Label headerLabel = new Label().setLeftMouseButtonPressAction(this::noteExpandButtonPress);
	
	//optional attributes
	private Accordion parentAccordion;
	private Widget<?, ?> widget;
	
	//constructor
	public AccordionTab() {
		this(DEFAULT_HEADER);
	}
	
	//constructor
	public AccordionTab(final String header) {
		
		tabVerticalStack.reset();
		
		tabVerticalStack.addWidget(
			headerHorizontalStack.addWidget(
				headerLabel
			)
		);
		
		reset();
		setHeader(header);
	}
	
	//constructor
	public AccordionTab(final String header, final Widget<?, ?> widget) {
		
		this(header);
		
		setWidget(widget);
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			setWidget(WidgetGUI.createWidgetFrom(attribute));
		}
		
		else {
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	public boolean belongsToAccordion() {
		return (parentAccordion != null);
	}
	
	//method
	@Override
	public void clear() {
		if (containsAny()) {
			tabVerticalStack.removeWidget(widget);
			widget = null;
		}
	}
	
	//method
	public AccordionTab collapse() {
		
		expanded.setValue(Boolean.FALSE);
		
		return this;
	}
	
	//method
	public AccordionTab expand() {
		
		expanded.setValue(Boolean.TRUE);
		
		return this;
	}
	
	//method
	public CursorIcon getActiveCursorIcon() {
		return tabVerticalStack.getCursorIcon();
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (containsAny()) {
			attributes.addAtEnd(getRefWidget().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	@Override
	public String getHeader() {
		return header.getValue();
	}
	
	//method
	public int getHeaderHeight() {
		return headerHorizontalStack.getHeight();
	}
	
	//method
	public Accordion getParentAccordion() {
		
		supposeBelongsToAccordion();
		
		return parentAccordion;
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		
		supposeIsNotEmpty();
		
		return widget;
	}
	
	//method
	public boolean isCollapsed() {
		return !isExpanded();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	public boolean isExpanded() {
		return expanded.getValue();
	}
	
	//method
	public boolean isUnderCursor() {
		return tabVerticalStack.isUnderCursor();
	}
	
	//method
	@Override
	public void reset() {
		setHeader(DEFAULT_HEADER);
		collapse();
		clear();
	}
	
	//method
	@Override
	public AccordionTab setHeader(final String header) {
		
		Validator.assertThat(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
		this.header.setValue(header);
		
		return asConcrete();
	}
	
	//method
	public AccordionTab setWidget(final Widget<?, ?> widget) {
		
		Validator.assertThat(widget).isOfType(Widget.class);
		
		this.widget = widget;
		tabVerticalStack.addWidget(widget);
		
		return this;
	}
	
	//method
	HorizontalStack getRefHeaderHorizontalStack() {
		return headerHorizontalStack;
	}
	
	//method
	VerticalStack getRefTabVerticalStack() {
		return tabVerticalStack;
	}
	
	//method
	int getRequiredMinWidth() {
		
		int requiredMinWidth = headerLabel.getWidth();
		
		if (containsAny()) {
			requiredMinWidth = Calculator.getMax(requiredMinWidth, getRefWidget().getWidth());
		}
		
		return requiredMinWidth;
	}
	
	//method
	void preparePaint(final AccordionLook accordionLook) {
		
		headerLabel.setText(getHeader());
		
		headerHorizontalStack.resetConfiguration();
		headerHorizontalStack.applyOnBaseLook(bl -> bl.setLeftPadding(10));
		
		final var contentAreaWidth = getParentAccordion().getNaturalContentAreaWidth();
		if (contentAreaWidth > 0) {
			headerHorizontalStack.setProposalWidth(contentAreaWidth);
		}
		
		if (accordionLook.hasRecursiveTabHeaderBackgroundColor()) {
			headerHorizontalStack
			.getRefBaseLook()
			.setBackgroundColor(accordionLook.getRecursiveOrDefaultTabHeaderBackgroundColor());
		}
		
		if (accordionLook.hasRecursiveTabHeaderTextSize()) {
			for (final var w : headerHorizontalStack.getChildWidgets()) {
				w
				.getRefBaseLook()
				.setTextSize(accordionLook.getRecursiveOrDefaultTabHeaderTextSize());
			}
		}
		
		if (accordionLook.hasRecursiveTabHeaderTextColor()) {
			for (final var w : headerHorizontalStack.getChildWidgets()) {
				w
				.getRefBaseLook()
				.setTextColor(accordionLook.getRecursiveOrDefaultTabHeaderTextColor());
			}
		}
				
		if (accordionLook.hasRecursiveTabBackgroundColor()) {
			headerHorizontalStack
			.getRefBaseLook()
			.setBackgroundColor(accordionLook.getRecursiveOrDefaultTabBackgroundColor());
		}
		
		headerLabel.setCustomCursorIcon(CursorIcon.HAND);
		headerLabel.getRefBaseLook().setTextColor(Color.GREY);
		headerLabel.getRefHoverLook().setTextColor(Color.BLACK);
		headerLabel.getRefFocusLook().setTextColor(Color.BLACK);
		
		if (containsAny()) {
			if (isCollapsed()) {
				getRefWidget().setCollapsed();
			}
			else {
				getRefWidget().setExpanded();
			}
		}
		
		tabVerticalStack.recalculate();
	}
	
	//method
	void setParentAccordion(final Accordion parentAccordion) {
		
		Validator
		.assertThat(parentAccordion)
		.thatIsNamed("parent accordion")
		.isNotNull();
		
		this.parentAccordion = parentAccordion;
	}
	
	//method
	void setPositionOnParent(
		final int yPositionOnParent
	) {
		tabVerticalStack.setPositionOnParent(0, yPositionOnParent);
		
		
	}
	
	//method
	private void noteExpandButtonPress() {
		
		//Handles the case that the current accordion tab is collapsed.
		if (isCollapsed()) {
			getParentAccordion().expand(this);
		}
		
		//Handles the case that the curent accordion tab is expanded.
		else {
			getParentAccordion().collapse(this);
		}
	}
	
	//method
	private void supposeBelongsToAccordion() {
		if (!belongsToAccordion()) {
			throw
			new InvalidArgumentException(
				this,
				"does not belong to an accordion"
			);
		}
	}
	
	//method
	private void supposeIsNotEmpty() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
