//package declaration
package ch.nolix.system.webgui.container;

import ch.nolix.core.commontype.constant.StringCatalogue;
//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.webguiapi.containerapi.IContainer;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//class
public abstract class Container<
	C extends Container<C, ECS>,
	ECS extends IExtendedControlStyle<ECS>
>
extends Control<C, ECS> implements IContainer<C, ECS> {
	
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
	public final void noteKeyPress(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteKeyRelease(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteKeyTyping(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteMouseWheelPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteMouseWheelRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteRightMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteRightMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void removeRole() {
		role.clear();
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
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();
		
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
