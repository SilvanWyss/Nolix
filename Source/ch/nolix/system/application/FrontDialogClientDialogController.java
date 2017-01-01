package ch.nolix.system.application;

import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.specification.Statement;

//package-visible class
class FrontDialogClientDialogController implements ILevel1Controller {
	
	private final FrontDialogClient dialogFrontEndClient;
	
	public FrontDialogClientDialogController(FrontDialogClient dialogFrontEndClient) {
		this.dialogFrontEndClient = dialogFrontEndClient;
	}

	public void run(Statement command) {
		dialogFrontEndClient.invokeOnOriginMachine(command.toString());
	}
}
