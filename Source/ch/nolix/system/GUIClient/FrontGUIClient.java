//package declaration
package ch.nolix.system.GUIClient;

import ch.nolix.core.constants.IPv6Catalogue;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;
import ch.nolix.system.GUIClientoid.FrontGUIClientoid;
import ch.nolix.system.GUIClientoid.FrontGUIClientoidType;
import ch.nolix.system.client.Application;

//class
/**
 * A {@link FrontGUIClient} provides a {@link GUI}
 * and is controlled by a {@link BackGUIClient}.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 160
 */
public final class FrontGUIClient extends FrontGUIClientoid<FrontGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient}
	 * that will connect to the given application.
	 * 
	 * @param application
	 */
	public FrontGUIClient(Application<BackGUIClient> application) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} with the given GUI
	 * that will connect to the given application.
	 * 
	 * @param GUI_
	 * @param application
	 * @throws NullArgumentExcepiton if the given GUI is null.
	 */
	public FrontGUIClient(
		final GUI<?> GUI_,
		final Application<BackGUIClient> application
	) {
		
		//Calls other constructor.
		this(GUI_);
		
		internal_connectTo(application);
	}
	
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontGUIClient(final int port) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the application with the given name
	 * on given port on the local machine
	 * 
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(
		final int port,
		final String name
	) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(port, name);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the default application
	 * on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(
		final String ip,
		final int port
	) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the application with the given name
	 * on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(
		final String ip,
		final int port,
		final String name
	) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(ip, port, name);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} with the given GUI
	 * 
	 * @param GUI_
	 * @throws NullArgumentExcepiton if the given GUI is null.
	 */
	private FrontGUIClient(final GUI<?> GUI_) {
		
		super(GUI_);
		
		
	}

	//method
	@Override
	public FrontGUIClientoidType getFrontEndType() {
		return FrontGUIClientoidType.GUISpecificationConsumer;
	}
}
