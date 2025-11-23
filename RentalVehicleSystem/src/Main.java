import controller.AdminController;
import controller.AuthController;
import controller.BookingController;
import controller.StaffController;
import controller.VehicleController;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Admin;
import model.Booking;
import model.Customer;
import model.Staff;
import model.Vehicle;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    
    private static VehicleController vehicleController = new VehicleController();
    private static AdminController adminController = new AdminController();
    private static AuthController authController = new AuthController();
    private static BookingController bookingController = new BookingController();
    private static StaffController staffController = new StaffController();

    public static void main(String[] args) {
        while (true) {
            showLandingPage();
        }
    }

    // LANDING PAGE (TRANG CHU)
    private static void showLandingPage() {
        System.out.println("\n==========================================");
        System.out.println("   HE THONG THUE XE (VEHICLE RENTAL)");
        System.out.println("==========================================");
        System.out.println("1. Dang nhap (Khach hang)");
        System.out.println("2. Dang ky tai khoan moi");
        System.out.println("3. Dang nhap Nhan Vien (Staff)");
        System.out.println("4. Dang nhap Quan Tri Vien (Admin)");
        System.out.println("0. Thoat chuong trinh");
        System.out.println("==========================================");
        System.out.print(">> Chon chuc nang: ");

        int choice = getChoice();

        switch (choice) {
            case 1: // DANG NHAP KHACH
                handleCustomerLogin();
                break;

            case 2: // DANG KY KHACH
                handleRegister();
                break;
            
            case 3: // DANG NHAP STAFF
                handleStaffLogin();
                break;

            case 4: // DANG NHAP ADMIN
                handleAdminLogin();
                break;

            case 0:
                System.out.println("Tam biet! Hen gap lai.");
                System.exit(0);
                break;

            default:
                System.out.println(">> Lua chon khong hop le.");
        }
    }

    // LOGIC XU LY CHO KHACH HANG

    private static void handleCustomerLogin() {
        System.out.println("\n--- DANG NHAP KHACH HANG ---");
        System.out.print("Email: "); String email = sc.nextLine().trim();
        System.out.print("Mat khau: "); String pass = sc.nextLine().trim();

        Customer customer = authController.login(email, pass);
        
        if (customer != null) {
            showCustomerMenu(customer);
        }
    }

    private static void handleRegister() {
        System.out.println("\n--- DANG KY TAI KHOAN ---");
        System.out.print("Ten: "); String name = sc.nextLine().trim();
        System.out.print("Email: "); String email = sc.nextLine().trim();
        System.out.print("Mat khau: "); String pass = sc.nextLine().trim();
        System.out.print("SDT: "); String phone = sc.nextLine().trim();
        System.out.print("Dia chi: "); String addr = sc.nextLine().trim();

        authController.register(name, email, pass, phone, addr);
    }

    private static void showCustomerMenu(Customer customer) {
        boolean isUsing = true;
        while (isUsing) {
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("   XIN CHAO: " + customer.getName().toUpperCase());
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("1. Xem danh sach xe");
            System.out.println("2. Tim kiem xe");
            System.out.println("3. Dat xe (Booking)");
            System.out.println("4. Xem lich su dat xe");
            System.out.println("5. Huy don dat");
            System.out.println("6. Dang xuat");
            System.out.print(">> Chon chuc nang: ");

            int choice = getChoice();

            switch (choice) {
                case 1: printVehicleList(vehicleController.getAllVehicles()); break;
                case 2: 
                    System.out.print("Nhap tu khoa: ");
                    printVehicleList(vehicleController.search(sc.nextLine())); 
                    break;
                case 3: handleBookingProcess(customer); break;
                case 4: 
                    System.out.println("\n--- LICH SU GIAO DICH ---");
                    bookingController.getBookingsByCustomer(customer.getId()).forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Nhap ID don hang muon huy: ");
                    try { bookingController.cancelBooking(Integer.parseInt(sc.nextLine())); }
                    catch (Exception e) { System.out.println("Loi ID."); }
                    break;
                case 6:
                    isUsing = false;
                    System.out.println(">> Da dang xuat.");
                    break;
                default: System.out.println("Lua chon khong hop le.");
            }
        }
    }

    // LOGIC XU LY CHO STAFF (NHAN VIEN)
    private static void handleStaffLogin() {
        System.out.println("\n--- DANG NHAP NHAN VIEN ---");
        System.out.print("Staff Email: "); String email = sc.nextLine().trim();
        System.out.print("Mat khau: "); String pass = sc.nextLine().trim();

        Staff staff = staffController.login(email, pass);

        if (staff != null) {
            showStaffDashboard(staff);
        } else {
            System.out.println(">> Dang nhap that bai (Sai email hoac mat khau).");
        }
    }

    private static void showStaffDashboard(Staff staff) {
        boolean isStaff = true;
        while (isStaff) {
            System.out.println("\n==========================================");
            System.out.println("   QUAY GIAO DICH VIEN - " + staff.getName().toUpperCase());
            System.out.println("==========================================");
            System.out.println("1. Xu ly GIAO XE (Pick-up/Check-in)");
            System.out.println("2. Xu ly TRA XE (Return/Close)");
            System.out.println("3. Xem tat ca lich su don hang");
            System.out.println("0. Dang xuat");
            System.out.println("==========================================");
            System.out.print(">> Chon nghiep vu: ");

            int choice = getChoice();

            switch (choice) {
                case 1: handleHandoverProcess(); break;
                case 2: handleReturnProcess(); break;
                case 3: 
                    System.out.println("\n--- TOAN BO DON HANG ---");
                    bookingController.getAllBookings().forEach(System.out::println);
                    break;
                case 0:
                    isStaff = false;
                    System.out.println(">> Staff da dang xuat.");
                    break;
                default: System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void handleHandoverProcess() {
        System.out.println("\n--- DANH SACH CHO GIAO XE (CONFIRMED) ---");
        List<Booking> list = staffController.getPendingHandoverBookings();
        
        if (list.isEmpty()) {
            System.out.println(">> Khong co don hang nao can giao.");
            return;
        }
        list.forEach(System.out::println);

        System.out.print("\nNhap ID don hang de giao xe: ");
        int bId = getChoice();
        
        System.out.println(">> Da kiem tra CCCD va Bang lai xe? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            if (staffController.processHandover(bId)) {
                System.out.println(">> Giao xe THANH CONG! Trang thai don -> 'Renting'.");
            } else {
                System.out.println(">> Loi: Khong tim thay ID hoac don chua duoc duyet.");
            }
        } else {
            System.out.println(">> Huy giao xe do thieu giay to.");
        }
    }

    private static void handleReturnProcess() {
        System.out.println("\n--- DANH SACH XE DANG THUE (RENTING) ---");
        List<Booking> list = staffController.getRentingBookings();
        
        if (list.isEmpty()) {
            System.out.println(">> Khong co xe nao dang duoc thue.");
            return;
        }
        list.forEach(System.out::println);

        System.out.print("\nNhap ID don hang khach tra xe: ");
        int bId = getChoice();

        System.out.println("--- KIEM TRA TINH TRANG XE ---");
        System.out.print("Co hu hong/tray xuoc moi khong? (y/n): ");
        String isDamaged = sc.nextLine();
        
        System.out.print("Co bi qua so Km gioi han khong? (y/n): ");
        String isOverKm = sc.nextLine();
        
        double surcharge = 0;
        if (isDamaged.equalsIgnoreCase("y") || isOverKm.equalsIgnoreCase("y")) {
            System.out.print(">> Nhap tong so tien phat sinh (Surcharges): ");
            try {
                surcharge = Double.parseDouble(sc.nextLine());
            } catch (Exception e) { surcharge = 0; }
        }

        System.out.println("Dang tinh toan hoa don cuoi cung...");
        double finalTotal = staffController.processReturn(bId, surcharge, "Returned");

        if (finalTotal >= 0) {
            System.out.println("******************************************");
            System.out.println("   HOA DON THANH TOAN (BILL)");
            System.out.println("******************************************");
            System.out.printf("Tong tien thue: $%.2f\n", finalTotal - surcharge);
            System.out.printf("Phi phat sinh:  $%.2f\n", surcharge);
            System.out.println("------------------------------------------");
            System.out.printf("TONG CONG:      $%.2f\n", finalTotal);
            System.out.println("******************************************");
            System.out.println(">> Don hang da Hoan tat (Completed).");
        } else {
            System.out.println(">> Loi: Khong tim thay ID.");
        }
    }

    // LOGIC XU LY CHO ADMIN (TRUC TIEP)

    private static void handleAdminLogin() {
        System.out.println("\n--- DANG NHAP QUAN TRI VIEN ---");
        System.out.print("Admin Email: "); String mail = sc.nextLine().trim();
        System.out.print("Mat khau: "); String pass = sc.nextLine().trim();

        Admin admin = adminController.login(mail, pass);
        
        if (admin != null) {
            showAdminDashboard(admin);
        }
    }

    private static void showAdminDashboard(Admin admin) {
        boolean isAdmin = true;
        while (isAdmin) {
            System.out.println("\n******************************************");
            System.out.println("   ADMIN DASHBOARD - " + admin.getName().toUpperCase());
            System.out.println("******************************************");
            System.out.println("1. Quan ly Xe (Them/Xoa/Sua)");
            System.out.println("2. Quan ly Khach hang");
            System.out.println("3. Quan ly Don hang (Duyet)");
            System.out.println("4. Xem Bao cao Thong ke");
            System.out.println("0. Dang xuat Admin");
            System.out.print(">> Admin chon: ");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    adminController.showAllVehicles();
                    System.out.println("[1] Them xe  [2] Xoa xe  [0] Quay lai");
                    String vOpt = sc.nextLine();
                    if (vOpt.equals("1")) {
                        System.out.print("Ten: "); String name = sc.nextLine();
                        System.out.print("Hang: "); String brand = sc.nextLine();
                        System.out.print("Loai: "); String type = sc.nextLine();
                        System.out.print("Gia: "); double price = Double.parseDouble(sc.nextLine());
                        adminController.addVehicle(new Vehicle(0, name, brand, type, price, "Available", "New"));
                    } else if (vOpt.equals("2")) {
                        System.out.print("ID xoa: ");
                        adminController.deleteVehicle(getChoice());
                    }
                    break;
                case 2: adminController.showAllCustomers(); break;
                case 3: 
                    adminController.showAllBookings();
                    System.out.println("[1] Cap nhat trang thai  [0] Quay lai");
                    if (sc.nextLine().equals("1")) {
                        System.out.print("ID Don: "); int bid = getChoice();
                        System.out.print("Trang thai (Confirmed/Completed): ");
                        adminController.updateBookingStatus(bid, sc.nextLine());
                    }
                    break;
                case 4: adminController.showReport(); break;
                case 0: 
                    isAdmin = false; 
                    System.out.println(">> Admin da dang xuat."); 
                    break;
                default: System.out.println("Sai lua chon.");
            }
        }
    }

    // CAC HAM TIEN ICH (HELPER)

    private static int getChoice() {
        try { return Integer.parseInt(sc.nextLine()); } 
        catch (Exception e) { return -1; }
    }

    private static void printVehicleList(List<Vehicle> list) {
        if (list.isEmpty()) System.out.println(">> Khong co xe.");
        else {
            System.out.println("--------------------------------------------------");
            System.out.printf("%-5s %-20s %-15s %-10s %-15s\n", "ID", "Ten Xe", "Hang", "Gia/Ngay", "Trang Thai");
            list.forEach(v -> System.out.printf("%-5d %-20s %-15s $%-9.0f %-15s\n", 
                    v.getId(), v.getName(), v.getBrand(), v.getPricePerDay(), v.getStatus()));
            System.out.println("--------------------------------------------------");
        }
    }

    private static void handleBookingProcess(Customer customer) {
        System.out.print("Nhap ID xe: ");
        Vehicle v = vehicleController.getVehicleById(getChoice());
        if (v == null) { System.out.println(">> Khong tim thay xe."); return; }
        try {
            System.out.print("Ngay di (yyyy-mm-dd): "); LocalDate s = LocalDate.parse(sc.nextLine());
            System.out.print("Ngay ve (yyyy-mm-dd): "); LocalDate e = LocalDate.parse(sc.nextLine());
            bookingController.createBooking(customer, v, s, e);
        } catch (Exception e) { System.out.println(">> Loi ngay thang."); }
    }

}




