package service;

import util.EmailSender;

public class NotificationService {

    // Gui thong bao qua email
    public void sendEmail(String to, String subject, String message) {
        System.out.println("\n===== GUI EMAIL (MO PHONG) =====");
        EmailSender.send(to, subject, message);
    }

    // Gui SMS mo phong
    public void sendSMS(String phone, String message) {
        System.out.println("\n===== GUI SMS (MO PHONG) =====");
        System.out.println("To: " + phone);
        System.out.println("Message: " + message);
        System.out.println("================================");
    }

    // Thong bao dat xe thanh cong
    public void notifyBookingSuccess(String email, String vehicleName, String start, String end, double total) {
        String subject = "Xac nhan dat xe thanh cong";
        String content = String.format("Ban da dat xe %s tu ngay %s den ngay %s.\nTong tien: %,.0f VND.", 
                                       vehicleName, start, end, total);
        sendEmail(email, subject, content);
    }

    // Thong bao huy don
    public void notifyCancelBooking(String email, int bookingId) {
        String subject = "Huy don dat xe";
        String content = "Don dat xe #" + bookingId + " da duoc huy thanh cong theo yeu cau.";
        sendEmail(email, subject, content);
    }

    // Thong bao cap nhat trang thai don (Admin duyet, Hoan thanh...)
    public void notifyBookingStatus(String email, int bookingId, String newStatus) {
        String subject = "Cap nhat trang thai don thue xe";
        String content = "Don hang #" + bookingId + " cua ban da duoc cap nhat trang thai thanh: " + newStatus.toUpperCase();
        sendEmail(email, subject, content);
    }

    // Thong bao thanh toan thanh cong
    public void notifyPayment(String email, double amount, String method) {
        String subject = "Thanh toan thanh cong";
        String content = String.format("He thong da nhan duoc khoan thanh toan %,.0f VND.\nPhuong thuc: %s.", amount, method);
        sendEmail(email, subject, content);
    }
}