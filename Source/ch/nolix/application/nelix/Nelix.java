//package declaration
package ch.nolix.application.nelix;

//Java imports
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;




import ch.nolix.common.application.ContextApplication;
//own imports
import ch.nolix.common.controller.ILevel2Controller;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.module.CentralController;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.WindowsEnvironmentVariablesManager;
import ch.nolix.element.dialog.DefaultDesign;
import ch.nolix.system.designs.BlackNBlueDesign;
import ch.nolix.system.designs.GreenDesign;
import ch.nolix.system.dialogClient.DialogClient;
import ch.nolix.system.dialogClient.FrontDialogClient;
import ch.nolix.system.modules.DesignManager;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 */
public final class Nelix extends CentralController implements ILevel2Controller {
	
	//constant
	public final static int PORT = 50000;
	
	//commands
	public final static String OPEN_DIRECTORY_COMMAND = "OpenDirectory";
	public final static String QUIT_COMMAND = "Quit";
	
	//constructor
	/**
	 * Creates new nelix.
	 */
	public Nelix() {
		
		//Calls constructor of the base class.
		super(System.getenv(WindowsEnvironmentVariablesManager.APP_DATA) + "/Nelix");
				
		//Creates design manager.
		new DesignManager(this)
		.addDesignIfNeeded(
			new BlackNBlueDesign(),
			new GreenDesign(),
			new DefaultDesign()
		);
		
		//Creates front dialog client.
		new FrontDialogClient(
			new ContextApplication<DialogClient, Nelix>("Nelix", this, MainSession.class)
		);
	}
	
	//method
	/**
	 * @return the design of this nelix
	 * @throws Exception if this nelix has no design
	 */
	public final String getDesign() {	
		final Configuration configuration = new Configuration();
		configuration.load(getDirectory() + "/Configuration.spec");
		return configuration.getDesign();
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests
	 * @throws Exception if the given request is not valid
	 */
	public final Object getRawData(Statement request) {
		if (!request.hasNextStatement()) {
			switch (request.toString()) {
				default:
					throw new InvalidArgumentException(
						new ArgumentName("request"),
						new Argument(request)
					);
			}
		}
		String codeName = request.getFirstPartToString();
		return getRefModuleByCodeName(codeName).getData(request.getNextStatement());
	}
	
	//method
	/**
	 * @return the referecne the given request requests
	 * @throws Exception if the given request is not valid
	 */
	public final Object getRawReference(Statement request) {
		final String codeName = request.getFirstPartToString();
		return getRefModuleByCodeName(codeName).getReference(request.getNextStatement());
	}
	
	//method
	/**
	 * Opens the directory of this nolix in the explorer.
	 */
	public final void openDirectory() {
		try {
			Desktop.getDesktop().open(new File(getDirectory()));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
			
	//method
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	public final void run(Statement command) {
		
		if (!command.hasNextStatement()) {
			switch (command.getFirstPartToString()) {
				case OPEN_DIRECTORY_COMMAND:
					openDirectory();
					return;
				case QUIT_COMMAND:
					quit();
					return;
				default:
					throw new InvalidArgumentException(
						new ArgumentName("command"),
						new Argument(command)
					);
			}
		}
		
		String codeName = command.getFirstPartToString();
		getRefModuleByCodeName(codeName).run(command.getNextStatement());
	}
	
	//method
	/**
	 * Quits this Nelix.
	 */
	public final void quit() {
		System.exit(0);
	}
	
	//method
	/**
	 * Sets the design of this Nelix.
	 * 
	 * @param design
	 */
	public final void setDesign(String design)  {
		
		final Configuration initialConfiguration = new Configuration();
		
		if (new File(getDirectory() + "/Configuration.spec").exists()) {
			initialConfiguration.load(getDirectory() + "/Configuration.spec");
		}
		
		initialConfiguration.setDesign(design);
		initialConfiguration.save(getDirectory() + "/Configuration.spec");
	}
}
