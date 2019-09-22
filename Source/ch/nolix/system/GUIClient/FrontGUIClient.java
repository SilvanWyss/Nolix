//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.element.GUI.GUI;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClient;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClientGUIType;
import ch.nolix.system.client.Application;

//class
/**
 * A {@link FrontGUIClient} provides a {@link GUI} and is controlled by a {@link BackGUIClient}.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 100
 */
public final class FrontGUIClient extends BaseFrontGUIClient<FrontGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the given application.
	 * 
	 * @param application
	 */
	public FrontGUIClient(Application<BackGUIClient> application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(application);
		noteResizeOnCounterpart();
	}
	
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontGUIClient(final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(port);
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the application with the given name on given port on the local machine
	 * 
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final int port, final String name) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(port, name);
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the default application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(ip, port);
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the application with the given name on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final String ip, final int port, final String name) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(ip, port, name);
		noteResizeOnCounterpart();
	}
}
