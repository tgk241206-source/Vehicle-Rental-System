package util;

public class EmailSender {

    /**
     * Gia lap viec gui email (In ra Console)
     */
    public static void send(String to, String subject, String content) {
        System.out.println("--------------------------------------------------");
        System.out.println("[SERVER EMAIL] Sending to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: \n" + content);
        System.out.println("--------------------------------------------------");
    }

}





