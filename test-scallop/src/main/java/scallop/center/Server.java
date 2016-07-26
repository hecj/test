/*    */ package scallop.center;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.restlet.Component;
/*    */ import org.restlet.Context;
/*    */ import org.restlet.VirtualHost;
/*    */ import org.restlet.data.Protocol;
/*    */ import org.restlet.ext.jaxrs.JaxRsApplication;
/*    */ import org.restlet.util.ServerList;
/*    */ import scallop.center.rest.RestApplication;
/*    */ 
/*    */ public class Server
/*    */ {
/*    */   private static final int PORT = 8182;
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 14 */     int port = 8182;
/* 15 */     if (args.length > 0) {
/* 16 */       String[] arrayOfString = args; int j = args.length; for (int i = 0; i < j; i++) { String arg = arrayOfString[i];
/* 17 */         if (arg.startsWith("-p=")) {
/* 18 */           port = Integer.parseInt(arg.replace("-p=", ""));
/*    */         }
/*    */       }
/*    */     }
/* 22 */     Component component = new Component();
/*    */ 
/* 24 */     component.getServers().add(Protocol.HTTP, port);
/* 25 */     JaxRsApplication application = new JaxRsApplication(component.getContext().createChildContext());
/*    */ 
/* 27 */     application.add(new RestApplication());
/*    */ 
/* 29 */     component.getDefaultHost().attach(application);
/*    */ 
/* 31 */     component.start();
/* 32 */     System.out.println("registry center is started. port is " + port);
/*    */   }
/*    */ }

/* Location:           C:\Users\hechaojie\Desktop\scallop-center-dist\classes\
 * Qualified Name:     scallop.center.Server
 * JD-Core Version:    0.6.1
 */