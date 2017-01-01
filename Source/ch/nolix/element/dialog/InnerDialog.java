/*
 * file:	InnerDialog.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.element.dialog;

//own import
import ch.nolix.common.util.Validator;

//class
public final class InnerDialog extends Dialog<InnerDialog> {

	//constant
	public final static String SIMPLE_CLASS_NAME = "InnerDialog";
	
	//attribute
	private final FrameContext frameContext;
	
	//constructor
	/**
	 * Creates new inner dialog with the given frame context.
	 * 
	 * @param frameContext
	 * @throws Exception if the given frame context is null
	 */
	public InnerDialog(FrameContext frameContext) {
		
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
