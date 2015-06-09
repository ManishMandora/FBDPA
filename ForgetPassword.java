 import org.vertx.java.core.Handler;
  import org.vertx.java.core.http.HttpServerRequest;
  import org.vertx.java.platform.Verticle;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.http.HttpServer;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

  public class ForgetPassword extends Verticle
   {
    
       public void start()
       {
             
         final String login="ForgetPassword";


          vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>()
          {
             public void handle(HttpServerRequest req) 
            {
                 String file = req.path();
	       
		System.out.println(file);
               if(file.equals("/ForgetPassword"))
                {
                  try {
			
			String uname=req.params().get("email");
                  	
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection  =DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","");
			 Statement st = connection.createStatement();
		 	 ResultSet rs= st.executeQuery("select * from user where uname= '" + uname + "'");
			  
			if(rs.next())
                         {
				 System.out.println("Demo.......");
                                 System.out.println(rs.getString("uname"));
                             
		                 
      // Recipient's email ID needs to be mentioned.
      String to =  "pankaj20sharma19@gmail.com";   //uname;
System.out.println("..........."  +to);

      // Sender's email ID needs to be mentioned
      String from = "pankaj@reducedata.co";

      // Assuming you are sending email from localhost
     // String host = "localhost"; 
	String host = "smtp.gmail.com"; //465



      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
     // properties.setProperty("mail.smtp.host", host);
       properties.setProperty("mail.smtp.host", host);
	
	properties.setProperty("mail.smtp.port", "587");
	properties.setProperty("mail.smtp.auth", "true");
	properties.setProperty("mail.smtp.starttls.enable", "true");
	
	properties.setProperty("mail.smtp.user", from);
	properties.setProperty("mail.smtp.password", "9926032092");


   
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

     
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>This is actual message</h1>",
                            "text/html" );

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }

	else{

             req.response().end("Email does not exist");    

             }
	 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
        
  
                }                 
              }
          }).listen(8080);
      }
  }
