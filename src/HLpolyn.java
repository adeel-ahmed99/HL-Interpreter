import java.util.*;
import java.math.*;

/**
 * Implements HLpolyn objects
 * An HL polynomial is represented by two parallel linked lists of the exponents and coefficients.
 * The exponents are non-negative Integers, and the coefficients are BigIntegers.
 *      (BigIntegers are safer when evaluating polynomials, and this class has more useful features)
 * The linked list of exponents is in decreasing order (i.e. higher exponents before lower exponents)
 * e.g 5x2-23x10-2500+x would be represented by the two lists: exps=(10,2,1,0) coeffs=(-23,5,1,-2500)
 */

 /**
  * Attempted to use iterators where possible; get() and remove() calls result
  * in O(n^2) complexity on LinkedLists.
 */

public class HLpolyn {

// main method for testing, delete after:
    public static void main(String[] args) {
        System.out.println("testing function: RUNNING");
    }
//

/**
 * linked list of exponents
 */
  private LinkedList exps = new LinkedList();
/**
 * linked list of coefficients
 */
  private LinkedList coeffs = new LinkedList();
/**
 * number of terms in the polynomial
 */
  private int terms = 0;

  static Integer ZERO = new Integer(0);
  static Integer ONE = new Integer(1);

/**
 * Creates a new empty polynomial
 * @return new empty polynomial
 */
   public HLpolyn()
    {
    }

/**
 * Creates a new polynomial with a single term
 * @param coeff Integer coefficient of term
 * @param exp Integer exponent of term
 * @return new polynomial with specified coefficient and exponent
 */
   public HLpolyn(Integer coeff, Integer exp)
    {
        this.exps.push(exp);
        this.coeffs.push(coeff);
        this.terms = ONE;
    }

/**
 * Creates a new polynomial with a single term
 * @param coeff BigInteger coefficient of term
 * @param exp Integer exponent of term
 * @return new polynomial with specified coefficient and exponent
 */
   public HLpolyn(BigInteger coeff, Integer exp)
    {
        this.exps.push(exp);
        this.coeffs.push(coeff);
        this.terms = ONE;
    }

/**
 * Creates a new polynomial with same values
 * as existing polynomial; same as clone()
 * @param polyn2 existing polynomial
*/

 public HLpolyn(HLpolyn other)
 {
     this.exps = other.exps;
     this.coeffs = other.coeffs;
     this.terms = other.terms;
 }

/**
 * Confirms that this is not a function
 * @return False
 */
   public Boolean isFun()
    {
        return false;
    }

/**
 * Confirms that this is a polynomial
 * @return True
 */
   public Boolean isPol()
    {
        return true;
    }

/**
 * Verifies whether this polynomial is a single integer
 * @return True iff this is an integer
 */
   public Boolean isInt()
    {
        return this.terms == 1 && (int) this.exps.peekFirst() == 0; // x^0 = 1
    }

/**
 * Returns a clone of this polynomial where exps and coeffs have been cloned
 * @return a clone of this polynomial
 */
   public HLpolyn clone()
    {
        HLpolyn cloned = new HLpolyn();
        cloned.exps = (LinkedList) this.exps;
        cloned.coeffs = (LinkedList) this.coeffs;
        cloned.terms = this.terms;
        return cloned;
    }

/**
 * Returns a negation of this polynomial
 * @return this polynomial with all the coefficients negated
 */

  public HLpolyn negate()
    {
        Object[] exps = this.exps.toArray();
        Object[] coeffs = this.coeffs.toArray();
        int n = this.terms;

        for(int i = 0; n > i; i++) {
            this.coeffs.set(i, (int) coeffs[i] * -1);
        }

        return this;

        /*
        // pop coeffs from linkedlist
        HLpolyn negated = this.clone();
        int[] negatedCoeffs = new int[negated.terms];
        for(int i = 0; negated.terms > i; i++) {
            negatedCoeffs[i] = -1 * (int) negated.coeffs.pop();
        }
        // push negated coeffs back
        for(int i = 0; negated.terms > i; i++) {
            negated.coeffs.push(negatedCoeffs[i]);
        }
        return negated;
        */
    }

 public void add(HLpolyn polyn2)
 {
     List aExps = new ArrayList(this.exps);
     List aCoeffs = new ArrayList(this.coeffs);
     int i = 0;

     List bExps = new ArrayList(polyn2.exps);
     List bCoeffs = new ArrayList(polyn2.coeffs);
     int j = 0;

     while(aExps.size() > i && bExps.size() > j)
     {
         int a = (int) aExps.get(i);
         int b = (int) bExps.get(j);
         if (a > b)
         {
             i++;
         }
         else if (b > a)
         {
             aExps.add(i, b);
             aCoeffs.add(i, bCoeffs.get(j));
             i++;
             j++;
         }
         else
         {
             aCoeffs.set(i, (int) aCoeffs.get(i) + (int) bCoeffs.get(j));
             i++;
             j++;
         }
     }

     while(bExps.size() > j)
     {
         int b = (int) bExps.get(j);
         aExps.add(i, b);
         aCoeffs.add(i, bCoeffs.get(j));
         i++;
         j++;
     }

     this.exps = new LinkedList(aExps);
     this.coeffs = new LinkedList(aCoeffs);
     this.terms = aExps.size();
     this.cleanzeros();


     return;
 }

/*
 public void add(HLpolyn polyn2)
 {
     Object[] aExps = this.exps.toArray();
     Object[] aCoeffs = this.coeffs.toArray();
     int aSize = this.terms;
     int expInd = 0;
     int coeffInd = 0;

     Object[] bExps = polyn2.exps.toArray();
     Object[] bCoeffs = polyn2.coeffs.toArray();
     int bSize = this.terms;
     int bExpInd = 0;
     int bCoeffInd = 0;

     while(aSize > expInd && bSize > bExpInd) {
         int a = (int) aExps[expInd];
         int b = (int) bExps[bExpInd];
         if (a > b)
         {
             expInd++;
         }
         else if (b > a)
         {
             this.exps.add(coeffInd, b);
             this.coeffs.add(coeffInd, (int) bCoeffs[bExpInd]);
             this.terms++;
             coeffInd++;
             bExpInd++;
         }
         else // a = b
         {
             this.coeffs.set(coeffInd, (int) aCoeffs[expInd] + (int) bCoeffs[bExpInd]);
             coeffInd++;
             expInd++;
             bExpInd++;
         }
     }
     while(bSize > bExpInd) {
         int b = (int) bExps[bExpInd];
         this.exps.add(coeffInd, b);
         this.coeffs.add(coeffInd, (int) bCoeffs[bExpInd]);
         this.terms++;
         coeffInd++;
         bExpInd++;
     }

     this.cleanzeros();
     return;
 }
 */

/**
 * Creates a new polynomial by adding a second polynomial to this polynomial
 * @param polyn2 polynomial added to this polynomial
 * @return sum of two polynomials
 */
 /*
  public HLpolyn add(HLpolyn polyn2)
  {
      // first polyn and its index
      Object[] aExps = this.exps.toArray();
      Object[] aCoeffs = this.coeffs.toArray();
      int i = 0;
      // second polyn and its index
      Object[] bExps = polyn2.exps.toArray();
      Object[] bCoeffs = polyn2.coeffs.toArray();
      int j = 0;

      HLpolyn sum = new HLpolyn();
      // one pass through polynomials preserving order
      while(this.terms > i && polyn2.terms > j) {
          if ((int) aExps[i] > (int) bExps[j]) {
              sum.exps.add(aExps[i]);
              sum.coeffs.add(aCoeffs[i]);
              i++;
          }
          else if ((int) bExps[i] > (int) aExps[j]) {
              sum.exps.add(bExps[j]);
              sum.coeffs.add(bCoeffs[j]);
              j++;
          }
          else {
              sum.exps.add((aExps[i]));
              sum.coeffs.add((int) aCoeffs[i] + (int) bCoeffs[j]);
              i++;
              j++;
          }
          sum.terms++;
      }
      // copy leftover nums
      while(this.terms > i) {
          sum.exps.add(aExps[i]);
          sum.coeffs.add(aCoeffs[i]);
          i++;
      }

      while(polyn2.terms > j) {
          sum.exps.add(bExps[j]);
          sum.coeffs.add(bCoeffs[j]);
          j++;
      }

      this = sum.clone();
      return sum;
  }
  */

/**
 * Removes redundant 0s in polynomials
 * and adds a zero term if the polynomial is the constant 0
 * Note that this polynomial is modified
 * @return this modified polynomial
 */

  private void cleanzeros()
  {
      // if polynomial is constant 0:
      if(this.terms == 1 && (int) this.coeffs.peekFirst() == 0) {return;}
      // pop coeffs
      Object[] aExps = this.exps.toArray();
      Object[] aCoeffs = this.coeffs.toArray();
      int i = 0;
      int j = 0;
      int n = this.terms;

      while(n > i) {
          int a = (int) aCoeffs[i];
          if(a == 0) {
              this.terms--;
              this.coeffs.remove(j);
              this.exps.remove(j);
              i++;
          }
          else {i++; j++;}
      }
      return;
      /*
      HLpolyn simplified = this.clone();
      int n = simplified.terms;
      int[] coeffs = new int[n];
      int[] exps = new int[n];
      for(int i = 0; n > i; i++) {
          coeffs[i] = (int) simplified.coeffs.pop();
          exps[i] = (int) simplified.exps.pop();
      }
      simplified.terms = 0;
      // push non-zero terms back
      for(int i = 0; n > i; i++) {
          if(coeffs[i] != 0) {
              simplified.terms++;
              simplified.coeffs.push(coeffs[i]);
              simplified.exps.push(exps[i]);
          }
      }
      return simplified;
      */
  }

/**
 * Returns a string representation of this polynomial
 * @return a string representation of this polynomial
 */

  public String toString()
  {
      // if bigInteger evaluated polyn:


      boolean first = true;
      StringBuilder s = new StringBuilder();
      Iterator expsIter = this.exps.iterator();
      Iterator coeffsIter = this.coeffs.iterator();
      while(expsIter.hasNext() && coeffsIter.hasNext()) {
          int c = (int) coeffsIter.next();
          int exp = (int) expsIter.next();
          String sign = c > 0 ? "+" : "-";
          if(!first || c < 0) {s.append(sign);}
          // handle printing edge cases ***
          if(c == 0) {if (first) {s.append("0"); first=false; continue;} else {continue;}}

          if(exp == 0) {s.append(Math.abs(c));}
          else if(exp == 1) {
              if(Math.abs(c) == 1) {s.append("x");}
              else {s.append(Math.abs(c)); s.append("x");}
          }
          else {
              if(Math.abs(c) == 1) {s.append("x"); s.append(exp);}
              else {s.append(Math.abs(c)); s.append("x"); s.append(exp);}
          }
          first = false;
      }
      return s.toString();
  }

/**
 * Evaluates this polynomial when x is the passed xvalue
 * @param xvalue is value of x which must be an HLpolyn of degree 0
 * @return evaluated polynomial
 */
  public HLpolyn eval(HLpolyn xvalue)
    {
        int xval = (int) xvalue.coeffs.peekFirst();
        if (xvalue.terms != 1 && xval != 0) {return null;}
        Iterator expsIter = this.exps.iterator();
        Iterator coeffsIter = this.coeffs.iterator();
        int ans = 0;
        int coeff;
        int exp;
        while(expsIter.hasNext() && coeffsIter.hasNext()) {
            coeff = (int) coeffsIter.next();
            exp = (int) expsIter.next();
            ans += coeff * Math.pow(xval, exp);
        }
        return new HLpolyn(ans, 0);
    }

/**
 * Creates a new polynomial by multiplying integer  to this polynomial
 * @param polyn2 integer multiplying this polynomial
 * @return multiplication of two polynomials
 */

  public void multiply(HLpolyn polyn2)
    {
        if (polyn2.terms == 0 || this.terms == 0) {return;} // should always be int
        int constant = (int) polyn2.coeffs.peekFirst();

        HLpolyn result = new HLpolyn();
        for(int i = 0; this.terms > i; i++) {
            HLpolyn curr = new HLpolyn();
            for(int j = 0; polyn2.terms > j; j++)
            {
                curr.add(new HLpolyn((int) this.coeffs.get(i) * (int) polyn2.coeffs.get(j),
                            (int) this.exps.get(i) + (int) polyn2.exps.get(j)));
            }
            curr.cleanzeros();
            result.add(curr);
        }
        result.cleanzeros();
        this.terms = result.terms;
        this.coeffs = result.coeffs;
        this.exps = result.exps;
        return;
    }

/**
 * Creates a new polynomial by dividing this polynomial by a second polynomial
 * @param polyn2 polynomial dividing this polynomial
 * @return quotient of two polynomials
 */

  public void quotient(HLpolyn polyn2)
    {
        if (polyn2.terms == 0 | this.terms == 0) {return;} // should always be int
        int constant = (int) polyn2.coeffs.peekFirst();
        int exp = (int) polyn2.exps.peekFirst();

        for(int i = 0; this.terms > i; i++) {
            this.coeffs.set(i, (int) this.coeffs.get(i) / constant); // inefficient
            this.exps.set(i, (int) this.exps.get(i) + exp);
        }
        this.cleanzeros();
        return;
    }

/**
 * Compares this polynomial against polyn2 with the specified comparison
 * @param comparison is one of HLTreeConstants for LT, LE, GT, GE, EQ, NE
 * @param polyn2 is the second polynomial in the comparison
 * @return Boolean for this comparison polyn2
 */
  public Boolean compare(int comparison, HLpolyn polyn2)
    {
        // A = 'this' polyn, B = polyn2
        Iterator aCoeffs = this.coeffs.iterator();
        Iterator aExps = this.exps.iterator();
        Iterator bCoeffs = polyn2.coeffs.iterator();
        Iterator bExps = polyn2.exps.iterator();

        switch(comparison) {
            // EQ and NE
            case 27: case 28:
                if (this.terms != polyn2.terms) {return comparison == 27  ? false : true;}
                while(aExps.hasNext() && bExps.hasNext()) {
                    int aExp = (int) aExps.next();
                    int bExp = (int) bExps.next();
                    int aCoeff = (int) aCoeffs.next();
                    int bCoeff = (int) bCoeffs.next();

                    if(aExp != bExp) {return comparison == 27  ? false : true;}
                    if(aCoeff != bCoeff) {return comparison == 27  ? false : true;}
                }
                if(aExps.hasNext() || bExps.hasNext()) {return comparison == 27  ? false : true;}
                if (comparison == 27) {return true;}
                return false;

            // GT and GE
            case 25: case 26:
                while(aExps.hasNext() && bExps.hasNext()) {
                    int aExp = (int) aExps.next();
                    int bExp = (int) bExps.next();
                    int aCoeff = (int) aCoeffs.next();
                    int bCoeff = (int) bCoeffs.next();
                    //System.out.println(aCoeff + "x" + aExp + " compared to " + bCoeff + "x" + bExp);
                    if(aExp > bExp) {return true;}
                    else if (bExp > aExp) {return false;}
                    else { // same exponent, compare coeffs
                        if (aCoeff > bCoeff) {return true;}
                        else if (bCoeff > aCoeff) {return false;}
                        else {if(comparison == 26) {return true;} else{continue;}}
                    }
                }
                if (comparison == 25 && aExps.hasNext()) {return true;}
                return false;

            // LT and LE
            case 23: case 24:
                while(aExps.hasNext() && bExps.hasNext()) {
                    int aExp = (int) aExps.next();
                    int bExp = (int) bExps.next();
                    int aCoeff = (int) aCoeffs.next();
                    int bCoeff = (int) bCoeffs.next();
                    //System.out.println(aCoeff + "x" + aExp + " compared to " + bCoeff + "x" + bExp);
                    if(bExp > aExp) {return true;}
                    else if (aExp > bExp) {return false;}
                    else { // same exponent, compare coeffs
                        if (bCoeff > aCoeff) {return true;}
                        else if (bCoeff < aCoeff) {return false;}
                        else {if(comparison == 24) {return true;} else{continue;}}
                    }
                }
                if (comparison == 23 && bExps.hasNext()) {return true;}
                return false;
        }
        return true;
    }

/**
 * Called when this == polyn2 to answer the question: is this comparison polyn2?
 * @param comparison is one of HLTreeConstants for LT, LE, GT, GE, EQ, NE
 * @return Boolean.True when the comparison was EQ, LE, or GE and Boolean.False otherwise
 */

  private Boolean equal(int expected)
    {
        return true;
    }

/**
 * Called when this < polyn2 to answer the question: is this comparison polyn2?
 * @param comparison is one of HLTreeConstants for LT, LE, GT, GE, EQ, NE
 * @return Boolean.True when the comparison was NE, LT or LE and Boolean.False otherwise
 */

  private Boolean smaller(int expected)
    {
        return true;
    }

/**
 * Called when this > polyn2 to answer the question: is this comparison polyn2?
 * @param comparison is one of HLTreeConstants for LT, LE, GT, GE, EQ, NE
 * @return Boolean.True when the comparison was NE, GT or GLE and Boolean.False otherwise
 */

  private Boolean bigger(int expected)
    {
        return true;
    }
}
