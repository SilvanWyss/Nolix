//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Textbox extends Control<ITextbox, ITextboxStyle> implements ITextbox {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.EDIT;
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.EMPTY_STRING;
	
	//constant
	public static final TextMode DEFAULT_TEXT_MODE = TextMode.NORMAL;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//constant
	private static final String TEXT_MODE_HEADER = "TextMode";
	
	//attribute
	private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);
	
	//attribute
	private MutableValue<TextMode> textMode =
	new MutableValue<>(
		TEXT_MODE_HEADER,
		DEFAULT_TEXT_MODE,
		this::setTextMode,
		TextMode::fromSpecification,
		Node::fromEnum
	);
	
	//optional attribute
	private IElementTaker<String> updateTextAction;
	
	//constructor
	public Textbox() {
		getOriStyle()
		.setBorderThicknessForState(ControlState.BASE, 1)
		.setBackgroundColorForState(ControlState.BASE, Color.AQUAMARINE)
		.setBackgroundColorForState(ControlState.HOVER, Color.MEDIUM_AQUA_MARINE)
		.setBackgroundColorForState(ControlState.FOCUS, Color.MEDIUM_AQUA_MARINE);
	}
	
	//method
	@Override
	public void emptyText() {
		setText(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	public CursorIcon getDefaultCursorIcon() {
		return DEFAULT_CURSOR_ICON;
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>("return x.value;");
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getOriChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public String getText() {
		return text.getValue();
	}
	
	//method
	@Override
	public TextMode getTextMode() {
		return textMode.getValue();
	}
	
	//method
	@Override
	public String getUserInput() {
		return getText();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public void registerHTMLElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public void removeUpdateTextAction() {
		updateTextAction = null;
	}
	
	//method
	@Override
	public void runHTMLEvent(final String htmlEvent) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHTMLEvent");
	}
	
	//method
	@Override
	public Textbox setText(final String text) {
		
		this.text.setValue(text);
		
		runOptionalUpdateTextActionForText(text);
		
		return this;
	}
	
	//method
	@Override
	public Textbox setTextMode(final TextMode textMode) {
		
		this.textMode.setValue(textMode);
		
		return this;
	}
	
	//method
	@Override
	public Textbox setUpdateTextAction(final IAction updateTextAction) {
		
		GlobalValidator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();
		
		return setUpdateTextAction(t -> updateTextAction.run());
	}
	
	//method
	@Override
	public Textbox setUpdateTextAction(final IElementTaker<String> updateTextAction) {
		
		GlobalValidator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();
		
		this.updateTextAction = updateTextAction;
		
		return this;
	}
	
	//method
	@Override
	public Textbox setUserInput(final String userInput) {
		return setText(userInput);
	}
	
	//method
	@Override
	protected TextboxStyle createStyle() {
		return new TextboxStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<ITextbox, ITextboxStyle> getCSSRuleCreator() {
		return TextboxCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<ITextbox> getHTMLBuilder() {
		return TextboxHtmlBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		emptyText();
		
		setTextMode(DEFAULT_TEXT_MODE);
		
		removeUpdateTextAction();
	}
	
	//method
	private boolean hasUpdateTextAction() {
		return (updateTextAction != null);
	}
	
	//method
	private void runOptionalUpdateTextActionForText(final String text) {
		if (hasUpdateTextAction()) {
			updateTextAction.run(text);
		}
	}
}
