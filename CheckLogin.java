 import org.vertx.java.core.Handler;
  import org.vertx.java.core.http.HttpServerRequest;
  import org.vertx.java.platform.Verticle;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.http.HttpServer;
import java.sql.*;
  public class CheckLogin extends Verticle
   {
    
       public void start()
       {
             
         final String login="CheckLogin";


          vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>()
          {
             public void handle(HttpServerRequest req) 
            {
                 String file = req.path();
	       
		System.out.println(file);
               if(file.equals("/CheckLogin"))
                {
                  

                 // req.response().end("username : "  + uname + "password : " + upass);    
		
			try {
			
			String uname=req.params().get("username");
                  	String upass=req.params().get("password"); 
				
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection  =DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","");
			 Statement st = connection.createStatement();
		 	 ResultSet rs= st.executeQuery("select * from user where uname='" + uname + "' and upass='" + upass + "'");
			
			if(rs.next()){
				
				req.response().sendFile("home.html");
				 
			      }

			else{

                             req.response().end("Invalid UserName or Password..");    

                             }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
        
  
                }                 
              }
          }).listen(8080);
      }
  }
