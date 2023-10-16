//package declaration
package ch.nolix.core.provider.serviceprovider;

//own imports
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
public final class GlobalServiceProvider {

  //constant
  private static final ServiceProvider SERVICE_PROVIDER = new ServiceProvider();

  //static method
  public static <S> S get(final Class<S> pInterface) {
    return SERVICE_PROVIDER.get(pInterface);
  }

  //static method
  public static <I, S extends I> void register(final Class<I> pInterface, final S service) {
    SERVICE_PROVIDER.register(pInterface, service);
  }

  //static method
  public static <I, S extends I> void register(
      final Class<I> pInterface,
      final S service,
      final WriteMode writeMode) {
    SERVICE_PROVIDER.register(pInterface, service, writeMode);
  }

  //constructor
  private GlobalServiceProvider() {
  }
}
