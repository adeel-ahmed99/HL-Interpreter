/* Generated By:JJTree: Do not edit this line. ASTbool.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTbool extends SimpleNode {
  public ASTbool(int id) {
    super(id);
  }

  public ASTbool(HL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(HLVisitor visitor, Object data) throws Exception {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=d4289887fdd976fd38cc77524d019c4a (do not edit this line) */
