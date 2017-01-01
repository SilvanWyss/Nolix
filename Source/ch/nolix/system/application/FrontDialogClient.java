/*
 * file:	FrontDialogClient.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	10
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.specification.Specification;
import ch.nolix.element.dialog.Dialog;
import ch.nolix.element.dialog.Frame;

//class
public final class FrontDialogClient extends Client<FrontDialogClient> {

	//attribute
	private Dialog<?> dialog;
	
	public FrontDialogClient(
		final String ip,
		final int port,
		final String targetApplication) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, new Frame());
	}
	
	public FrontDialogClient(Application<DialogClient> targetApplication) {
		
		//Calls constructor of the base class.
		super(new Frame(), targetApplication);
	}
	
	//method
	protected Specification createUpdateSpecification() {
		return dialog.getSpecification();
	}
	
	//initialize
	protected void initialize(final Object object) {	
		dialog = (Dialog<?>)object;
		dialog.setController(new FrontDialogClientDialogController(this));
	}
	
	//method
	protected void update(Specification updateSpecification) {
		dialog.reset(updateSpecification.getRefAttributes());
		dialog.update();
	}
}
