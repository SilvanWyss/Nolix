//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.containerapi.IContainer;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//class
public abstract class Container<
	C extends IContainer<C, ECS>,
	ECS extends IExtendedControlStyle<ECS>
>
extends Control<C, ECS> implements IContainer<C, ECS> {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//attribute
	private final MutableOptionalValue<ContainerRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		ContainerRole::fromSpecification,
		Node::fromEnum
	);
	
	//method
	@Override
	public final CursorIcon getDefaultCursorIcon() {
		return DEFAULT_CURSOR_ICON;
	}
	
	//method
	@Override
	public final ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>();
	}
	
	//method
	@Override
	public final ContainerRole getRole() {
		return role.getValue();
	}
	
	//method
	@Override
	public final String getUserInput() {
		return StringCatalogue.EMPTY_STRING;
	}
	
	//method
	@Override
	public final boolean hasRole() {
		return role.hasValue();
	}
	
	//method
	@Override
	public final boolean hasRole(final String role) {
		return (hasRole() && getRole().toString().equals(role));
	}
	
	//method
	@Override
	public final void removeRole() {
		role.clear();
	}
	
	//method
	@Override
	public final void runHTMLEvent(final String htmlEvent) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHTMLEvent");
	}
	
	//method
	@Override
	public final C setRole(final ContainerRole role) {
		
		this.role.setValue(role);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final C setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isEmpty();
		
		return asConcrete();
	}
	
	//method declaration
	protected abstract void resetContainer();
	
	//method
	@Override
	protected final void resetControl() {
		
		removeRole();
		clear();
		
		resetContainer();
	}
}
