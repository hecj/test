/*    */ package scallop.center.rest;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.ws.rs.core.Application;
/*    */ 
/*    */ public class RestApplication extends Application
/*    */ {
/*    */   public Set<Class<?>> getClasses()
/*    */   {
/* 12 */     Set rrcs = new HashSet();
/* 13 */     rrcs.add(ResourcesService.class);
/* 14 */     rrcs.add(RootResource.class);
/* 15 */     return rrcs;
/*    */   }
/*    */ }

/* Location:           C:\Users\hechaojie\Desktop\scallop-center-dist\classes\
 * Qualified Name:     scallop.center.rest.RestApplication
 * JD-Core Version:    0.6.1
 */