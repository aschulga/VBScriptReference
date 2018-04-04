namespace java com.alexshulga.thrift

struct Theme {
	1:i32 id
    2:string title
	3:string content
}

struct Author {
    1:string firstname
	2:string lastname
}

struct Book {
	1:i32 id
    2:Author author
	3:string title
	4:list<Theme> listThemes
}

service BookService {
	list<Book> getAllBooks()
	void addBook(1:Book book)
	void deleteBook(1:i32 index)
	void changeBook(1:i32 index, 2:Book book)
	list<Theme> getAllThemes(1:i32 index)
	void addTheme(1:Theme theme, 2:i32 indexInListBooks)
	void deleteTheme(1:i32 index, 2:i32 indexInListBooks)
	void changeTheme(1:i32 index, 2:Theme theme, 3:i32 indexInListBooks)
	void saveChanging()
}