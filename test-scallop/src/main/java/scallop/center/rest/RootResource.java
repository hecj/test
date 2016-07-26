/*    */package scallop.center.rest;

/*    */
/*    */import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import scallop.center.dao.Resource;
import scallop.center.dao.ResourceDao;
import scallop.center.dao.ibatis.ResourceDaoIbatis;

/*    */
/*    */@Path("/")
/*    */public class RootResource
/*    */{
	/*    */@GET
	/*    */@Produces({ "text/html" })
	/*    */public String getHtml()
	/*    */{
		/* 13 */return "<html><head></head><body style='font-family: 微软雅黑;font-size: 14px;'><center><br>welcome to scallop resource center<br>"+getAllResource()+"</center></body></html>";
		/*    */}

	private String getAllResource() {
		int total = 0;
		StringBuffer center = new StringBuffer("<table border='1' width='800' style='border-collapse: collapse;border-spacing: 0px;text-align:center'>");
		center.append(
				"<thead>" +
				"<tr>" +
					"<th scope='col' width=50>Id</th>" +
					"<th scope='col' width=450>Name</th>" +
					"<th scope='col' width=300>Resource</th>" +
				"</tr>" +
				"</thead>");
		try {
			ResourceDao dao = new ResourceDaoIbatis();
			List<Resource> list = dao.getAllResource();
			if(list != null ){
				total = list.size();
				center.append("<tbody>");
				for(Resource r : list){
					center.append("<tr>");
					center.append("<td>"+r.getId()+"</td>");
					center.append("<td>"+r.getName()+"</td>");
					center.append("<td>"+r.getResource()+"</td>");
					center.append("</tr>");
				}
				center.append("</tbody>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "获取资源出错！"+e.getMessage();
		}
		return "total:"+total+center.append("</table>").toString();
	}

	/*    */
}
