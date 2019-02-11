//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//abstract class
public abstract class DatabaseApplicationSession
extends BackGUIClientSession {
	
	//attribute
	private DatabaseAdapter databaseAdapter;
	
	//method
	@Override
	public final void initialize() {
		
		getRefGUI()
		.setTitle(((DatabaseApplicationContext)getRefApplicationContext()).getTitle())
		.setRootWidget(
			new VerticalStack(
				createTitleWidget(),
				createContentWidget()
			)
			.setRole(ContainerRole.OverallContainer)
		);
		
		if (((DatabaseApplicationContext)getRefApplicationContext()).hasGUILook()) {
			getRefGUI().setConfiguration(
				((DatabaseApplicationContext)getRefApplicationContext()).getGUILook()
			);
		}
	}
	
	//abstract method
	protected abstract Widget<?, ?> createContentWidget();
	
	//method
	protected final DatabaseAdapter getRefDatabaseAdapter() {
		
		if (!hasDatabaseAdapter()) {
			databaseAdapter = 
			((DatabaseApplicationContext)getRefApplicationContext()).createDatabaseAdapter();
		}
		
		return databaseAdapter;
	}
	
	//method
	private Label createTitleWidget() {
		return
		new Label()
		.setText(((DatabaseApplicationContext)getRefApplicationContext()).getTitle())
		.setRole(LabelRole.Title);
	}
	
	//method
	private boolean hasDatabaseAdapter() {
		return (databaseAdapter != null);
	}
}
