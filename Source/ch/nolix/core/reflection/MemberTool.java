//package declaration
package ch.nolix.core.reflection;

//own imports
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

//class
public final class MemberTool {

  //method
  public boolean isPrivate(final Member member) {
    return Modifier.isPrivate(member.getModifiers());
  }

  //method
  public boolean isProtected(final Member member) {
    return Modifier.isProtected(member.getModifiers());
  }

  //method
  public boolean isPublic(final Member member) {
    return Modifier.isPublic(member.getModifiers());
  }
}
