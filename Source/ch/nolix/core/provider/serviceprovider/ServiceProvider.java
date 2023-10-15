//package declaration
package ch.nolix.core.provider.serviceprovider;

//Java imports
import java.util.HashMap;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
public final class ServiceProvider {

  // multi-attribute
  private final HashMap<Class<?>, Object> services = new HashMap<>();

  // method
  @SuppressWarnings("unchecked")
  public <S> S get(final Class<S> pInterface) {

    final var service = (S) services.get(pInterface);

    if (service == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          this,
          "does not contain a service for the interface '"
              + pInterface.getCanonicalName()
              + "'.");
    }

    return service;
  }

  // method
  public <I, S extends I> void register(final Class<I> pInterface, final S service) {
    register(pInterface, service, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
  }

  // method
  public <I, S extends I> void register(final Class<I> pInterface, final S service, final WriteMode writeMode) {

    GlobalValidator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isNotNull();
    GlobalValidator.assertThat(service).thatIsNamed(LowerCaseCatalogue.SERVICE).isNotNull();

    switch (writeMode) {
      case THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY:

        if (services.putIfAbsent(pInterface, service) != null) {
          throw InvalidArgumentException.forArgumentAndErrorPredicate(
              this,
              "contains already a service with the given interface '"
                  + pInterface.getCanonicalName()
                  + "'");
        }

        break;
      case OVERWRITE_WHEN_TARGET_EXISTS_ALREADY:
        services.put(pInterface, service);
        break;
      case SKIP_WHEN_TARGET_EXISTS_ALREADY:
        services.putIfAbsent(pInterface, service);
        break;
    }
  }
}
