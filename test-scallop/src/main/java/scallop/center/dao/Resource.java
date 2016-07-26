/*    */ package scallop.center.dao;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Resource
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1980400279647385980L;
/*    */   private Long id;
/*    */   private String name;
/*    */   private String resource;
/*    */   private String strategy;
/*    */ 
/*    */   public String getStrategy()
/*    */   {
/* 15 */     return this.strategy;
/*    */   }
/*    */   public void setStrategy(String strategy) {
/* 18 */     this.strategy = strategy;
/*    */   }
/*    */   public Long getId() {
/* 21 */     return this.id;
/*    */   }
/*    */   public void setId(Long id) {
/* 24 */     this.id = id;
/*    */   }
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 30 */     this.name = name;
/*    */   }
/*    */   public String getResource() {
/* 33 */     return this.resource;
/*    */   }
/*    */   public void setResource(String resource) {
/* 36 */     this.resource = resource;
/*    */   }
/*    */ }

/* Location:           C:\Users\hechaojie\Desktop\scallop-center-dist\classes\
 * Qualified Name:     scallop.center.dao.Resource
 * JD-Core Version:    0.6.1
 */