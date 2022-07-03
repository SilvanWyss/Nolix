//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.formatelement.NonCascadingProperty;

//class
public final class CheckBoxLook extends BorderWidgetLook<CheckBoxLook> {
	
	//constant
	public static final int DEFAULT_LINE_THICKNESS = 1;
	
	//constant
	private static final String DEFAULT_LINE_THICKNESS_HEADER = "LineThickness";
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> lineThickness =
	new NonCascadingProperty<>(
		DEFAULT_LINE_THICKNESS_HEADER,
		WidgetLookState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
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
		
		GlobalValidator.assertThat(lineThickness).thatIsNamed(LowerCaseCatalogue.LINE_THICKNESS).isNotNegative();
		
		this.lineThickness.setValueForState(state, lineThickness);
		
		return asConcrete();
	}
}
