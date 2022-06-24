//package declaration
package ch.nolix.system.gui.main;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.system.element.Property;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.WidgetGUI;

//class
public final class MutableOptionalWidgetProperty extends Property implements Clearable {
	
	//attribute
	private final IElementTaker<Widget<?, ?>> setterMethod;
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//constructor
	public MutableOptionalWidgetProperty(final IElementTaker<Widget<?, ?>> setterMethod) {
		
		GlobalValidator.assertThat(setterMethod).thatIsNamed("setter method")	.isNotNull();
		
		this.setterMethod = setterMethod;
	}
	
	//method
	@Override
	public void clear() {
		widget = null;
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		
		assertHasWidget();
		
		return widget;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	public void setWidget(final Widget<?, ?> widget) {
		
		GlobalValidator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		
		this.widget = widget;
	}
	
	//method
	@Override
	protected boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (!WidgetGUI.canCreateWidgetFrom(attribute)) {
			return false;
		}
		
		setterMethod.run(WidgetGUI.createWidgetFrom(attribute));
		return true;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(LinkedList<Node> list) {
		if (containsAny()) {
			list.addAtEnd(getRefWidget().getSpecification());
		}
	}
	
	//method
	private void assertHasWidget() {
		if (isEmpty()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, Widget.class);
		}
	}
}
