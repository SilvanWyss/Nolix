//package declaration
package ch.nolix.core.programatom.marker;

//Java imports
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//annotation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AllowDefaultMethodsAsDesignPattern {}
