package emailSend;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Sendmail {
	public static void send() throws MessagingException, UnsupportedEncodingException {
		   
		   String info=ReadHTML.reMailString();
		   
		    //�ʼ�������
		   String host="smtp.qq.com";
			//������
		   String from="3029923422@qq.com";
			//�ռ���
		   String to="1073650903@qq.com";
		   //������
		   String toCC1="3029923422@qq.com";
		   String toCC2="3029923422@qq.com";
		   String username="3029923422@qq.com";
		   String password="ryavkpyzgvzkdffg";
			//�ʼ��Ự����
			//Properties  p=System.getProperties();
		   Properties  p=new Properties();
		   	p.put("mail.smtp.host", host);
		   	/*
			   	p.put("mail.smtp.auth", "true");
			   	//����һ��������֤��
			   	Authenticator auth = new MyAuthenticator(username, password);
			   	//���Session
			    Session session=Session.getDefaultInstance(p,auth);
		   */
		   	//////////////////sesion���Transprot����
		   Session session=Session.getDefaultInstance(p,null);
		   		session.setDebug(true);
		   	
		   	/////////////////////
		   //����Message��Ϣ
		   MimeMessage message=new MimeMessage(session);
		   //�����ʼ������ߵ�ַ
		   Address fromAD = new InternetAddress(from,"���Ȼ");
		   //nternetAddress(from)
		   //�����ʼ�������
					message.setFrom(fromAD);	
		   //�����ʼ��Ľ��յ�ַ
		   Address toAD = new InternetAddress(to);
		   //��������������
		   Address toCAD1=new InternetAddress(toCC1);
		   Address toCAD2=new InternetAddress(toCC2);
		   Address [] toCs={toCAD1,toCAD2};
		   //�����ʼ��Ľ��յ�ַ
					message.setRecipient(Message.RecipientType.TO,toAD);
					message.addRecipients(Message.RecipientType.CC,toCs );
			//���÷���ʱ��
					message.setSentDate(new Date());
		   	//��������	
					message.setSubject("Hello JavaMail");	
			/*
			   	//������Ϣ����,�ı�  		
						message.setText("Welcome To JavaMail");
				//����HTML����
						message.setContent("<a href='http://www.163.com'>�ٶ�</a>","text/html;charset=utf-8");
			*/	
			// MimeMultipart����һ�������࣬����MimeBodyPart���͵Ķ���    	
			Multipart mainPart = new MimeMultipart();  
			//����һ������HTML���ݵ�MimeBodyPart
			BodyPart body=new MimeBodyPart();
			//����html����
				body.setContent(info,"text/html;charset=utf-8");
			//��MimeMultipart����Ϊ�ʼ�����
				mainPart.addBodyPart(body);
				message.setContent(mainPart);
			///////////////////////sesion���Transprot
			Transport transport=session.getTransport("smtp");
		   		transport.connect(host, username, password);
		   		transport.sendMessage(message,message.getAllRecipients());
		   		transport.close();
		   		
			//////////////////////
			
			//	Transport.send(message);
			
		   
	   }
	   public static void main(String[] args) throws MessagingException, UnsupportedEncodingException  {
			// TODO Auto-generated method stub
		  send();
		}

}
