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
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlapi.ILabel;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Label extends Control<Label, LabelStyle> implements ILabel<Label, LabelStyle> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//constant
	private static final LabelHtmlBuilder HTML_BUILDER = new LabelHtmlBuilder();
	
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
	
	//constructor
	public Label() {
		
		//Info: Reset is technically optional, but required to achieve a custom state on reset.
		reset();
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
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public void removeRole() {
		role.clear();
	}
	
	//method
	@Override
	public void runHtmlEvent(final String htmlEvent) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
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
	protected IControlCssRuleBuilder<Label, LabelStyle> getCssRuleCreator() {
		return LabelCssRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<Label> getHtmlBuilder() {
		return HTML_BUILDER;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		removeRole();
		setText(DEFAULT_TEXT);
		
		setCursorIcon(CursorIcon.EDIT);
	}
}
