//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.webgui.basecontainer.Container;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IControlGetter;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainer;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public abstract class LinearContainer<
	LC extends ILinearContainer<LC, LCS>,
	LCS extends ILinearContainerStyle<LCS>
>
extends Container<LC, LCS>
implements ILinearContainer<LC, LCS> {
	
	//constant
	private static final String CHILD_CONTROL_HEADER = "ChildControl";
	
	//attribute
	private final MultiValue<IControl<?, ?>> childControls =
	new MultiValue<>(
		CHILD_CONTROL_HEADER,
		this::addControl,
		GlobalControlFactory::createControlFromSpecification,
		IControl::getSpecification
	);
	
	//method
	@Override
	public final LC addControl(IControl<?, ?> control, final IControl<?, ?>... controls) {
		
		final var allControls = ReadContainer.forElement(control, controls);
		
		return addControls(allControls);
	}
	
	//method
	@Override
	public final LC addComponent(final IControlGetter component, final IControlGetter... components) {
		
		final var allComponents = ReadContainer.forElement(component, components);
		
		return addComponents(allComponents);
	}
	
	//method
	@Override
	public final LC addComponents(IContainer<? extends IControlGetter> components) {
		
		components.forEach(this::addComponent);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final LC addControls(final IContainer<? extends IControl<?, ?>> controls) {
		
		controls.forEach(this::addControl);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final void clear() {
		childControls.clear();
	}
	
	//method
	@Override
	public final IContainer<IControl<?, ?>> getStoredChildControls() {
		return childControls.getStoredValues();
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return childControls.isEmpty();
	}
	
	//method
	@Override
	public final void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void removeControl(final IControl<?, ?> control) {
		childControls.remove(control);
	}
	
	//method
	private void addComponent(final IControlGetter component) {
		
		final var control = component.getStoredControl();
		
		addControl(control);
	}
	
	//method
	private void addControl(final IControl<?, ?> control) {
		
		control.technicalSetParentControl(this);
		
		this.childControls.add(control);
	}
}
