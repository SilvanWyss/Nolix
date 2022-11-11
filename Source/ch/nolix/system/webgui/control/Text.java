//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.controlapi.IText;
import ch.nolix.systemapi.webguiapi.controlapi.TextRole;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;

//class
public final class Text extends Control<Text, TextStyle> implements IText<Text, TextStyle> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//attribute
	private final MutableOptionalValue<TextRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		TextRole::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final MutableValue<String> value = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getRefChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public TextRole getRole() {
		return role.getValue();
	}
	
	//method
	@Override
	public String getText() {
		return value.getValue();
	}
	
	//method
	@Override
	public String getUserInput() {
		return StringCatalogue.EMPTY_STRING;
	}
	
	//method
	@Override
	public boolean hasRole() {
		return role.hasValue();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return (hasRole() && getRole().toString().equals(role));
	}
		
	//method
	@Override
	public void registerHTMLElementEventsAt(final IMutableList<IHTMLElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public void removeRole() {
		role.clear();
	}
	
	//method
	@Override
	public void runHTMLEvent(final String pHTMLEvent) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHTMLEvent");
	}
	
	//method
	@Override
	public Text setRole(final TextRole role) {
		
		this.role.setValue(role);
		
		return this;
	}
	
	//method
	@Override
	public Text setText(final String text) {
		
		GlobalValidator.assertThat(text).thatIsNamed(LowerCaseCatalogue.TEXT).isNotBlank();
		
		this.value.setValue(text);
		
		return this;
	}
	
	//method
	@Override
	public Text setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();
		
		return this;
	}
	
	//method
	@Override
	protected TextStyle createStyle() {
		return new TextStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<Text, TextStyle> getCSSRuleCreator() {
		return TextCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHTMLBuilder<Text> getHTMLBuilder() {
		return TextHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetControl() {
		removeRole();
		setText(DEFAULT_TEXT);
	}
}
