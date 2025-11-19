package util;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    // Kiem tra email hop le
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // Kiem tra mat khau (>= 6 ky tu)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    // Kiem tra ten khong rong
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Kiem tra so dien thoai hop le
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    // Kiem tra dia chi khong rong
    public static boolean isValidAddress(String addr) {
        return addr != null && !addr.trim().isEmpty();
    }

    // Kiem tra gia thue xe > 0
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    // Kiem tra chuoi rong
    public static boolean notEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Kiem tra ngay
    public static boolean validateDateOrder(java.time.LocalDate start, java.time.LocalDate end) {
        return start != null && end != null && start.isBefore(end);
    }

}




