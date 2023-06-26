//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.controlhelper.ValidationLabelHelper;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.controlapi.IButton;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.controlhelperapi.IValidationLabelHelper;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Button extends Control<Button, ButtonStyle> implements IButton<Button, ButtonStyle> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//constant
	private static final IValidationLabelHelper VALIDATION_LABEL_HELPER = new ValidationLabelHelper();
	
	//attribute
	private final MutableOptionalValue<ButtonRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		ButtonRole::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);
	
	//optional attribute
	private IElementTaker<IButton<?, ?>> leftMouseButtonPressAction;
	
	//optional attribute
	private IElementTaker<IButton<?, ?>> leftMouseButtonReleaseAction;
	
	//constructor
	public Button() {
		
		//Info: Reset is technically optional, but required to achieve a custom state on reset.
		reset();
		
		getOriStyle()
		.setBackgroundColorForState(ControlState.BASE, Color.AQUAMARINE)
		.setBackgroundColorForState(ControlState.HOVER, Color.MEDIUM_AQUA_MARINE)
		.setBackgroundColorForState(ControlState.FOCUS, Color.MEDIUM_AQUA_MARINE);
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getOriChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public ButtonRole getRole() {
		return role.getValue();
	}
	
	//method
	@Override
	public String getText() {
		return text.getValue();
	}
	
	//method
	@Override
	public String getUserInput() {
		return getText();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return (hasRole() && getRole().toString().equals(role));
	}
		
	//method
	@Override
	public boolean hasRole() {
		return role.hasValue();
	}
	
	//method
	@Override
	public void pressLeftMouseButton() {
		if (hasLeftMouseButtonPressAction()) {
			VALIDATION_LABEL_HELPER.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
				this,
				leftMouseButtonPressAction
			);
		}
	}

	//method
	@Override
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		list.addAtEnd(
			HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmousedown"),
			HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmouseup")
		);
	}
	
	//method
	@Override
	public void releaseLeftMouseButton() {
		if (hasLeftMouseButtonReleaseAction()) {
			VALIDATION_LABEL_HELPER.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
				this,
				leftMouseButtonReleaseAction
			);
		}
	}
	
	//method
	@Override
	public void removeLeftMouseButtonPressAction() {
		leftMouseButtonPressAction = null;
	}
	
	//method
	@Override
	public void removeLeftMouseButtonReleaseAction() {
		leftMouseButtonReleaseAction = null;
	}
	
	//method
	@Override
	public void removeRole() {
		role.clear();
	}
	
	//method
	@Override
	public void runHtmlEvent(final String htmlEvent) {
		switch (htmlEvent) {
			case "onmousedown" ->
				pressLeftMouseButton();
			case "onmouseup" ->
				releaseLeftMouseButton();
			default ->
				throw InvalidArgumentException.forArgumentNameAndArgument("HTML event", htmlEvent);
		}
	}
	
	//method
	@Override
	public Button setLeftMouseButtonPressAction(final IAction leftMouseButtonPressAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonPressAction)
		.thatIsNamed("left mouse button press action")
		.isNotNull();
		
		return setLeftMouseButtonPressAction(b -> leftMouseButtonPressAction.run());
	}
	
	//method
	@Override
	public Button setLeftMouseButtonPressAction(final IElementTaker<IButton<?, ?>> leftMouseButtonPressAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonPressAction)
		.thatIsNamed("left mouse button press action")
		.isNotNull();
		
		this.leftMouseButtonPressAction = leftMouseButtonPressAction;
		
		return this;
	}
	
	//method
	@Override
	public Button setLeftMouseButtonRelaseAction(final IAction leftMouseButtonReleaseAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonReleaseAction)
		.thatIsNamed("left mouse button release action")
		.isNotNull();
		
		return setLeftMouseButtonRelaseAction(b -> leftMouseButtonReleaseAction.run());
	}
	
	//method
	@Override
	public Button setLeftMouseButtonRelaseAction(final IElementTaker<IButton<?, ?>> leftMouseButtonReleaseAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonReleaseAction)
		.thatIsNamed("left mouse button release action")
		.isNotNull();
		
		this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;
		
		return this;
	}
	
	//method
	@Override
	public Button setRole(final ButtonRole role) {
		
		this.role.setValue(role);
		
		return this;
	}
	
	//method
	@Override
	public Button setText(final String text) {
		
		this.text.setValue(text);
		
		return this;
	}
	
	//method
	@Override
	public Button setUserInput(final String userInput) {
		return setText(userInput);
	}
	
	//method
	@Override
	protected ButtonStyle createStyle() {
		return new ButtonStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<Button, ButtonStyle> getCSSRuleCreator() {
		return ButtonCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<Button> getHtmlBuilder() {
		return ButtonHtmlBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		removeRole();
		setText(StringCatalogue.EMPTY_STRING);
		removeLeftMouseButtonPressAction();
		removeLeftMouseButtonReleaseAction();
		
		setCursorIcon(CursorIcon.HAND);
	}
	
	//method
	private boolean hasLeftMouseButtonPressAction() {
		return (leftMouseButtonPressAction != null);
	}
	
	//method
	private boolean hasLeftMouseButtonReleaseAction() {
		return (leftMouseButtonReleaseAction != null);
	}
}
