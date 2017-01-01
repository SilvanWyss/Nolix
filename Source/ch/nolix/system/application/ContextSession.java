/*
 * file:	ContextSession.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	40
 */

//package declaration
package ch.nolix.system.application;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import ch.nolix.common.duplexController.DuplexController;
//own import
import ch.nolix.common.util.Validator;

//class
/**
 * A context session is a session with a context.
 */
public abstract class ContextSession<CL extends Client<CL>, CO> extends Session<CL> {
	
	//attribute
	private final CO context;
	
	//constructor
	/**
	 * Creates new context session with the given context.
	 * 
	 * @param context
	 * @throws Exception if the given context is null
	 */
	public ContextSession(CO context) {
		
		super();
		
		Validator.throwExceptionIfValueIsNull("context", context);
		
		this.context = context;
	}
	
	//method
	/**
	 * @return the context of this context session.
	 */
	public final CO getRefContext() {
		return context;
	}
	
	@SuppressWarnings("unchecked")
	CL createClientAndSetSessionTo(DuplexController duplexController) {
		try {
			ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
			       
			String parameterClassName = pt.getActualTypeArguments()[1].toString().split("\\s")[1];
			Constructor<?> clientClassConstructor = Class.forName(parameterClassName).getDeclaredConstructors()[0];
			clientClassConstructor.setAccessible(true);

			return (CL)clientClassConstructor.newInstance(duplexController, this);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
