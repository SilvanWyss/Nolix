//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.Property;
import ch.nolix.element.gui.widget.Widget;

//class
public final class MutableOptionalWidgetProperty extends Property implements Clearable {
	
	//attribute
	private final IElementTaker<Widget<?, ?>> setterMethod;
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//constructor
	public MutableOptionalWidgetProperty(final IElementTaker<Widget<?, ?>> setterMethod) {
		
		Validator.assertThat(setterMethod).thatIsNamed("setter method")	.isNotNull();
		
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
		
		Validator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		
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
			throw new ArgumentDoesNotHaveAttributeException(this, Widget.class);
		}
	}
}
