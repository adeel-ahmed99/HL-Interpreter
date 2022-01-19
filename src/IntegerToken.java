/**
 * Describes INTEGER tokens
 */

public class IntegerToken extends Token {

  /**
   * The integer value of the token is also stored for NUMBER tokens
   */
  public Integer value;
  
   /* toString() generates string directly from value instead of image
   * to get rid of leading zeroes.
   */
  public String toString()
  {
  	return value.toString();
  }

  public Object getValue()
  {
	return value;
  }
 
  public IntegerToken(String image)
  {
    this.kind = HLConstants.INTEGER  ;
    this.image = image;
   	value = new Integer(this.image);
  }

}
