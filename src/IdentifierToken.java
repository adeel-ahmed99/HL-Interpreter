/**
 * Describes IDENTIFER tokens
 */

public class IdentifierToken extends Token {

  public String value;
  
  public String toString()
  {
  	return value.toString();
  }

  public Object getValue()
  {
	return value;
  }
 
  public IdentifierToken(String image)
  {
    this.kind = HLConstants.IDENTIFIER  ;
    this.image = image;
   	value = image;
  }

}
