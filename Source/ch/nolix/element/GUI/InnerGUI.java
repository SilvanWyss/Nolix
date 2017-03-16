/*
 * file:	InnerDialog.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.common.util.Validator;

//class
public final class InnerGUI extends GUI<InnerGUI> {

	//constant
	public static final String SIMPLE_CLASS_NAME = "InnerDialog";
	
	//attribute
	private final FrameContext frameContext;
	
	//constructor
	/**
	 * Creates new inner dialog with the given frame context.
	 * 
	 * @param frameContext
	 * @throws Exception if the given frame context is null
	 */
	public InnerGUI(FrameContext frameContext) {
		
		Validator.throwExceptionIfValueIsNull("frame context", frameContext);
		
		this.frameContext = frameContext;
	}
	
	//method
	/**
	 * @return the frame context of this inner dialog
	 */
	public final FrameContext getRefFrameContext() {
		return frameContext;
	}

	
	public void updateFromInteraction() {
		// TODO Auto-generated method stub
		
	}
}
