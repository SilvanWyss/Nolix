//package declaration
package ch.nolix.application.nelix;

//own imports
import ch.nolix.common.application.ContextSession;
import ch.nolix.element.dialog.Button;
import ch.nolix.element.dialog.ContainerRole;
import ch.nolix.element.dialog.Label;
import ch.nolix.element.dialog.LabelRole;
import ch.nolix.element.dialog.TabContainer;
import ch.nolix.element.dialog.TabContainerTab;
import ch.nolix.element.dialog.VerticalStack;
import ch.nolix.system.dialogClient.DialogClient;
import ch.nolix.system.modules.DesignManager;

//class
/**
 * A main session is the principal session for a user to control a nelix throug a GUI.
 * A main session is a context session whose context is a nelix.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 140
 */
public final class MainSession extends ContextSession<DialogClient, Nelix> {
	
	//constructor
	/**
	 * Creates new main session with the given nelix.
	 * 
	 * @param nelix
	 * @throws NullArgumentException if the given nelix is null.
	 */
	public MainSession(final Nelix nelix) {
		
		//Calls constructor of the base class.
		super(nelix);
	}

	//method
	/**
	 * Initializes this main session.
	 */
	public void initialize() {
		
		//Creates home vertical stack.
		final VerticalStack homeVerticalStack = new VerticalStack();
		if (getRefClient().isLocalClient()) {
			homeVerticalStack.addRectangle(
				new Button()
				.setText("Open Directory")
				.setLeftMouseButtonPressCommand("OpenDirectory")
			);
		}
		
		//Creates design vertical stack.
		final VerticalStack designVerticalStack = new VerticalStack();
		
		//Fetches the design manager.
		final DesignManager designManager = getRefContext().getRefModuleByCodeName("DesignManager");
		
		//Creates design buttons.
		for (String dn: designManager.getDesignNames()) {
			designVerticalStack.addRectangle(
				new Button()
				.setText(dn)
				.setLeftMouseButtonPressCommand("SetDesign", dn)
			);
		}
		
		//Setups the dialog of the client of this main session.
		getRefClient().getRefDialog()
		.setTitle("Nelix 2016-12-27")
		.setRootRectangle(
			new VerticalStack()
			.setRole(ContainerRole.OverallContainer)
			.addRectangle(
				new Label()
				.setRole(LabelRole.Title)
				.setText("Nelix"),
				new TabContainer()
				.setRole(ContainerRole.MainContainer)
				.addTab(
					new TabContainerTab()
					.setName("Home")
					.setRectangle(
						homeVerticalStack
					),
					new TabContainerTab()
					.setName("Design")
					.setRectangle(designVerticalStack)
				)
			)
		);
		
		//Tries to set the configuration of the dialog of the client of this main session.
		try {
			getRefClient().getRefDialog().setConfiguration(designManager.getDesignByName(getRefContext().getDesign()));
		}
		catch (Exception e) { e.printStackTrace();}
	}
	
	//method
	/**
	 * Opens the directory in the file explorer.
	 */
	public void OpenDirectory() {
		getRefContext().run(Nelix.OPEN_DIRECTORY_COMMAND);
	}
	
	//method
	/**
	 * Quits the nelix of this main session.
	 */
	public void Quit() {
		getRefContext().run(Nelix.QUIT_COMMAND);
	}
	
	//method
	/**
	 * Sets the given design to the nelix of this main session.
	 * 
	 * @param design
	 * @throws UnexistingAttributeException if the design manager of the nelix of this main session does not contain the given design.
	 */
	public void SetDesign(final String design) {
		
		getRefContext().setDesign(design);
		
		//Fetches the design manager.
		final DesignManager designManager = getRefContext().getRefModuleByCodeName("DesignManager");
		
		getRefClient().getRefDialog().setConfiguration(designManager.getDesignByName(design));
	}
}
