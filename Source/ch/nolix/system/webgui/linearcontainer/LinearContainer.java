//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.webgui.container.Container;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;

//class
public abstract class LinearContainer<
	LC extends LinearContainer<LC, LCS>,
	LCS extends LinearContainerStyle<LCS>
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
	public final LC addControl(final IControl<?, ?>... controls) {
		
		for (final var c : controls) {
			c.technicalSetParentControl(this);
			this.childControls.add(c);
		}
		
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
	public final IContainer<IControl<?, ?>> getRefChildControls() {
		return childControls.getRefValues();
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return childControls.isEmpty();
	}
	
	//method
	@Override
	public final void registerHTMLElementEventsAt(final IMutableList<IHTMLElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void removeControl(final IControl<?, ?> control) {
		childControls.remove(control);
	}
}
