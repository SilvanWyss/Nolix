//package declaration
package ch.nolix.core.reflection;

//own imports
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

//class
public final class GlobalMemberHelper {

  //constructor
  private GlobalMemberHelper() {
  }

  //static method
  public static boolean isPrivate(final Member member) {
    return Modifier.isPrivate(member.getModifiers());
  }

  //static method
  public static boolean isProtected(final Member member) {
    return Modifier.isProtected(member.getModifiers());
  }

  //static method
  public static boolean isPublic(final Member member) {
    return Modifier.isPublic(member.getModifiers());
  }
}
