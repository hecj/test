/*    */ package scallop.center.dao.ibatis;
/*    */ 
/*    */ import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scallop.api.ScallopServerDaoException;
import scallop.center.dao.Resource;
import scallop.center.dao.ResourceDao;
import sun.org.mozilla.javascript.internal.ast.TryStatement;

import com.ibatis.sqlmap.client.SqlMapClient;
/*    */ 
/*    */ public class ResourceDaoIbatis
/*    */   implements ResourceDao
/*    */ {
/* 16 */   private static final Logger logger = LoggerFactory.getLogger(ResourceDaoIbatis.class);
/*    */ 
/* 18 */   private SqlMapClient sqlMapper = SqlMapperCreator.getInstance().getSqlMapper();
/*    */ 
/*    */   public Resource getResourceByName(String name) throws ScallopServerDaoException {
/*    */     try {
/* 22 */       return (Resource)this.sqlMapper.queryForObject("selectResourceByName", name);
/*    */     } catch (SQLException e) {
/* 24 */       logger.error(e.getMessage(), e);
/* 25 */       e.printStackTrace();
/* 26 */       throw new ScallopServerDaoException(e.getMessage(), e);
/*    */     }
/*    */   }
/*    */
public List<Resource> getAllResource() throws ScallopServerDaoException {

	try {
		
		return this.sqlMapper.queryForList("selectAllResources");
		
	} catch (Exception e) {
		logger.error("ERROR , query resource ! ");
		e.printStackTrace();
	}
	return null;
} }

