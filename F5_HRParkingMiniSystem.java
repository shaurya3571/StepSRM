public class F5_HRParkingMiniSystem {
    static class Employee {
        private int empId;
        private String empName;
        private double salary;

        Employee(int empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(int empId, String empName, double salary,
                        double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {
        private double stipendCap;

        InternEmployee(int empId, String empName, double salary,
                       double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    static class ParkingSlot {
        private String slotNo;
        private int capacity;
        private int occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot(String vehicleNo) {
            if (occupiedCount < capacity) {
                occupiedCount++;
            }
        }

        static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
            for (ParkingSlot slot : slots) {
                if (slot != null && slot.occupiedCount < slot.capacity) {
                    return slot;
                }
            }
            return null;
        }

        static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
            ParkingSlot slot = findAvailableSlot(slots);
            if (slot != null) {
                slot.allot(vehicleNo);
            } else {
                System.out.println("No slots available for " + vehicleNo);
            }
        }

        String getSlotNo() {
            return slotNo;
        }
    }

    static class CompanyEmployeeRecord {
        String name;
        String empId;
        Employee employee;
        ParkingSlot slot;

        static int totalRecords = 0;

        CompanyEmployeeRecord(String name, String empId,
                              Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        String fullProfile() {
            double effectivePay;

            if (employee instanceof ManagerEmployee) {
                effectivePay =
                        ((ManagerEmployee) employee).effectiveSalary();
            } else if (employee instanceof InternEmployee) {
                effectivePay =
                        ((InternEmployee) employee).effectiveSalary();
            } else {
                effectivePay = employee.getSalary();
            }

            String slotInfo = slot != null
                    ? slot.getSlotNo()
                    : "no parking assigned";

            return name + " | Pay: Rs " + effectivePay
                    + " | Slot: " + slotInfo;
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] parkingSlots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        ManagerEmployee divya =
                new ManagerEmployee(101, "Divya", 70000, 8000);
        Employee karan =
                new Employee(102, "Karan", 40000);
        InternEmployee meera =
                new InternEmployee(103, "Meera", 12000, 10000);

        ParkingSlot slot1 = ParkingSlot.findAvailableSlot(parkingSlots);
        if (slot1 != null) {
            slot1.allot("TN09DV0001");
        }

        ParkingSlot slot2 = ParkingSlot.findAvailableSlot(parkingSlots);
        if (slot2 != null) {
            slot2.allot("TN09KR0002");
        }

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord("Divya", "E101", divya, slot1);
        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord("Karan", "E102", karan, slot2);
        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord("Meera", "E103", meera, null);

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());
        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}
