//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

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
	
	//method
	@Override
	public void emptyText() {
		setText(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>("function(input) {return input.value;}");
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
	public void noteKeyPress(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyTyping(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public Textbox setText(final String text) {
		
		this.text.setValue(text);
		
		return this;
	}
	
	//method
	public Textbox setTextMode(final TextMode textMode) {
		
		this.textMode.setValue(textMode);
		
		return this;
	}
	
	//method
	@Override
	public Textbox setUserInput(final String userInput) {
		return setText(userInput);
	}
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return TextboxHTMLCreator.INSTANCE.createHTMLElementForTextbox(this);
	}
	
	//method
	@Override
	protected TextboxStyle createStyle() {
		return new TextboxStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleCreator<Textbox, TextboxStyle> getCSSRuleCreator() {
		return TextboxCSSRuleCreator.forTextbox(this);
	}
	
	@Override
	protected void resetControl() {
		emptyText();
	}
}
