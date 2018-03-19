namespace java com.alexshulga.thrift

struct Kind {
    1:string kind
}

struct Notation {
	1:i32 id
	2:string type
    3:Kind kind
    4:string subtype
    5:string description
}

service NotationService {
	list<Notation> getAllNotations()
	void addNotation(1: Notation notation)
	void deleteNotation(1: i32 index)
	void changeNotation(1: i32 index, 2: Notation notation)
	void saveChanging()
}