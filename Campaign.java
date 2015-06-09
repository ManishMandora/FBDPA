
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.http.HttpServer;
import java.sql.*;
public class Campaign extends Verticle
 {
  
     public void start()
     {
           
       final String login="Campaign";


        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>()
        {
           public void handle(HttpServerRequest req) 
          {
               String file = req.path();
	       
		System.out.println(file);
             if(file.equals("/Campaign"))
              {
                 System.out.println("In IF");
			try {
			
			 String name=req.params().get("name");
           		 //int pid=Integer.parseInt(req.params().get("productId")); 
                          String pid=req.params().get("productId");
			System.out.println(name);
			System.out.println(pid); 
		
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection  =DriverManager.getConnection("jdbc:mysql://localhost:3306/DPA","root","");
			PreparedStatement ps = connection.prepareStatement("insert into demo values(?,?)");
			
			ps.setString(3,pid);
		        ps.setString(2, name);
			ps.executeUpdate(); 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
      

              }                 
            }
        }).listen(8080);
    }
}
