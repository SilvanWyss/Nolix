//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.Application;

//package-visible class
final class SampleApplication extends Application<BackGUIClient> {

	//constant
	public static final String NAME = "SampleApplication";
	
	//constructor
	public SampleApplication() {
		super(NAME, BackGUIClient.class, MainSession.class);
	}
}
