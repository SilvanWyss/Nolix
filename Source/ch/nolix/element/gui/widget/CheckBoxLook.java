//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.formatelement.NonCascadingProperty;
import ch.nolix.element.gui.base.WidgetLook;
import ch.nolix.element.gui.base.WidgetLookState;

//class
public final class CheckBoxLook extends WidgetLook<CheckBoxLook> {
	
	//constant
	public static final int DEFAULT_LINE_THICKNESS = 1;
	
	//constant
	private static final String DEFAULT_LINE_THICKNESS_HEADER = "LineThickness";
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> lineThickness =
	new NonCascadingProperty<>(
		DEFAULT_LINE_THICKNESS_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setLineThicknessForState,
		DEFAULT_LINE_THICKNESS
	);
	
	//method
	public int getLineThickness() {
		return lineThickness.getValue();
	}
	
	//method
	public void removeLineThicknesses() {
		lineThickness.setUndefined();
	}
	
	//method
	public CheckBoxLook setLineThicknessForState(final WidgetLookState state, final int lineThickness) {
		
		Validator.assertThat(lineThickness).thatIsNamed(LowerCaseCatalogue.LINE_THICKNESS).isNotNegative();
		
		this.lineThickness.setValueForState(state, lineThickness);
		
		return asConcrete();
	}
}
