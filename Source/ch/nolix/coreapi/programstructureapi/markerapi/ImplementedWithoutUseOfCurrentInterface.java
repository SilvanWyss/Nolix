//package declaration
package ch.nolix.coreapi.programstructureapi.markerapi;

//own imports
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//interface
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface ImplementedWithoutUseOfCurrentInterface {
}
