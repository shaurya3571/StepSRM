public class F1_BookIssue {
    static class BookIssue {
        private String title;
        private String borrowerName;
        private int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        double fineAmount() {
            return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
        }

        boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        // Static because this calculates one total for an array of books;
        // fineAmount() belongs to one particular BookIssue object.
        static double totalFineCollected(BookIssue[] issues) {
            double total = 0.0;
            for (BookIssue issue : issues) {
                total += issue.fineAmount();
            }
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aditi", 18),
            new BookIssue("Effective Java", "Rohan", 5),
            new BookIssue("Refactoring", "Karan", 0),
            new BookIssue("DSA Handbook", "Meera", 21),
            new BookIssue("Design Patterns", "Arjun", 9)
        };

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue()
                    ? "Severely overdue" : "OK";
            System.out.println(issue.title + " - " + issue.daysOverdue
                    + " days - " + status);
        }

        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(issues));
    }
}
