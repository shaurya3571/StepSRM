public class F3_ParkingSlot {
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
                System.out.println(vehicleNo + " allotted to slot " + slotNo);
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
            ParkingSlot availableSlot = findAvailableSlot(slots);

            if (availableSlot != null) {
                availableSlot.allot(vehicleNo);
            } else {
                System.out.println("No slots available for " + vehicleNo);
            }
        }

        String getSlotNo() {
            return slotNo;
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] availableSlots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        System.out.println("Slots: A1 (3/4), A2 (5/5)");
        ParkingSlot.safeAllot(availableSlots, "TN09AB1234");

        ParkingSlot[] fullSlots = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        System.out.println("Slots: A1 (4/4), A2 (5/5)");
        ParkingSlot.safeAllot(fullSlots, "TN09AB1234");

        /*
         * Passing the array passes a reference to the array, whose elements
         * are references to ParkingSlot objects. The ParkingSlot objects
         * themselves are not copied, so mutations affect the same objects.
         */
    }
}
