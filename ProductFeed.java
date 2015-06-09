
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.http.HttpServer;
import java.sql.*;
public class ProductFeed extends Verticle
 {
  
     public void start()
     {
           
       final String login="ProductFeed";


        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>()
        {
           public void handle(HttpServerRequest req) 
          {
             String file = req.path();
             System.out.println(file);

             if(file.equals("/ProductFeed"))
              {
                
		try {
		 String name=req.params().get("fileurl");
   		 String docFile = req.params().get("docFile");
                 System.out.println(docFile.length());
		 String schedule = req.params().get("schedule");
		 String time = req.params().get("time");

		 Class.forName("com.mysql.jdbc.Driver");
		 Connection connection  =DriverManager.getConnection("jdbc:mysql://localhost:3306/DPA","root","");
		 PreparedStatement ps = connection.prepareStatement("insert into demo(name,file,schedule,time) values(?,?,?,?)");

		       ps.setString(1, name);
		       ps.setString(2, docFile);
		       ps.setString(3, schedule);
		       ps.setString(4, time);
		       ps.executeUpdate(); 
		
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
      

              }                 
            }
        }).listen(8080);
    }
}
