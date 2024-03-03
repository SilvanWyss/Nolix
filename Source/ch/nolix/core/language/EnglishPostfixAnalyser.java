//package declaration
package ch.nolix.core.language;

//class
public final class EnglishPostfixAnalyser {

  //method
  public boolean endsWithVocalAndY(final String noun) {
    return //
    noun.endsWith("ay") //NOSONAR: This method is uniform.
    || noun.endsWith("ey")
    || noun.endsWith("iy")
    || noun.endsWith("oy")
    || noun.endsWith("uy");
  }
}
