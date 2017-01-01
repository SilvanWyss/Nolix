/*
 * file:	Application.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	20
 */

//package declaration
package ch.nolix.system.application;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.common.basic.NamedElement;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.util.Validator;

//abstract class
public abstract class Application<C extends Client<C>> extends NamedElement {

	//attribute
	protected final Class<?> initialSessionClass;
	
	//multiple attribute
	private final List<C> clients = new List<C>();
	
	//package-visible constructor
	/**
	 * Creates new application with the given name and the given initial session class.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws Exception if:
	 * -The given name is null.
	 * -The given name is an empty string.
	 * -The given initial session class is null.
	 */
	Application(final String name, final Class<?> initialSessionClass) {
	
		//Calls constructor of the base class.
		super(name);
		
		//Checks the given initial session class.
		Validator.throwExceptionIfValueIsNull("initial session class", initialSessionClass);
		
		this.initialSessionClass = initialSessionClass;
	}
	
	Application(final String name, final int port, Class<?> initialSessionClass) {
		
		this(name, initialSessionClass);
		
		new Server(port, this);
	}
	
	//package-visible method
	/**
	 * Creates new client that:
	 * -Has a session of the initial session class of this application.
	 * -Will have the given duplex controller.
	 * 
	 * @param duplexController
	 */
	@SuppressWarnings("unchecked")
	public final void createClient(DuplexController duplexController) {
		try {
			final Session<C> initialSession = createInitialSession();	
			final String className = ((ParameterizedType)initialSession.getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString().split("\\s")[1];
			final Constructor<?> constructor = Class.forName(className).getConstructor(DuplexController.class, Session.class);	
			constructor.setAccessible(true);
			clients.addAtEnd((C)constructor.newInstance(duplexController, initialSession));
		}
		catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}
	
	public final IContainer<C> getRefClients() {
		return clients.removeAll(c -> c.isAborted());
	}
	
	//abstract method
	protected abstract Session<C> createInitialSession();
}
