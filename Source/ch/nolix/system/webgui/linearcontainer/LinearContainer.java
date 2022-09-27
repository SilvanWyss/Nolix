//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.webgui.container.Container;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public abstract class LinearContainer
extends Container<LinearContainer, LinearContainerStyle>
implements ILinearContainer<LinearContainer, LinearContainerStyle> {
	
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
	
	//attribute
	private final LinearContainerCSSRuleCreator mCSSRuleCreator =
	LinearContainerCSSRuleCreator.forLinearContainer(this);
	
	//method
	@Override
	public final LinearContainer addControl(final IControl<?, ?>... controls) {
		
		for (final var c : controls) {
			c.technicalSetParentControl(this);
			this.childControls.add(c);
		}
		
		return this;
	}
	
	//method
	@Override
	public final LinearContainer addControls(final IContainer<? extends IControl<?, ?>> controls) {
		
		controls.forEach(this::addControl);
		
		return this;
	}
	
	//method
	@Override
	public final void clear() {
		childControls.clear();
	}
	
	//method
	@Override
	public IControlCSSRuleCreator<LinearContainer, LinearContainerStyle> getCSSRuleCreator() {
		return mCSSRuleCreator;
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
	public final void removeControl(final IControl<?, ?> control) {
		childControls.remove(control);
	}
	
	//method
	@Override
	public final void technicalUpdateMediaRegistrationsAtGUI(final IWebGUI<?> pGUI) {
		//Does nothing.
	}
	
	//method
	@Override
	protected final LinearContainerStyle createStyle() {
		return new LinearContainerStyle();
	}
}
