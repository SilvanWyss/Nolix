//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.bases.HeaderableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.primitive.invalidStateException.EmptyStateException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
public final class AccordionTab
extends HeaderableElement<AccordionTab>
implements Clearable<AccordionTab> {
	
	//default value
	public static final String DEFAULT_HEADER = StringCatalogue.DEFAULT_STRING;
	
	//constant
	private static final String EXPANDED_FLAG_HEADER = "Expanded";
	
	//method
	/**
	 * @param specification
	 * @return a new {@link AccordionTab} from the given specification.
	 */
	public static AccordionTab createFromSpecification(final Specification specification) {
		
		final var tab = new AccordionTab(DEFAULT_HEADER);
		tab.reset(specification);
		
		return tab;
	}
	
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
		s -> Boolean.createFromSpecification(s)
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
	public AccordionTab(final String header) {
		
		tabVerticalStack.reset();
		
		tabVerticalStack.addWidget(
			headerHorizontalStack.addWidget(
				headerLabel,
				expandButton
			)
		);
		
		expandButton.resetConfiguration();

		
		reset();
		setHeader(header);
		approveProperties();
	}
	
	//constructor
	public AccordionTab(final String header, final Widget<?, ?> widget) {
		this(header);
		setWidget(widget);
	}
	
	//method
	public void addOrChangeAttribute(final Specification attribute) {
		
		if (GUI.canCreateWidget(attribute.getHeader())) {
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
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (containsAny()) {
			attributes.addAtEnd(getRefWidget().getSpecification());
		}
		
		return attributes;
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
	public AccordionTab reset() {
		
		setHeader(DEFAULT_HEADER);
		collapse();
		clear();
		
		return this;
	}
	
	//method
	public AccordionTab setWidget(final Widget<?, ?> widget) {
		
		Validator.suppose(widget).isInstanceOf(Widget.class);
		
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
	void preparePaint(final AccordionLook borderWidgetLook) {
			
		headerLabel.setText(getHeader());
		
		headerHorizontalStack.resetConfiguration();
		
		final var contentAreaWidth = getParentAccordion().getContentAreaWidth();
		if (contentAreaWidth > 0) {
			headerHorizontalStack.setProposalWidth(contentAreaWidth);
		}
		
		if (borderWidgetLook.hasRecursiveTabHeaderBackgroundColor()) {
			headerHorizontalStack
			.getRefBaseLook()
			.setBackgroundColor(borderWidgetLook.getRecursiveOrDefaultTabHeaderBackgroundColor());
		}
		
		if (borderWidgetLook.hasRecursiveTabHeaderTextSize()) {
			for (final var w : headerHorizontalStack.getRefWidgets()) {
				w
				.getRefBaseLook()
				.setTextSize(borderWidgetLook.getRecursiveOrDefaultTabHeaderTextSize());
			}
		}
		
		if (borderWidgetLook.hasRecursiveTabHeaderTextColor()) {
			for (final var w : headerHorizontalStack.getRefWidgets()) {
				w
				.getRefBaseLook()
				.setTextColor(borderWidgetLook.getRecursiveOrDefaultTabHeaderTextColor());
			}
		}
				
		if (borderWidgetLook.hasRecursiveTabBackgroundColor()) {
			headerHorizontalStack
			.getRefBaseLook()
			.setBackgroundColor(borderWidgetLook.getRecursiveOrDefaultTabBackgroundColor());
		}
		
		expandButton.setCustomCursorIcon(CursorIcon.Hand);
		
		expandButton
		.getRefBaseLook()
		.setPaddings(2)
		.setTextColor(Color.GREY);
		
		expandButton
		.getRefHoverLook()
		.setBorderThicknesses(2)
		.setBorderColors(Color.GREY)
		.setPaddings(0);
		
		expandButton
		.getRefHoverFocusLook()
		.setBorderThicknesses(2)
		.setBorderColors(Color.GREY)
		.setPaddings(0);
		
		if (containsAny()) {
			if (isCollapsed()) {
				getRefWidget().setCollapsed();
			}
			else {
				getRefWidget().setNormal();
			}
		}
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
		.isInstance();
		
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
			new InvalidStateException(
				this,
				"does not belong to an accordion"
			);
		}
	}
	
	//method
	private void supposeIsNotEmpty() {
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
	}
}
