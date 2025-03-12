```mermaid

classDiagram
class User {
-userID: int
-name: String
-email: String
-phoneNumber: String
}

    class LibraryAgent {
        -employeeID: int
        +enterNewDocument(Document)
        +collectFine(Borrower, double)
        +generateFineReport()
        +generateBorrowsReport()
    }

    class Borrower {
        -borrowerID: int
        -fineBalance: double
        +borrowDocument(Document)
        +returnDocument(Document)
        +payFine(double)
        +viewBorrowingHistory()
    }

    class Document {
        -documentID: int
        -title: String
        -status: String
        -dueDate: Date
        +checkAvailability()
        +updateStatus()
    }

    class Book {
        -ISBN: String
        -author: String
        -publisher: String
        -numberOfPages: int
    }

    class CD {
        -artist: String
        -duration: int
        -genre: String
    }

    class DVD {
        -director: String
        -duration: int
        -rating: String
    }

    class Fine {
        -fineID: int
        -amount: double
        -createdDate: Date
        -status: String
        +calculateAmount()
        +updateStatus()
    }

    class Borrow {
        -borrowID: int
        -borrowDate: Date
        -totalItems: int
        -status: String
        +getTotalItems()
    }

    class BorrowLineItem {
        -lineItemID: int
        -borrowDate: Date
        -dueDate: Date
        -returnDate: Date
        -status: String
        +isOverdue()
        +calculateFine()
        +updateStatus()
    }

    User <|-- LibraryAgent
    User <|-- Borrower
    Document <|-- Book
    Document <|-- CD
    Document <|-- DVD
    Borrower "1" -- "*" Borrow : has
    Borrow "1" -- "*" BorrowLineItem : contains
    BorrowLineItem "*" -- "1" Document : references
    Fine "*" -- "1" Borrower : has
    LibraryAgent "1" -- "*" Document : manages
```