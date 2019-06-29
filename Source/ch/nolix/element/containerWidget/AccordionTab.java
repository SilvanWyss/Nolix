//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.core.attributeAPI.Headerable;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.Element;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.CursorIcon;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.element.widget.Widget;

//class
public final class AccordionTab extends Element<AccordionTab>
implements Clearable<AccordionTab>, Headerable<AccordionTab>, Specifiable<AccordionTab> {
	
	//default value
	public static final String DEFAULT_HEADER = StringCatalogue.DEFAULT_STRING;
	
	//constant
	private static final String EXPANDED_FLAG_HEADER = "Expanded";
	
	//method
	/**
	 * @param specification
	 * @return a new {@link AccordionTab} from the given specification.
	 */
	public static AccordionTab createFromSpecification(final DocumentNodeoid specification) {
		
		final var tab = new AccordionTab();
		tab.reset(specification);
		
		return tab;
	}
	
	//attribute
	private final MutableProperty<String> header =
	new MutableProperty<String>(
		PascalCaseNameCatalogue.HEADER,
		h -> setHeader(h),
		s -> s.getOneAttributeAsString(),
		h -> new DocumentNode(PascalCaseNameCatalogue.HEADER, getHeader())
	);
	
	//attribute
	private final MutableProperty<Boolean> expanded =
	new MutableProperty<Boolean>(
		EXPANDED_FLAG_HEADER,
		e -> {
			if (e.isTrue()) {
				expand();
			}
			else {
				collapse();
			}
		},
		s -> Boolean.createFromSpecification(s),
		e -> e.getSpecification()
	);
	
	//attributes
	private VerticalStack tabVerticalStack = new VerticalStack();
	private final HorizontalStack headerHorizontalStack = new HorizontalStack();
	private final Label headerLabel = new Label();
	private final Button expandButton = new Button(" v ").setLeftMouseButtonPressCommand(() -> noteExpandButtonPress());
	
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
				expandButton,
				headerLabel
			)
		);
		
		expandButton.resetConfiguration();

		
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
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		if (GUI.canCreateWidgetOf(attribute.getHeader())) {
			setWidget(GUI.createWidget(attribute));
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
	public AccordionTab clear() {
		
		if (containsAny()) {
			tabVerticalStack.removeWidget(widget);
			widget = null;
		}
		
		return this;
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
	public List<DocumentNode> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (containsAny()) {
			attributes.addAtEnd(getRefWidget().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	@Override
	public final String getHeader() {
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
		return expanded.getValue().isTrue();
	}
	
	//method
	public boolean isUnderCursor() {
		return tabVerticalStack.isUnderCursor();
	}
	
	//method
	@Override
	public AccordionTab reset() {
		
		setHeader(DEFAULT_HEADER);
		collapse();
		clear();
		
		return this;
	}
	
	//method
	@Override
	public AccordionTab setHeader(final String header) {
		
		Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
		this.header.setValue(header);
		
		return asConcreteType();
	}
	
	//method
	public AccordionTab setWidget(final Widget<?, ?> widget) {
		
		Validator.suppose(widget).isOfType(Widget.class);
		
		this.widget = widget;
		tabVerticalStack.addWidget(widget);
		
		return this;
	}
	
	//package-visible method
	HorizontalStack getRefHeaderHorizontalStack() {
		return headerHorizontalStack;
	}
	
	//package-visible method
	VerticalStack getRefTabVerticalStack() {
		return tabVerticalStack;
	}
	
	//package-visible method
	int getRequiredMinWidth() {
		
		int requiredMinWidth = headerLabel.getWidth() + expandButton.getWidth();
		
		if (containsAny()) {
			requiredMinWidth = Calculator.getMax(requiredMinWidth, getRefWidget().getWidth());
		}
		
		return requiredMinWidth;
	}
	
	//package-visible method
	void preparePaint(final AccordionLook accordionLook) {
		
		headerLabel.setText(getHeader());
		
		headerHorizontalStack.resetConfiguration();
		
		final var contentAreaWidth = getParentAccordion().getContentAreaWidth();
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
		
		expandButton.setCustomCursorIcon(CursorIcon.Hand);
		expandButton.getRefBaseLook().setTextColor(Color.GREY);
		expandButton.getRefHoverLook().setTextColor(Color.BLACK);
		expandButton.getRefFocusLook().setTextColor(Color.BLACK);
		
		if (containsAny()) {
			if (isCollapsed()) {
				getRefWidget().setCollapsed();
			}
			else {
				getRefWidget().setNormal();
			}
		}
		
		tabVerticalStack.recalculate();
	}
	
	//package-visible method
	void setParentCursorPosition(
		final int cursorXPositionOnContent,
		final int cursorYPositionOnContent
	) {
		tabVerticalStack.setParentCursorPosition(
			cursorXPositionOnContent,
			cursorYPositionOnContent
		);
	}
	
	//package-visible method
	void setParentAccordion(final Accordion parentAccordion) {
		
		Validator
		.suppose(parentAccordion)
		.thatIsNamed("parent accordion")
		.isNotNull();
		
		this.parentAccordion = parentAccordion;
	}
	
	//package-visible method
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
