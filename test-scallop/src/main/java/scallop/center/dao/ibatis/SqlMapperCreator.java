/*    */ package scallop.center.dao.ibatis;
/*    */ 
/*    */ import com.ibatis.common.resources.Resources;
/*    */ import com.ibatis.sqlmap.client.SqlMapClient;
/*    */ import com.ibatis.sqlmap.client.SqlMapClientBuilder;
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ 
/*    */ public class SqlMapperCreator
/*    */ {
/*    */   private static SqlMapClient sqlMapper;
/*    */ 
/*    */   public static SqlMapperCreator getInstance()
/*    */   {
/* 23 */     return SingletonHolder.instance;
/*    */   }
/*    */ 
/*    */   private SqlMapperCreator() {
/*    */     try {
/* 28 */       Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
/* 29 */       sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
/* 30 */       reader.close();
/*    */     } catch (IOException e) {
/* 32 */       throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public SqlMapClient getSqlMapper() {
/* 37 */     return sqlMapper;
/*    */   }
/*    */ 
/*    */   static class SingletonHolder
/*    */   {
/* 19 */     static SqlMapperCreator instance = new SqlMapperCreator();
/*    */   }
/*    */ }

/* Location:           C:\Users\hechaojie\Desktop\scallop-center-dist\classes\
 * Qualified Name:     scallop.center.dao.ibatis.SqlMapperCreator
 * JD-Core Version:    0.6.1
 */