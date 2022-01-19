/**
 * Describes BOOLEAN tokens
 */

public class BooleanToken extends Token {

  /**
   * The Boolean value of the token is also stored for BOOLEAN tokens
   */
  public Boolean value;
  
   /* toString() generates string directly from value */

  public String toString()
  {
  	return value.toString();
  }

  public Object getValue()
  {
	return value;
  }
 
  public BooleanToken(String image)
  {
    this.kind = HLConstants.BOOLEAN  ;
    this.image = image;
   	value = new Boolean(this.image);
  }

}
