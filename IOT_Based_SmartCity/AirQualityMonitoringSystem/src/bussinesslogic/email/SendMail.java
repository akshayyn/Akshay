package bussinesslogic.email;



import static Business.Organization.Organization.Type.Citizen;
import Business.UserAccount.UserAccount;
import Citizen.Citizen;
import business.MedicalHistory.VitalSign;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
    
    public void sendEmail(Citizen citizen,String subject,String content){
       
        final String username = "vitalsignsystem@gmail.com";
        final String password = "application1";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);


            message.setFrom(new InternetAddress("vitalsignsystem@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(citizen.getEmailId()));
            message.setSubject(subject);
            message.setText(content) ;

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void  sendAbnormalVitalSignsEmail(Citizen c,VitalSign v){

            
            String subject = "Abnormal vital Sign detected";
            String content ="Dear "+ c.getPerson().getFirstName()+","
                + "\n\nWe are sorry to inform that we have detedcted an abnormality in your vital Signs record recently."
            +"\n\n Vital Sign record time stamp : " + v.getTimeStamp().toString()+
                    "\n\n Please enter the application to see further details." +
                    "\n\n Sincerly,"+
                    "\n\n" + "City Air Quality and Health Monitor System" ;
            this.sendEmail(c, subject, content);
            
    }
    public void sendRegistrationSuccessEmail(UserAccount ua){
        String subject = "Registered succesfully";
            String content ="Dear "+ ua.getCitizen().getPerson().getFirstName()+","
                + "\n\nWe are pleased to inform that you are succesfully registered in City Air Quality and Health Control Application."
            +"\n\n Following are your credentials" +
                    "\n\n User name : " +ua.getUsername()+
                    "\n\n Password : "+ua.getPassword()+
                    "Please Log into your account"+
                    "\n\n Sincerly,"+
                    "\n\n" + "City Air Quality and Health Monitor System" ;
            this.sendEmail(ua.getCitizen(), subject, content);
    }
}