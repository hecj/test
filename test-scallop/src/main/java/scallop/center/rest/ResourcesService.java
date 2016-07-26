/*    */ package scallop.center.rest;
/*    */ 
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.PathParam;
/*    */ import javax.ws.rs.Produces;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import scallop.api.ScallopServerDaoException;
/*    */ import scallop.center.dao.Resource;
/*    */ import scallop.center.dao.ResourceDao;
/*    */ import scallop.center.dao.ibatis.ResourceDaoIbatis;
/*    */ 
/*    */ @Path("resources")
/*    */ public class ResourcesService
/*    */ {
/*    */   private static final String RESOURCE = "resource";
/*    */   private static final String NAME = "name";
/*    */   private static final String STRATEGY = "strategy";
/*    */ 
/*    */   @GET
/*    */   @Path("{name}")
/*    */   @Produces({"text/html"})
/*    */   public String getResource(@PathParam("name") String name)
/*    */     throws ScallopServerDaoException, JSONException
/*    */   {
/* 28 */     ResourceDao dao = new ResourceDaoIbatis();
/* 29 */     Resource resource = dao.getResourceByName(name);
/* 30 */     JSONObject jsonObject = new JSONObject();
/* 31 */     jsonObject.accumulate("name", name);
/* 32 */     jsonObject.accumulate("resource", resource.getResource());
/* 33 */     jsonObject.accumulate("strategy", resource.getStrategy());
/* 34 */     return jsonObject.toString();
/*    */   }
/*    */   @GET
/*    */   @Produces({"text/html"})
/*    */   public String getHtml() {
/* 40 */     return "<html><head></head><body>\nwelcome to  resource (as html text).\n</body></html>";
/*    */   }
/*    */ }

/* Location:           C:\Users\hechaojie\Desktop\scallop-center-dist\classes\
 * Qualified Name:     scallop.center.rest.ResourcesService
 * JD-Core Version:    0.6.1
 */