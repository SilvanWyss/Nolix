//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.controlapi.ILabel;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;

//class
public final class Label extends Control<Label, LabelStyle> implements ILabel<Label, LabelStyle> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//attribute
	private final MutableOptionalValue<LabelRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		LabelRole::fromSpecification,
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
	public LabelRole getRole() {
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
	public void registerHTMLElementEventsAt(final ILinkedList<IHTMLElementEvent> list) {
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
	public Label setRole(final LabelRole role) {
		
		this.role.setValue(role);
		
		return this;
	}
	
	//method
	@Override
	public Label setText(final String text) {
		
		GlobalValidator.assertThat(text).thatIsNamed(LowerCaseCatalogue.TEXT).isNotBlank();
		
		this.value.setValue(text);
		
		return this;
	}
	
	//method
	@Override
	public Label setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();
		
		return this;
	}
	
	//method
	@Override
	protected LabelStyle createStyle() {
		return new LabelStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<Label, LabelStyle> getCSSRuleCreator() {
		return LabelCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHTMLBuilder<Label> getHTMLBuilder() {
		return LabelHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetControl() {
		removeRole();
		setText(DEFAULT_TEXT);
	}
}
