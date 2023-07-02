//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class SingleContainer
extends Container<SingleContainer, SingleContainerStyle>
implements ISingleContainer<SingleContainer, SingleContainerStyle> {
	
	//constant
	private static final String CONTROL_HEADER = "Control";
	
	//attribute
	private final MutableOptionalValue<IControl<?, ?>> control =
	new MutableOptionalValue<>(
		CONTROL_HEADER,
		this::setControl,
		GlobalControlFactory::createControlFromSpecification,
		IControl::getSpecification
	);
	
	//method
	@Override
	public void clear() {
		control.clear();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getOriChildControls() {
		
		if (isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(getOriControl());
	}
	
	//method
	@Override
	public IControl<?, ?> getOriControl() {
		return control.getValue();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return !control.hasValue();
	}
	
	//method
	@Override
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public SingleContainer setControl(final IControl<?, ?> control) {
		
		control.technicalSetParentControl(this);
		this.control.setValue(control);
		
		return this;
	}
	
	//method
	@Override
	protected SingleContainerStyle createStyle() {
		return new SingleContainerStyle();
	}
	
	//method
	@Override
	protected IControlCssRuleBuilder<SingleContainer, SingleContainerStyle> getCssRuleCreator() {
		return SingleContainerCssRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<SingleContainer> getHtmlBuilder() {
		return SingleContainerHtmlBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		clear();
	}
}
