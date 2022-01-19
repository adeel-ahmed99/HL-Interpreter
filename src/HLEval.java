/* Generated By:JavaCC: Do not edit this line. HLDefaultVisitor.java Version 7.0.9 */
public class HLEval implements HLVisitor{
  public Object defaultVisit(SimpleNode node, Object data) throws Exception{
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTvar_decl node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTfn_decl node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTparam_decls node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTvarparam node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTfunparam node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTbody node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTassignment node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTif_stat node, Object data) throws Exception{
    int children = node.jjtGetNumChildren();
    if( (boolean) node.jjtGetChild(0).jjtAccept(this, data)) {
        return node.jjtGetChild(1).jjtAccept(this, data);
    }
    return node.jjtGetChild(2).jjtAccept(this, data);
  }
  public Object visit(ASTNULL node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTclause node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTfor_stat node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTforeach_stat node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTwhile_stat node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTfn_call node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTparameters node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTreturn_stat node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTprint_stat node, Object data) throws Exception{
    //System.out.println(node.toString());
    int children = node.jjtGetNumChildren();
    for(int i = 0; children > i; i++) {
        Node child = node.jjtGetChild(i);
        //System.out.println(child.toString());
        System.out.print(child.jjtAccept(this, data));
    }
    return data;
  }
  public Object visit(ASTprintln_stat node, Object data) throws Exception{
    int children = node.jjtGetNumChildren();
    for(int i = 0; children > i; i++) {
        Node child = node.jjtGetChild(i);
        System.out.print(child.jjtAccept(this, data));
    }
    System.out.println();
    return data;
  }
  public Object visit(ASTor node, Object data) throws Exception{
      int children = node.jjtGetNumChildren();
      for(int i = 0; children > i; i++) {
          Node child = node.jjtGetChild(i);
          if((boolean) child.jjtAccept(this, data)) {return true;} // short-circuit
      }
      return false;
  }
  public Object visit(ASTand node, Object data) throws Exception{
      int children = node.jjtGetNumChildren();
      for(int i = 0; children > i; i++) {
          Node child = node.jjtGetChild(i);
          if( ! (boolean) child.jjtAccept(this, data)) {return false;} // short-circuit
      }
      return true;
  }
  public Object visit(ASTnot node, Object data) throws Exception{
      return ! (boolean) node.jjtGetChild(0).jjtAccept(this, data);
  }
  public Object visit(ASTcomparison node, Object data) throws Exception{
      HLpolyn p1 = (HLpolyn) node.jjtGetChild(0).jjtAccept(this, data);
      int comparison = (int) node.jjtGetChild(1).jjtAccept(this, data);
      HLpolyn p2 = (HLpolyn) node.jjtGetChild(2).jjtAccept(this, data);
      return p1.compare(comparison, p2);
  }
  public Object visit(ASTLT node, Object data) throws Exception{
    return node.getId(); // 23
  }
  public Object visit(ASTLE node, Object data) throws Exception{
    return node.getId(); // 24
  }
  public Object visit(ASTGT node, Object data) throws Exception{
    return node.getId(); // 25
  }
  public Object visit(ASTGE node, Object data) throws Exception{
    return node.getId();  // 26
  }
  public Object visit(ASTEQ node, Object data) throws Exception{
    return node.getId();  // 27
  }
  public Object visit(ASTNE node, Object data) throws Exception{
    return node.getId(); // 28
  }
  public Object visit(ASTisint node, Object data) throws Exception{
      return ( (HLpolyn) node.jjtGetChild(0).jjtAccept(this, data)).isInt();
  }
  public Object visit(ASTispol node, Object data) throws Exception{
      return ( (HLpolyn) node.jjtGetChild(0).jjtAccept(this, data)).isPol();
  }
  public Object visit(ASTisfun node, Object data) throws Exception{
      return ( (HLpolyn) node.jjtGetChild(0).jjtAccept(this, data)).isFun();
  }
  public Object visit(ASTsum node, Object data) throws Exception{
      int children = node.jjtGetNumChildren();
      HLpolyn summation = new HLpolyn();
      for(int i = 0; children > i; i++) {
          Node child = node.jjtGetChild(i);
          summation.add((HLpolyn) child.jjtAccept(this, null));
      }
      return (HLpolyn) summation;
  }
  public Object visit(ASTplus node, Object data) throws Exception{
      Node child = node.jjtGetChild(0);
      return ((HLpolyn) child.jjtAccept(this, data));
  }
  public Object visit(ASTminus node, Object data) throws Exception{
      int children = node.jjtGetNumChildren();
      Node child = node.jjtGetChild(0);
      return ((HLpolyn) child.jjtAccept(this, data)).negate();
  }
  public Object visit(ASTproduct node, Object data) throws Exception{
     int children = node.jjtGetNumChildren();
     // initialize to 1
     HLpolyn prod = (HLpolyn) node.jjtGetChild(0).jjtAccept(this, null);

     for(int i = 1; children > i; i++) {
        Node child = node.jjtGetChild(i);
        if (child.getId() == 37) { // div
            prod.quotient((HLpolyn) child.jjtAccept(this, null));
        }
        else {
            prod.multiply((HLpolyn) child.jjtAccept(this, null));
        }
     }
     return (HLpolyn) prod;
  }
  public Object visit(ASTtimes node, Object data) throws Exception{
      Node child = node.jjtGetChild(0);
      return ((HLpolyn) child.jjtAccept(this, data));
  }
  public Object visit(ASTdiv node, Object data) throws Exception{
      Node child = node.jjtGetChild(0);
      return ((HLpolyn) child.jjtAccept(this, data));
  }
  public Object visit(ASTeval node, Object data) throws Exception{
      int children = node.jjtGetNumChildren();
      if (children != 2) {return data;}
      HLpolyn xval = (HLpolyn) node.jjtGetChild(1).jjtAccept(this, data);
      HLpolyn polyn = (HLpolyn) node.jjtGetChild(0).jjtAccept(this, data);
      return polyn.eval(xval);
  }

  public Object visit(ASTpolyn node, Object data) throws Exception{
      int coeff = (int) node.jjtGetChild(0).jjtAccept(this, data);
      int exp = (int) node.jjtGetChild(1).jjtAccept(this, data);
      HLpolyn polyn = new HLpolyn(coeff, exp);
      return polyn;
  }
  public Object visit(ASTidentifier node, Object data) throws Exception{
    return defaultVisit(node, data);
  }
  public Object visit(ASTinteger node, Object data) throws Exception{
    return node.value;
  }
  public Object visit(ASTstring node, Object data) throws Exception{
    return node.value;
  }
  public Object visit(ASTbool node, Object data) throws Exception{
    return node.value;
  }
}
/* JavaCC - OriginalChecksum=3a0829908d54fac5212052073dca7ea2 (do not edit this line) */