//package 
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.programcontrolapi.processproperty.OnOffState;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.HTMLElementEvent;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlapi.IToggleButton;
import ch.nolix.systemapi.webguiapi.controlapi.IToggleButtonStyle;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class ToggleButton extends Control<IToggleButton, IToggleButtonStyle> implements IToggleButton {
	
	//constant
	public static final OnOffState DEFAULT_ON_OFF_STATE = OnOffState.OFF;
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.HAND;
	
	//attribute
	private final MutableValue<OnOffState> onOffState =
	new MutableValue<>(
		ToggleButtonConstantCatalogue.ON_OFF_STATE_HEADER,
		DEFAULT_ON_OFF_STATE,
		this::setOnOffState,
		s -> OnOffState.valueOf(s.getSingleChildNodeHeader()),
		Node::fromEnum
	);
	
	//optional attribute
	private IAction offAction;
	
	//optional attribute
	private IAction onAction;
	
	//method
	@Override
	public CursorIcon getDefaultCursorIcon() {
		return DEFAULT_CURSOR_ICON;
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>("return x.checked;");
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getOriChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public String getUserInput() {
		
		if (isOff()) {
			return ToggleButtonConstantCatalogue.OFF;
		}
		
		return ToggleButtonConstantCatalogue.ON;
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isOff() {
		return !isOn();
	}
	
	//method
	@Override
	public boolean isOn() {
		return (getOnOffState() == OnOffState.ON);
	}
	
	//method
	@Override
	public void registerHTMLElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		
		//Registers a HTML event, that will be triggered after the HTML toggle button, at the HTML container element.
		list.addAtEnd(HTMLElementEvent.withHTMLElementIdAndHtmlEvent(getInternalId(), "onmouseup"));
	}
	
	//method
	@Override
	public void removeOffAction() {
		offAction = null;
	}
	
	//method
	@Override
	public void removeOnAction() {
		onAction = null;
	}
	
	//method
	@Override
	public void runHTMLEvent(final String htmlEvent) {
		if (!htmlEvent.equals("onmouseup")) {
			throw InvalidArgumentException.forArgumentNameAndArgument("HTML event", htmlEvent);
		}
	}
	
	//method
	@Override
	public IToggleButton setOff() {
		
		if (isOn()) {
			setOffWhenIsOn();
		}
		
		return this;
	}
	
	//method
	@Override
	public IToggleButton setOffAction(final IAction offAction) {
		
		GlobalValidator.assertThat(offAction).thatIsNamed("off action").isNotNull();
		
		this.offAction = offAction;
		
		return this;
	}
	
	//method
	@Override
	public IToggleButton setOn() {
		
		if (isOff()) {
			setOnWhenIsOff();
		}
		
		return this;
	}
	
	//method
	@Override
	public IToggleButton setOnAction(final IAction onAction) {
		
		GlobalValidator.assertThat(onAction).thatIsNamed("on action").isNotNull();
		
		this.onAction = onAction;
		
		return this;
	}
	
	//method
	@Override
	public IToggleButton setUserInput(final String userInput) {
		
		switch (userInput) {
			case ToggleButtonConstantCatalogue.OFF ->
				setOff();
			case ToggleButtonConstantCatalogue.ON ->
				setOn();
			default ->
				throw InvalidArgumentException.forArgumentNameAndArgument("user input", userInput);
		}
		
		return this;
	}
	
	//method
	@Override
	protected IToggleButtonStyle createStyle() {
		return new ToggleButtonStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<IToggleButton, IToggleButtonStyle> getCSSRuleCreator() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<IToggleButton> getHTMLBuilder() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		//The actions must be removed before setOff because setOff would run a probable set-off-action.
		removeOffAction();
		removeOnAction();
		
		setOff();
	}
	
	//method
	private OnOffState getOnOffState() {
		return onOffState.getValue();
	}
	
	//method
	private boolean hasOffAction() {
		return (offAction != null);
	}
	
	//method
	private boolean hasOnAction() {
		return (onAction != null);
	}
	
	//method
	private void setOffWhenIsOn() {
		if (hasOffAction()) {
			offAction.run();
		}
	}
	
	//method
	private void setOnOffState(final OnOffState onOffState) {
		this.onOffState.setValue(onOffState);
	}
	
	//method
	private void setOnWhenIsOff() {
		if (hasOnAction()) {
			onAction.run();
		}
	}
}
