//package declaration
package ch.nolix.core.classProvider;

//class
public final class ClassProvider {
	
	//static attribute
	private static final CoreClassProvider coreClassProvider =
	new CoreClassProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> interface_) {
		return coreClassProvider.containsClassFor(interface_);
	}
	
	//static method
	//Important: The ClassProvider will found only the first (!) constructor with the given amount of parameters.
	public static <I, C extends I> C create(
		final Class<I> interface_,
		final Object... arguments
	) {
		return coreClassProvider.create(interface_, arguments);
	}
	
	//static method
	public static <I, C extends I> void register(
		final Class<I> interface_,
		final Class<C> class_
	) {
		coreClassProvider.register(interface_, class_);
	}
	
	//static method
	public static <I, C extends I> void register(
		final Class<I> interface_,
		final Class<C> class_, boolean overwrite
	) {
		coreClassProvider.register(interface_, class_);
	}
	
	//private constructor
	private ClassProvider() {}
}
