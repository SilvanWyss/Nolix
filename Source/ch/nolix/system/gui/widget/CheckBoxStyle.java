//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateelement.NonCascadingProperty;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class CheckBoxStyle extends BorderWidgetStyle<CheckBoxStyle> {
	
	//constant
	public static final int DEFAULT_LINE_THICKNESS = 1;
	
	//constant
	private static final String DEFAULT_LINE_THICKNESS_HEADER = "LineThickness";
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> lineThickness =
	new NonCascadingProperty<>(
		DEFAULT_LINE_THICKNESS_HEADER,
		ControlState.class,
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
	public CheckBoxStyle setLineThicknessForState(final ControlState state, final int lineThickness) {
		
		GlobalValidator.assertThat(lineThickness).thatIsNamed(LowerCaseCatalogue.LINE_THICKNESS).isNotNegative();
		
		this.lineThickness.setValueForState(state, lineThickness);
		
		return asConcrete();
	}
}
