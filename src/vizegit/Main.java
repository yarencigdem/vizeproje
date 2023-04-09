package vizegit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Main extends işlemler {
	static Scanner ss=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
    	
        System.out.println("programa hoşgeldiniz...");
		işlemler islem =new işlemler();
		
		
		
		int devam;
		
		do {	// uygulamaya devam edip etmeyeceğini kontrol etmek için do while kullandım
		System.out.println("1- Elit üye ekleme\r\n"+ "2- Genel Üye ekleme\r\n"+ "3- Mail Gönderme\r\n"+ "yapmak istediğiniz işlemi seçiniz.");	
		 Scanner scan1=new Scanner(System.in);
	     int num= scan1.nextInt();	
		switch(num)	{
		case 1: 
			islem.secim1();
			break;
		case 2:islem.secim2();


			break;
		case 3:
			System.out.println("\r\nyapmak istediğiniz işlemi secin"+"1- Elit üyelere mail\r\n"+"2- genel üyelere mail\r\n"+"3- tüm üyelere mail");
			Scanner scann= new Scanner(System.in);
			int n1=scann.nextInt();
			if(n1==2) {//genel üyelere mail
				System.out.println("genel üyelere mail gönderiliyor... ");
				System.out.println("alıcı mail giriniz:");
				
				String alıcımail2=ss.nextLine();
				JavaMail.sendMail(alıcımail2);
				
				
			}  if(n1==1) {//elit üyelere mail
				
				System.out.println("elit üyelere mail göderiliyor...");
				
				System.out.println("alıcı mail giriniz:");
				
				String alıcımail=ss.nextLine();
				JavaMail.sendMail(alıcımail);
				
				
			}
			 if(n1==3) {// tüm üyelere mail
				System.out.println("tüm üyelere mail gönderiliyor...");
				System.out.println("alıcı mail giriniz:");
				
				
				String alıcımail1=ss.nextLine();
				
				
				JavaMail.sendMail(alıcımail1);}
				
			
			
			break;
			
		}
		System.out.println("tekrar islem yapmak istiyor musunuz?\n evet ise: 1\n hayır ise: 2'ye tıklayınız.\n");
		Scanner sca= new Scanner(System.in);
		 devam=sca.nextInt();
		} while(devam==1);
		 
		 
	       
    }}
    
class JavaMail  {//mail gönderme işlermleri
	static Scanner scan=new Scanner(System.in);
    public static void sendMail(String recepient) throws Exception {
    	
        System.out.println("Mesaj göndermeye hazırlanılıyor");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        System.out.println("email adresinizi giriniz:");
        String myAccountEmail=scan.nextLine();
        System.out.println("şifreyi giriniz:");
        String password=scan.nextLine();
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message= prepareMessage(session, myAccountEmail, recepient) ;
        Transport.send(message);
        System.out.println("mail basarıyla gönderildi");}
    
private static Message prepareMessage(Session session, String myAccountEmail, String recepient){
    try {
        Message message= new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        System.out.println("lutfen konuyu giriniz:");
        String konu=scan.nextLine();
        System.out.println("mesaj içeriğini giriniz:");
        String mesaj=scan.nextLine();
        message.setSubject(konu);
        message.setText(mesaj);
        return message;
    } catch (Exception ex) {
        Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null,ex);
    }
return null;
}
    }
class işlemler{
	
	public void secim1() throws IOException {
		File file =new File("elitüyeler.txt");//  elit üyeler için dosyaya yazma 
		if (!file.exists()) {
			file.createNewFile();
		}
		
		
		FileWriter fwriter =new FileWriter(file,false);
		BufferedWriter bwriter= new BufferedWriter(fwriter);
		
		for (int i=0;i<3;i++) {
			Scanner s= new Scanner(System.in);
			System.out.println("ad giriniz:");
		String isim=s.nextLine();
		System.out.println(" soyad giriniz:");
		String soyad=s.nextLine();
		System.out.println("mail giriniz:");
		String email=s.nextLine();
		bwriter.write( isim );
		bwriter.write("\t");
		bwriter.write(soyad );
		bwriter.write("\t");
		bwriter.write( email );
		bwriter.write("\n");
		}
		bwriter.close();
		   FileReader fileReader = new FileReader(file);//vermiş olduğunuz elit üyeleri dosyadan okuma
			String line,line1,line2;
			
			BufferedReader br = new BufferedReader(fileReader);
			 
		        
			while ((line = br.readLine()) == " ") {	           

			}
			 System.out.println(line);
		  
		  
		    while ((line1 = br.readLine()) == " ") {
	           
	           

			}
		    System.out.println(line1);
			   
			    while ((line2 = br.readLine()) ==" ") {
			    }
 
			    	System.out.println(line2);
            
		  
			
			;
			
			br.close();
	
	}
		
	public void secim2() throws IOException {
		//genel üyeler için dosyaya yazma 
		File file2 =new File("genelüyeler.txt");
		if (!file2.exists()) {
			file2.createNewFile();
		}
		System.out.println("ad giriniz.");
		Scanner sc= new Scanner(System.in);
		String isim=sc.nextLine();
		System.out.println("soyad giriniz:");
		String soyad=sc.nextLine();
		System.out.println("email giriniz:");
		String email=sc.nextLine();
		FileWriter fwriter1 =new FileWriter(file2,false);
		BufferedWriter bwriter1= new BufferedWriter(fwriter1);
		bwriter1.write(isim+"\t");
		
		bwriter1.write(soyad+"\t");
		bwriter1.write(email);
		bwriter1.close();
		FileReader fileReader = new FileReader(file2);//vermiş olduğunuz genel üyeleri dosyadan okuma
		String line;
		
		BufferedReader br = new BufferedReader(fileReader);

		while ((line = br.readLine()) != null) {
            System.out.println(line);
             
		   
		

		}

		br.close();
		
	}
	
}
 

