#include <iostream>

class Person {
public:
    Person() { std::cout << "I'm created." << std::endl; }
    ~Person() {
        std::cout << "I'm melting." << std::endl;
        // remember, delete to pointers, destructors are useful for this
        delete bloodCells;
        // also, we dont have to null this pointer, as it is now inaccessible (obj destroyed)
    }
    // this pointers to the object, doesn't change anything in this case...
    void setAge(int) { this->age = in_age; }
    int getAge() { return age; }
    void Vomit() const { std::cout << "Puke is everywhere..." << std::endl; }
private:
    int age;
    int *bloodCells;
};

int main()
{
    int var1 = 4;
    std::cout << "Standard Variable: " << var1 << std::endl
              << "Address of that is: " << &var1 << std::endl;

    // make sure to init pointers to 0 or something, wild pointers are dangerous?
    int *pVar1 = &var1;
    std::cout << "Address of that through a pointer: " << pVar1 << std::endl;

    // deference '*' operator gets value from pointer
    int sameVar = *pVar1;
    std::cout << "New variable from deferenced pointer: " << sameVar << std::endl;

    // deference op can be used to change value
    *pVar1 = 100;
    std::cout << "New value for first: " << var1 << std::endl; 

    // new returns address on freestore, so it must be sent to a pointer
    int *pFsInt1 = new int;
    *pFsInt1 = 4444;
    std::cout << "This is from the free store: " << *pFsInt1 << std::endl
              << "And now its gone, deleted to avoid a memory leak!" << std::endl;
    // delete frees the fs data, this avoids a memory leak, but keeps the pointer
    delete pFsInt1;
    // this is however, now a stray/wild pointer, which points to a address which
    // could contain other information now that its freed, this is bad, set it
    // to a null pointer (points to nothing) like so...
    pFsInt1 = 0;

    // object created on free store
    Person *pMyself = new Person;

    // parentheses needed to deference the pointer before method call
    std::cout << "Age is: " << (*pMyself).getAge() << std::endl;
    (*pMyself).setAge(14);
    std::cout << "Age now is: " << (*pMyself).getAge() << std::endl;

    // this will run the deconstructor, which frees the memory pointed at
    delete pMyself;

    // here the value pointed to cant be changed (the int is consted)
    const int *noValCh; // *noValCh = 44; (INVALID) 
    // value pointed to can be changed, but cant point elsewhere (const after int)
    int *const *noP2Ch; // noP2Ch = &noValCh (INVALID);
    // nothing can be changed (const before int, and before name)
    const int *const *nothingCH;

    // a pointer to a const object makes it so that you can only call the const methods
    const Person *somebodyElse;
    (*somebodyElse).Vomit();
    // (*somebodyElse).setAge(8); (INVALID
    // (crazy pointer arithmetic skipped, see book)
    //c-strings

    std::cout << std::endl;
    return 0;
}
