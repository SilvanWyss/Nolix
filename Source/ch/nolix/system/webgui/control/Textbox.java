//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;

//class
public final class Textbox extends Control<Textbox, TextboxStyle> implements ITextbox<Textbox, TextboxStyle> {
	
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
		editStyle(s -> s.setBorderThicknessForState(ControlState.BASE, 1));
	}
	
	//method
	@Override
	public void emptyText() {
		setText(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>("return x.value;");
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getRefChildControls() {
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
	public void registerHTMLElementEventsAt(final IMutableList<IHTMLElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public void removeUpdateTextAction() {
		updateTextAction = null;
	}
	
	//method
	@Override
	public void runHTMLEvent(final String pHTMLEvent) {
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
	protected IControlCSSRuleBuilder<Textbox, TextboxStyle> getCSSRuleCreator() {
		return TextboxCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHTMLBuilder<Textbox> getHTMLBuilder() {
		return TextboxHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		emptyText();
		
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
