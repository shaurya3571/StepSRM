public class F4_LibraryMember {
    // Broken version: these fields are shared by all members.
    // name is not static because each member has a different name.
    // memberId is not static because each member needs its own ID.
    // booksIssued is not static because each member has its own count.
    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    static class LibraryMember {
        private String name;
        private String memberId;
        private int booksIssued;

        private static String libraryName = "Central Library";
        private static int memberCount = 1000;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
            memberCount++;
            this.memberId = "LM-" + memberCount;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + (memberCount - 1000));
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);
        System.out.println("(Aditi's data was overwritten - both members now show Rohan)");

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember fixed1 = new LibraryMember("Aditi", 2);
        LibraryMember fixed2 = new LibraryMember("Rohan", 3);

        fixed1.printMemberCard();
        fixed2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
