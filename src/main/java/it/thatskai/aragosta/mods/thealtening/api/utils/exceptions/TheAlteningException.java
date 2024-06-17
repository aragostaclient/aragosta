/*    */ package it.thatskai.aragosta.mods.thealtening.api.utils.exceptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TheAlteningException
/*    */   extends RuntimeException
/*    */ {
/*    */   public TheAlteningException(String error, String errorMessage) {
/* 27 */     super(String.format("[%s]: %s", new Object[] { error, errorMessage }));
/*    */   }
/*    */ }
