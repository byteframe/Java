// Var.cpp
#include<iostream>
using namespace std;
int main()
{
    int Var;
    int Var;
}

// String.cpp
int main()
{
    string Name;
    Name = "String";
    cout << Name;
}

// length.cpp
double convertLength(double);
int main()
{
    double input;
    for (int counter = 1; counter <= 3; counter++)
    {
        cout << "Enter the number of inches: ";
        cin >> input;
        cout << endl << input << " is " << convertLength(input) << " centimers." << endl;
    }
    cout << endl;
    system("pause");
    return 0;    
}
double convertLength(double input)
{
    return input * 2.54;
}

// problem_350.cpp
int mystery(int, int);
int main()
{
    int x;
    int y;
    cout << "Enter two integers: ";
    cin >> x >> y;
    cout << "The result is " << mystery(x,y) << endl;
    system("pause");
    return 0;
}
int mystery (int a, int b)
{
    if (b==1)
        return a;
    else 
        return a + mystery(a, b-1);
}

// array_subtraction_test.cpp
int main()
{
    unsigned short int intArray[] = {1,2,3,4,5,6,7,8,9,10};
    cout << intArray[10 - 7];
    cout << intArray[7-9];
    system("pause");
}

// Hw6Extra.cpp
int main()
{
    int Mover = 2;
    do
    {
        cout << Mover << endl; 
        Mover = Mover + 2;
    } while (Mover <= 40);
}

// WeirdVar.cpp
int main()
{
    int IntVar = 444;
    int Printing*IntVar = 10100010;
    cout << Printing444 << endl;
}

// page_226_problem_2
int main()
{
    int input;
    int sum = 0;
    int digit;
    cout << "Enter an interger ";
    cin >> input;
    while (input > 0)
    {
        cout << 
        input -= input * digit
        cout << " ";
    }
    for (int t = 0; int )
}

// add.cpp
int main()
{
    int Num1;
    int Num2;
    cout << "Enter the first number yo, \n";
    cin >> Num1;
    cout << "Enter the second number, damnit \n";
    cin >> Num2;
    cout << endl << endl << Num1 + Num2;
}

// while_loop.cpp
int main()
{
    system("clear");
    int Int;
    cout << "Int 1-500 :";
    cin >> Int;
    while (Int <= 0 || Int > 500)
    {
        cout << "Wrong\nInt :";
        cin >> Int;
    }
    cout << "You win";
}

// final_number.cpp
const string alp = "abcdefghijklmnopqrstuvwxyz";
int main() {
    for (int a = 0; a < 26; a++)
        for (int b = 0; b < 26; b++)
            for (int c = 0; c < 26; c++)
                cout << alp.at(a) << alp.at(b) << alp.at(c) << endl;
    system("pause");
}

// final_grade.c
#include <stdio.h>
main()
{
    system("clear");
    float paper;
    do
    {
        printf("Input the student's grade for the paper : ");
        scanf("%f", &paper);
    } while (paper < 0.0 || paper > 100.0); // no extra credit
    float homework;
    do
    {
        printf("Input the student's homework average : ");
        scanf("%f", &homework);
    } while (homework < 0.0 || homework > 100.0);
    float project;
    do
    {
        printf("Input the student's project grade : ");
        scanf("%f", &project);
    } while (project < 0.0 || project > 100.0);
    printf("Final Grade : %d \n", (int)( paper + homework + project) / 3);
}

// final_grade.cpp
int main()
{
    system("clear");
    float paper;
    do
    {
        std::cout << "Input the student's grade for the paper : ";
        std::cin >> paper;
    } while (paper < 0.0 || paper > 100.0); // no extra credit
    float homework;
    do
    {
        std::cout << "Input the student's homework average : ";
        std::cin >> homework;
    } while (homework < 0.0 || homework > 100.0);
    float project;
    do
    {
        std::cout << "Input the student's project grade : ";
        std::cin >> project;
    } while (project < 0.0 || project > 100.0);
    std::cout << "Final Grade : " << (int)(paper + homework + project) / 3 << std::endl; // not rounded
    return 0;
}

// functionPassing.cpp
double BigEquation(double FirstDouble, double SecondDouble, double ThirdDouble)
{
    return FirstDouble + SecondDouble + ThirdDouble;
}
int main()
{
    double FirstInput, SecondInput, ThirdInput;
    cout << "Input the first number: ";
    cin >> FirstInput;
    cout << "Input the second number: ";
    cin >> SecondInput;
    cout << "Input the third number: ";
    cin >> ThirdInput;
    cout << "The big equation returned " << BigEquation(FirstInput, SecondInput, ThirdInput);
    return 0;
}

// modulus.cpp
int main()
{
    int Input1;
    int Remain1;
    system("clear");
    cout << "Modulus even-odd testing!\n";
    cout << "-------------------------\n";
    cout << "Interger entered was: " ;
    cin >> Input1;
    Remain1 = Input1 % 2;
    cout << "The modulus operator returned: " << Remain1 << "\n";
    if (Remain1 == 0)
        cout << "So...the provided interger: " << Input1 << " is even.";
    else
        cout << "So...the provided interger: " << Input1 << " is even.";
    cout << "\n\n";
}

// VrMenu.cpp
int main()
{
    system("clear");
    cout << "---------------------------------------\n";
    cout << "vortexrikers.primarydataloop\n";
    cout << "---------------------------------------\n";
    cout << "\n";
    cout << "1. Quit and Clear the Screen\n";
    cout << "2. Open up an Internet Browser\n";
    cout << "3. Telnet to arbornet.org\n";
    cout << "4. Play Doom 2\n";
    cout << "5. Play Diablo 2\n\n";
    string Choice;
    cout << "Enter the number of your selection, and press return: \n";
    cin >> Choice;
    if ( Choice == "1" )
        system("clear");
    else if ( Choice == "2" )
        system("epiphany-bin");
    else if ( Choice == "3" )
        system("telnet arbornet.org");
    else if ( Choice == "4" )
        system("doom2");
    else if ( Choice == "5" )
        system("diablo2");
    else
        cout << "You entered and invalid selection.\n\n";
}

// array.cpp
int main()
{
    int Array0[3];
    Array0[0] = 1;
    Array0[1] = 9;
    cout << Array0[0] + Array0[1] << endl;
    int Array1[10] = {1,2,3,4,5,6,7,8,9,10};
    cout << Array1[0];
    cout << Array1[1];
    cout << Array1[2];
    cout << Array1[3];
    cout << Array1[4];
    cout << Array1[5];
    cout << Array1[6];
    cout << Array1[7];
    cout << Array1[8];
    cout << Array1[9] << endl;
    string Array2[10]; 
    Array2[10] = {one, two, three, four, five, six, seven, eight, nine, ten};
    cout << Array2[0] << endl;
    cout << Array2[1] << endl;
    cout << Array2[2] << endl;
    cout << Array2[3] << endl;
    cout << Array2[4] << endl;
    cout << Array2[5] << endl;
    cout << Array2[6] << endl;
    cout << Array2[7] << endl;
    cout << Array2[8] << endl;
    cout << Array2[9] << endl;
}

// Hw402.cpp
int main()
{
    float Sales = 1.0;
    while (Sales > -1)
    {
        cout << "Enter the sales in dollars (-1 to end): ";
        cin >> Sales;
        if (Sales > -1)
        { 
            cout << "Salary is: $" << 200.00 + (Sales * .09) << endl << endl;
        }
    }
}

// midterm.cpp
int main()
{
    int i, number;
    for (number = 1; number <= 5; number++)
    {
        cin >> i;
        if (i == 0)
            cout << i << " is zero\n";
        else if (i > 0)
            cout << i << " is positive\n";
        else if (i < 0)
            cout << i << " is negative\n";
    }
    system("PAUSE");
    return 0;
}

// page_164_problem_240
int main()
{
    int Input = 0;
    int Sum = 0;
    cout << "Enter number of values to add up to :";
    cin >> Input;
    cout << endl;
    for (int Counter = 1; Counter <= Input; Counter++)
    {
        Sum = Sum + 100;
        cout << Sum << " ";  
    }
    cout << endl << endl;
}

// asterisks.cpp
void printAsterisks(unsigned int);
int main()
{
    unsigned int input;
    cout << "Enter the ammount of asterisks to print: ";
    cin >>input;
    printAsterisks(input);
    cout << endl;
    system("pause");
    return 0;
}
void printAsterisks(unsigned int input)
{
    for (int counter = 1; counter <= input; counter++)
    {
        cout << "*";
    }
}

// hw123.cpp
int main()
{
    int Num1;
    int Num2;
    cout << "Enter the first Interger : ";
    cin >> Num1;
    cout << endl;
    cout << "Enter the second Interger : ";
    cin >> Num2;
    cout << endl;
    cout << "Sum : " << Num1 + Num2 << endl;
    cout << "Product : " << Num1 * Num2 << endl;
    cout << "Difference " << Num1 - Num2 << endl;
    cout << "Quotient : " << Num1 / Num2 << endl;
}

// average.cpp
double computeAverage(double,double,double,double,double);
int main()
{
    double num1,num2,num3,num4,num5;
    cout << "Input five numbers, each followed by hitting [ENTER]:" << endl;
    cin >> num1 >> num2 >> num3 >> num4 >> num5;
    cout << "The average is: " << computeAverage(num1,num2,num3,num4,num5) << endl;
    cout << endl;
    system("pause");
    return 0;    
}
double computeAverage(double num1, double num2, double num3, double num4, double num5)
{
    return (num1 + num2 + num3 + num4 + num5) / 5;
}

// distance.cpp
#include <cmath>
const double Pie = 3.141592654;
int main()
{
    double X1,X2,Y1,Y2,Radius,Circum,Area;
    system("clear");
    cout << "Enter the first X Coordinate: ";
    cin >> X1;
    cout << "Enter the first Y Coordinate: ";
    cin >> Y1;
    cout << "Enter the second X Coordinate: ";
    cin >> X2;
    cout << "Enter the second Y Coordinate: ";
    cin >> Y2;
    Radius = sqrt(pow((X2 - X1),2) + pow((Y2 - Y1),2));
    Circum = 2 * Pie * Radius;
    Area = Pie * pow(Radius,2);
    cout << "\nThe Radius is " << Radius;
    cout << "\nThe Circumfurence is " << Circum;
    cout << "\nThe Area is " << Area << endl << endl;
}

// hw319.cpp
#include <cmath>
double Hypotenuse(double Side1, double Side2)
{
    return sqrt(pow(Side1,2) + pow(Side2,2));
}
int main()
{
    system("clear");
    int Counter = 0;
    double InSide1;
    double InSide2;
    while (Counter != 3)
    {
        cout << "Enter the length of side A: ";
        cin >> InSide1;
        cout << "Enter the length of side B: ";
        cin >> InSide2;
        cout << "The hypotenuse is: " << Hypotenuse(InSide1, InSide2) << endl << endl;    
        Counter++;
    }
    return 0;
}

// hw319.cpp
#include <cmath>
double Hypotenuse(double Side1, double Side2)
{
    return sqrt(pow(Side1,2) + pow(Side2,2));
}
int main()
{
    system("clear");
    int Counter = 0;
    double InSide1;
    double InSide2;
    while (Counter != 3)
    {
        cout << "Enter the length of side A: ";
        cin >> InSide1;
        cout << "Enter the length of side B: ";
        cin >> InSide2;
        cout << "The hypotenuse is: " << Hypotenuse(InSide1, InSide2) << endl << endl;    
        Counter++;
    }
    return 0;
}

// Hw126.cpp
int main()
{
  int Num1;
  int Num2;
  int Num3;
  cout << "Input three different intergers : \n\n";
  cin >> Num1;
  cin >> Num2;
  cin >> Num3;
  cout << "\nSum is " << Num1 + Num2 + Num3 << endl;
  cout << "Average is " << (Num1 + Num2 + Num3) / 3 << endl ;
  cout << "Product is " << Num1 * Num2 * Num3 << endl;
  if (Num1 > Num2 && Num1 > Num3)
  {
      cout << "Largest is " << Num1 << endl;
  }
  else if (Num2 > Num1 && Num2 > Num3)
  {
      cout << "Largest is " << Num2 << endl;
  }
  else if (Num3 > Num1 && Num3 > Num2)
  {
      cout << "Largest is " << Num3 << endl;
  }
  if (Num1 < Num2 && Num1 < Num3)
  {
      cout << "Smallest is " << Num1 << endl;
  }
  else if (Num2 < Num1 && Num2 < Num3)
  {
      cout << "Smallestt is " << Num2 << endl;
  }
  else if (Num3 < Num1 && Num3 < Num2)
  {
      cout << "Smallest is " << Num3 << endl;
  }
}

// template.cpp
template <class myType>
myType AddTwo(myType a, myType b) { return a + b; }
template <class myType>
myType MultiplyTwo(myType a, myType b) { return a * b; }
int main() {
    system("clear");
    int inputInt;
    double inputDouble;
    std::cout << "I want an Int : ";
    std::cin >> inputInt;
    std::cout << "Give me a Double : ";
    std::cin >> inputDouble;
    std::cout << "\n    Sum is : " << AddTwo<double>(inputDouble, inputInt)
              << "\n    Product is : " << MultiplyTwo<double>(inputDouble, inputInt) << "\n"
              << std::endl;
    system("pause");
    return 0;
}

// words.cpp
#include <string>
int FindWords(string);
int main()
{
    string input;    
    do
    {
        cout << "Input a string : ";
        cin >> input;
    } while (input.empty());
    cout << "Number of words : " << FindWords(input);
    system("pause");
}
int FindWords(string working)
{
    bool found = false;
    short words = 0, pos = 0;
    do
    {
        while (working.substr(pos, 1) == ' ')
            pos++;
        while (working.substr(pos, 1) != ' ')
        {
            pos++;
            found = true;
        }
        if (found)
        {
            words++;
            found = false;
        }
    } while (pos != working.length());
    return words;
}

// make_change.cpp
const int Halfdollar = 50;
const int Quarter = 25;
const int Dimes = 10;
const int Nickel = 5;
int main()
{
    int change;
    cout << "enter change in cents: ";
    cin >> change;
    cout << "the change you entered is " << change
         << endl;
    cout << "The number of halfdollars to be returned "
         << "is " << change / Halfdollar << endl;
    change = change % Halfdollar;
    cout << " The number of quarters to be returned is "
         << change / Quarter << endl;
    change = change % Quarter;
    cout << " The number of Dimes to be returned is "
         << change / Dimes << endl;
    change = change % Dimes;
    cout << " The number of Nickels to be returned is "
         << change / Nickel<< endl;
    change = change % Nickel;
    cout << "The number of pennies to be returned is "
         << change << endl; 
    system ("pause");
    return 0;
}

// biggest_thing.cpp
int findLargest(int, int);
int swapNumber(int, int);
int main()
{
    int a, b;
    cout << "Enter three intergers: ";
    cin >> a >> b;
    findLargest(a, b);
    cout << "The largest number is: " << a << endl;
    cout << "The smallest number is: " << b << endl;

    return 0;
}
int findLargest(int &a, int &b)
{
    if (a < b)
    {
        swapNumber(a, b);
    }
}
void swapNumber(int &a, int &b)
{
    int temp;
    temp = a;
    a = b;
    b = temp;    
}

// Hw325.cpp
int quotient(int a, int b)
{
    return a / b;
}
int remainder(int a, int b)
{
    return a % b;
}
void worker(int choice)
{
    if (choice >= 10000)
    {
        cout << quotient(choice, 10000) << "  ";
        choice = remainder(choice, 10000);
    }
    if (choice >= 1000)
    {
        cout << quotient(choice, 1000) << "  ";
        choice = remainder(choice, 1000);
    }
    if (choice >= 100)
    {
        cout << quotient(choice, 100) << "  ";
        choice = remainder(choice, 100);
    }
    if (choice >= 10)
    {
        cout << quotient(choice, 10) << "  ";
        choice = remainder(choice, 10);
    }
    if (choice >= 1)
    {
        cout << quotient(choice, 1) << "  ";
        choice = remainder(choice, 1);
    }
}
int main()
{
    system("cls");
    int selection;    
    cout << "Enter and interger between 1-32767: ";
    cin >> selection;
    worker(selection);
    cout << "\n\n";
}

// prob415.cpp
int main()
{
    int input;
    int numbers[20] = {0};
    system("clear");
    for (int counter = 0; counter < 20; counter++)
    {
        input = 0;
        cout << "Input Number (10-100):";
        while (input < 10 || input > 100)
        {
            cin >> input;
        }
        if (numbers[0]==input||numbers[1]==input||numbers[2]==input||numbers[3]==input||numbers[4]==input||numbers[5]==input||
            numbers[6]==input||numbers[7]==input||numbers[8]==input||numbers[9]==input||numbers[10]==input||
            numbers[11]==input||numbers[12]==input||numbers[13]==input||numbers[14]==input||numbers[15]==input||
            numbers[16]==input||numbers[17]==input||numbers[18]==input||numbers[19]==input)
        {
            numbers[counter];
            cout << "That number was already entered." << endl << endl;
        }
        else
        {
            numbers[counter] = input;
            cout << input << " was entered." << endl << endl;
        }
    } 
    system("pause");
    return 0;
}

// Hw401.cpp
int main()
{
    float Gallons = 1.0;
    float Miles;
    float Counter = 0.0;
    float Overall;
    float Averages = 0.0;    
    while (Gallons > -1)
    {
        cout << "Enter the gallons used (-1 to end): ";
        cin >> Gallons;
        if (Gallons > -1)
        {
            cout << "Enter the miles driven: ";
            cin >> Miles;
            Overall = Miles / Gallons;
            cout << "The miles / gallon for this tank was " << Overall << endl << endl;
            Averages = Averages + Overall;
            Counter = Counter + 1.0;
        }
        else
        {
            Gallons = -4444.4444;
        }
   }
   cout << "\nThe overall average miles/gallon was " << Averages / Counter << endl << endl;
}

// io_tests.cpp
int main()
{
    int varInt1;
    int varInt2;
    int varIntTotal;
    std::cout << "==============================================================\n";
    std::cout << "1. varInt1 is...\n";
    std::cin >> varInt1;
    std::cout << "\n2. varInt2 is...\n";
    std::cin >> varInt2;
    std::cout << "\nAdding...\n";
    varIntTotal = varInt1 + varInt2;
    std::cout << "\nTotal Interger is ...\n";
    std::cout <<  varIntTotal << "\n";
    if ( varIntTotal >= 99 )
        std::cout << "\nYour number is more than 100...
    else 
        std::cout << "\nSo...\nYour number is not more than 100...";
    std::cout << "\n============================================================";
    std::cout << "\n\n-Done-\n";
    return 0;
}

// timeConversion.cpp
int TC(int);
char ap(int);
int main()
{
    int hours = -2;
    system("cls");    
    while (hours < -1 || hours > 23)
    {
        cout << "Please input the hours in military time (-1 to quit): ";
        cin >> hours;
        if (hours < -1 || hours > 23)
        {
            cout << "That number is not valid." << endl << endl;
        }
    }
    if (hours > -1 && hours < 23)
    {
        cout << hours << " hours is " << TC(hours) << ":00 "<< ap(hours) << endl;        
    }
    system("pause");
    return 0;   
}
int TC(int hours)
{
    if (hours == 0)
    {
        return 12;
    }
    else if (hours < 12)  
    {
        return hours;
    }
    else if (hours == 12)
    {
        return 12;
    }
    else if (hours > 12)
    {
        return hours -12;
    }
}
char ap(int hours)
{
    if (hours < 12)
    {
        return 'A';
    }
    else
    {
        return 'P';
    }
}

// lab1bRevised.cpp
int main()
{
    int code;
    int errorCt = 0;
    int sum = 0;
    char response;
    do
    {
        cout << "Enter an integer value: ";
        cin >> code;
        switch(code)
        {
          case 10:
              cout << "Small" << endl;  
              break;
          case 20:
          case 25:
          case 30:
              cout << "Medium" << endl;
              sum = sum + code;
              break;
          case 41:
              cout << "Large" << endl;
              break;
          default:
              cout << "Error\n";
              errorCt++;
        }
        cout << "Do you want to continue (y/n) ?";
        cin >> response;
    } while (response == 'y');
    cout << "\nSum  of medium codes is " << sum << endl;
    cout << "Error count is " << errorCt << endl;
    cout << endl;
    system("pause");
    return 0;
}

// lab04A.cpp
const int SIZE = 10;
void Fill(int[], int);
int Sum50s(const int[], int);
void PrintReverse(const int[], int);
int main()
{
    int numAr[SIZE];
    int sumOf50s;
    Fill(numAr, SIZE);
    sumOf50s = Sum50s(numAr, SIZE);
    cout << endl << endl << "Sum of values in the 50's is " << sumOf50s << endl << endl << endl;
    PrintReverse(numAr, SIZE);
    cout << endl;
    system("pause");
    return 0;
}
void Fill(int numAr[], const int SIZE)
{
    for (int counter = 0; counter < SIZE; counter++)
    {
        cout << "Enter an interger #" << counter + 1 << " : ";
        cin >> numAr[counter];
    }
}
int Sum50s(const int numAr[], int SIZE)
{
    int sum = 0;
    for (int counter = 0; counter < SIZE; counter++)
    {
        if (numAr[counter] > 49 && numAr[counter] < 60)
        {
            sum = sum + numAr[counter];
        }
    }
    return sum;
}
void PrintReverse(const int numAr[], int SIZE)
{
    for (int counter = SIZE; counter > 0; counter--)
    {
        cout << "Interger #" << counter << " has the value: " << numAr[counter - 1] << endl;
    }
}

// ShellDr.cpp
#include "distanceclass.h"
void PrintDistance(DistanceClass);
int main()
{
    DistanceClass dist1, dist2, dist3, dist4;
    int milesIn, yardsIn, feetIn;
    system("clear");
    PrintDistance(dist1);
    cout << endl;
    cout << "Enter a length in yards to convert: ";
    cin >> yardsIn;
    dist2.ConvertYards(yardsIn);
    PrintDistance(dist2);
    cout << endl;
    cout << "Enter a length in feet to convert:  ";
    cin >> feetIn;
        dist2.ConvertFeet(feetIn);
    PrintDistance(dist3);
    cout << endl << "Enter a length in miles, yards and feet \n";
    cin >> milesIn >> yardsIn >> feetIn;
    dist1.Set(milesIn, yardsIn, feetIn);
    cout << endl << "Enter a 2nd length in miles, yards and feet \n";
    cin >> milesIn >> yardsIn >> feetIn;
    dist2.Set(milesIn, yardsIn, feetIn);
    dist4 = dist1.AddDistance(dist2);
    cout << "Sum is:\n";
    PrintDistance(dist4);
      cout << endl << endl;
    system("pause");
    return 0;
}
void PrintDistance(DistanceClass distance)
{    
    cout << distance.MilesAre() << " miles  " << distance.YardsAre() << " yards  " 
         << distance.FeetAre() << " feet" << endl << endl;
}

// DistanceClass interface.
class DistanceClass
{
    public:
        DistanceClass();
        int FeetAre() const;
    int YardsAre() const;
    int MilesAre() const;
    void Set(int, int, int);
    void ConvertYards(int);
    void ConvertFeet(int);    
    DistanceClass AddDistance(DistanceClass) const;
    private:
    int miles;
    int yards;
    int feet;
};

// DistanceClass.cpp
#include "distanceclasss.h"
DistanceClass::DistanceClass()
{
    miles,yards,feet = 0;
}
int  DistanceClass::FeetAre() const
{
    return feet;
}
int  DistanceClass::YardsAre() const
{
    return yards;
}
int  DistanceClass::MilesAre() const
{
    return miles;
}
void DistanceClass::Set(int mi, int yds, int ft)
{ 
    miles = mi;
    yards = yds;
    feet = ft;
}
void DistanceClass::ConvertYards(int conYards) 
{
    miles = conYards / 1760;
    yards = conYards % 1760;
}
void DistanceClass::ConvertFeet(int conFeet)
{
    miles = conFeet / 5180;
    yards = (conFeet % 5180) / 3;
    feet = conFeet * 3;                        
}
DistanceClass DistanceClass::AddDistance(DistanceClass  distance1) const  
{ 
    DistanceClass toReturn;
    int feetHold = feet + (yards * 3) + (miles * 5280) +
                   distance1.feet + (distance1.yards * 3) + (distance1.miles * 5280);
    toReturn.ConvertFeet(feetHold);  
    return toReturn;
}

// Complex.h
class Complex
{
    public:
        Complex(double = 0, double = 0);
        Complex Add(Complex);
    Complex Sub(Complex);
        void Print(Complex);
    private:
        double real;
    double imag;
};

// Complex.cpp
#include "Complex.h"
Complex::Complex(double r, double i)
{
    real = r;
    imag = i;
}
Complex Complex::Add(Complex toAdd)
{
    Complex sum;
    sum.real += real + toAdd.real;
    sum.imag += imag + toAdd.imag;
    return sum;
}
Complex Complex::Sub(Complex toSub)
{
    Complex difference;
    difference.real -= real - toSub.real;
    difference.imag -= imag - toSub.imag;
    return difference;
}
void Complex::Print(Complex toPrint)
{
    std::cout << "(" << toPrint.real << " + " << toPrint.imag << "i)";
}

// Problem66.cpp
#include "Complex.h"
int main()
{
    system("clear");
    double temp1, temp2;
    cout << "Input the real, and then the imaginary parts of the first complex number...\n";
    cin >> temp1;
    cin >> temp2;
    Complex c1(temp1, temp2);
    cout << "Input the real, and then the imaginary parts of the second complex number.\n";
    cin >> temp1;
    cin >> temp2;
    Complex c2(temp1, temp2);
    short choice;
    do
    {
        system("clear");
        cout << "Would operation would you like to perform?\n"
             << "------------------------------------------\n"
             << "1. Show the addition both complex numbers\n"
             << "2. Show the subtraction of the second from the first\n"
             << "3. Show the subtraction of the first from the second\n"
             << "          -or-\n"              
             << "4. Quit this nonsense\n"
             << "------------------------------------------\n"
             << "Input: ";
        cin >> choice;
        switch(choice)
        {
            default:
                break;
            case 1:
                c1.Print(c1.Add(c2));
            break;
            case 2:
                c2.Print(c2.Sub(c1));
            break;
            case 3:
                c1.Print(c1.Sub(c2));
            break;
            case 4:
                break;
        }
    } while (choice < 1 || choice > 5);
    cout << endl;
    system("pause");
    return 0;    
}

// fig04_16.cpp
#include <iomanip>
int main()
{
    const int arraySize = 10;
    int a[arraySize] = {2, 6, 4, 8, 10, 12, 89, 68, 45, 37};
    int l = -1;
    int hold;
    bool anyMade = false;
    cout << "Data items in original order\n";
    for (int i = 0; i < arraySize; i++)
    {
        cout << setw(4) << a[i];
    }
    for (int pass = 0; pass < arraySize - 1; pass++) 
    {
        // sets J at sort of at an offset from 0, at ever pass
        l++;
        for (int j = l; j < arraySize - 1; j++)      
        {
            if (a[j] > a[j + 1]) 
            {
                hold = a[j];
                a[j] = a[j + 1];
                a[j + 1] = hold;
                anyMade = true;
            }
        }
        if (!anyMade)
        {
            pass = 10;
        }
        anyMade = false;
    }
    cout << "\nData items in ascending order\n";
    for (int k = 0; k < arraySize; k++)
    {
        cout << setw(4) << a[k];
    }
    system("pause");
    cout << endl;
    return 0;
}

// 256.cpp
#include <iomanip>
int main()
{
    system("cls");
    int PayCode;
    cout << "Enter employee paycode number: ";
    cin >> PayCode;
    cout << "\n-------------------------------";
    cout << "\nPayment for ";
    switch (PayCode)
    {
        case 1:
            cout << "manager position\n" << "-------------------------------\n";
            cout << "Weekly salary awarded: " << "$" << 200000; 
            break;
        case 2:
            int HoursWorked;
            float WorkerAwarded;
            cout << "hourly employee\n" << "-------------------------------\n";
            cout << "Enter the hours worked: ";
            cin >> HoursWorked;
            if (HoursWorked > 40)
            {
                HoursWorked = HoursWorked % 40;
                WorkerAwarded = (40 * 6.75) + (HoursWorked * 10.125);               
                cout << "\nHourly Salary awarded: " << "$" << setprecision (2) << fixed << WorkerAwarded;
            }
            else
            {
                WorkerAwarded = HoursWorked * 6.75;
                cout << "\nHourly Salary awarded: " << "$" << setprecision (2) << fixed << WorkerAwarded;
            }
            break;
        case 3:
            float Gross;
            cout << "commision worker\n" << "-------------------------------\n";
            cout << "Enter the gross weekly sales: $";
            cin >> Gross;
            cout << "\nCommision based salary awarded: " << "$" << setprecision (2) << fixed << 250 + (Gross * .057);
            break;
        case 4:
            int NumWidgets;
            cout << "pieceworker\n" << "-------------------------------\n";
            cout << "Enter the widgets assembled: ";
            cin >> NumWidgets;
            cout << "\nPieceworker salary awarded: " << "$" << setprecision (2) << fixed << NumWidgets * .5;
            break;
        default:
            cout << "Get out of our payroll system...";
            system("sol");
    }
    cout << "\n\n\n\n";
}

// page_227_problem_8
int main()
{
    int firstNum;
    int secondNum;
    cout << "first num : ";
    cin >> firstNum;
    cout << "second num : ";
    cin >> secondNum;
    int oddcounter = secondNum - firstNum;
    while (oddcounter != secondNum)
    {
        if (oddcounter % 2 != 0)
        {
            cout << oddcounter << endl;
        }
        oddcounter++;
    }
    system("pause");
    int evencounter = secondNum - firstNum;
    while (evencounter != secondNum)
    {
        if (evencounter % 2 == 0)
        {
            cout << evencounter << endl;
        }
        evencounter++;
    }
    system("pause");
    int squareCounter = 1;
    while (squareCounter != 10)
    {
        cout << squareCounter * squareCounter << endl;
        squareCounter++;
    }
    system("pause");
    int sum = 0;
    int sumCounter = secondNum - firstNum;
    while (sumCounter != secondNum)
    {
        if (sumCounter % 2 != 0)
        {
            sum += sumCounter * sumCounter;
        }
        sumCounter++;
    }
    cout << sum << endl << endl;
    system("pause");
    string alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int aCounter = 0;   
    while (aCounter != 26)
    {
        cout << alphabet[aCounter] << endl;
        aCounter++;
    }
}

// lab1dRevised.cpp
int GetValues(int&, int&, int&, int&);
int Largest(int, int, int, int);
void PrintResults(int, int, int, int, int);
int main()
{
    int value1, value2, value3, value4;
    GetValues(value1, value2, value3, value4);
    int biggest = Largest(value1, value2, value3, value4);
    PrintResults(value1, value2, value3, value4, biggest);

    cout << endl;
    system("pause");
    return 0;
}
int GetValues(int &value1, int &value2, int &value3, int &value4)
{
    cout << "Input the first value: ";
    cin >> value1;
    cout << "Input the second value: ";
    cin >> value2;
    cout << "Input the third value: ";
    cin >> value3;
    cout << "Input the fourth value: ";
    cin >> value4;
}
int Largest(int lNum1, int lNum2, int lNum3, int lNum4)
{
    if (lNum1 > lNum2 && lNum1 > lNum3 && lNum1 > lNum4)
    {
        return lNum1;
    }
    else if (lNum2 > lNum1 && lNum2 > lNum3 && lNum2 > lNum4)
    {
        return lNum2;    
    }    
    else if (lNum3 > lNum1 && lNum3 > lNum2 && lNum3 > lNum4)
    {
        return lNum3;    
    }   
    else if (lNum4 > lNum1 && lNum4 > lNum2 && lNum4 > lNum3)
    {
        return lNum4;    
    }     
}
void PrintResults(int pNum1, int pNum2, int pNum3, int pNum4, int pGreatest)
{
    cout << endl;
    cout << pGreatest << " was the largest number you entered." << endl << endl;
    cout << pNum1 << " was the first number you entered." << endl;
    cout << pNum2 << " was the second number you entered." << endl;
    cout << pNum3 << " was the third number you entered." << endl;
    cout << pNum4 << " was the fourth number you entered." << endl;
}

// lab04B.cpp
const int SIZE = 20;
void Fill(int[], int&);
int SumEvens(const int [], int);
void Search(const int[], int, int, bool&);
void Print(const int[], int);
int main()
{
    int numList[SIZE];
    int numVals;
    int sumEvens;
    int val;
    bool found;
    Fill(numList, numVals);
    sumEvens = SumEvens(numList, numVals);
    cout << endl <<"Sum of even values is " << sumEvens << endl << endl;
    cout << "Enter a value to search for in the list: ";
    cin >> val;
    Search(numList, numVals, val, found);
    if (found)
    {
        cout << val << " was indeed found in the list.\n\n\n";
    }
    else
    {
        cout << val << " was not found in the list.\n\n\n";
    }
    Print(numList, numVals);
    cout << endl;
    system("pause");
    return 0;
}
void Fill(int numList[],int &numVals)
{
    //Check to make sure the number of values is within 1-SIZE
    do
    { 
        cout << "First, enter the number of values you'd like to input (1-20): ";
        cin >> numVals;
    } while (numVals < 1 || numVals > 20);
    cout << endl << endl;
    for (int counter = 0; counter < numVals; counter++)
    {
        cout << "Enter interger #" << counter + 1 << " : ";
        cin >> numList[counter];
    }
}
int SumEvens(const int numList[], int numVals)
{
    int sum = 0;
    for (int counter = 0; counter < numVals; counter++)
    {
          if (numList[counter] % 2 == 0)
          {
              sum = sum + numList[counter]; 
          }
    }
    return sum;
}
void Search(const int numList[], int numVals, int val, bool &found)
{
    for (int counter = 0; counter < numVals; counter++)
    {
        if (numList[counter] == val)
        {
            found = true;
        }
        else
        {
            found = false;
        }
    }
}
void Print(const int numList[], int numVals)
{
    for (int counter = 0; counter < numVals; counter++)
    {
        cout << "The element #" << counter + 1 << " holds the value: " << numList[counter] << endl;
    }
}

// banker.cpp
int main()
{
    int account;
    char accountType;
    float minBal, curBal;
    cout << "Enter Account Number : ";
    cin >> account;
    cout << "Enter Account Type : ";
    cin >> accountType;
    cout << "Enter Minimum Balance : ";
    cin >> minBal;
    cout << "Enter Current Balance : ";
    cin >> curBal;
    if (curBal < minBal)
    {
        if (accountType == 'c') 
        {
            curBal -= 25.00;
        }
        else if (accountType == 's') 
        {
            curBal -= 10.00;
        }
    }
    else
    {
        if (accountType == 's')
        {
            curBal += curBal * .04;
        }
        if (accountType == 'c')
        {
            if (curBal < 5000 + minBal)
            {
                curBal +=  curBal * .03;
            }
            else
            {
                curBal += curBal * .05;
            }
        }
    }
    cout << "Account Number :" << account << endl;
    cout << "Account Type :" << accountType << endl;
    cout << "Current Balance :" << curBal << endl << endl;
    if (curBal < minBal)
    {
        if (accountType == 'c') 
        {
            cout << "You lost 25 dollars";
        }
        else if (accountType == 's') 
        {
            cout << "You lost 10 dollars";
        }
    }
    else
    {
        if (accountType == 's')
        {
            cout << "You've gained 4% interest...";
        }
        else if (accountType == 'c')
        {
             if (curBal < 5000 + minBal)
             {
                 cout << "You've gained 3% interest...";
             }
             else
             {
                cout << "You've gained 5% interest...";
             }
        }
    }
    cout << endl << endl; 
    system("pause");
    return 0;
}

// ttyCards.cpp
#include <string>
#include <iomanip>
#include <fstream>
void GameBlackjack();
void GameFiveCardDraw();
void GameThirtyOne();
void GameHighCard();
void LoadDeck(char);
int main()
{
    system("clear");
    int gameChoice;
    do
    {
      cout << "Which card game would you like to play?" << endl;
      cout << "---------------------------------------" << endl;
      cout << "1. Blackjack" << endl;
      cout << "2. Five Card Draw" << endl;
      cout << "3. Thirty-One" << endl;
      cout << "4. High Card" << endl;
      cout << endl;
      
      cout << "Enter your selection: ";
      cin >> gameChoice;
      system("clear");
    } while (gameChoice < 1 || gameChoice > 4);
    switch (gamechoice)
    {
      default:
        system("clear");
        cout << "Error.";
      case 1:
        GameBlackjack();
        break;
      case 2:
        GameFiveCardDraw();
        break;
      case 3:
        GameThirtyOne();
        break;
      case 4:
        GameHighCard();
        break;
    }
    cout << endl;
    return 0;
}
void LoadDeck(char type);
{
    ifstream deckFile;
    deckFile.open("standard.deck");
    int theDeck[][];
}

// fig04_16.cpp
#include <iomanip>
int main()
{
    const int arraySize = 10;
    int a[arraySize] = {2, 6, 4, 8, 10, 12, 89, 68, 45, 37};
    int l = -1;
    int hold;
    bool anyMade = false;
    int comparisons = 0;
    cout << "Data items in original order\n";
    for (int i = 0; i < arraySize; i++)
    {
        cout << setw(4) << a[i];
    }
    cout << endl << endl;
    for (int pass = 0; pass < arraySize - 1; pass++) 
    {
    l++;
      for (int j = l; j < arraySize - 1; j++)      
      {
          if (a[j] > a[j + 1]) 
          {
              hold = a[j];
              a[j] = a[j + 1];
              a[j + 1] = hold;
              anyMade = true;
              comparisons++;
          }
      }
      cout << "After pass " << pass << ": ";
      for (int after = 0; after < arraySize; after++)
      {
          cout << setw(4) << a[after];
   }
   cout << endl;
      if (!anyMade)
      {
          pass = 10;
      }
      anyMade = false;
    }
    cout << "\nData items in ascending order\n";
    for (int k = 0; k < arraySize; k++)
    {
        cout << setw(4) << a[k];
    }
    cout << endl << endl <<"Number of comparisons = " << comparisons;
    cout << endl << endl;
    return 0;
}

// change_maker.cpp
int main()
{
    system("clear");
    int Num100bills;
    int Num50bills;
    int Num20bills;
    int Num10bills;
    int Num5bills;
    int Num1bills;
    int NumQuarters;
    int NumDimes;
    int NumNickels;
    int NumPennies;
    int NumOrigPennies;
    cout << "Welcome to the change maker!\n";
    cout << "--------------------------\n";
    cout << "You enter the ammount of pennies\n";
    cout << "and the change maker will make change.\n\n";
    cout << "Please enter the ammount of pennies :";
    cin >> NumPennies;
    cout << "-------------------------------\n";
    if (NumPennies <= 0)
    {
        cout << "You entered an invalid ammount of pennies.\n\n";
    }
    else if (NumPennies == 1)
    {
        cout << "You've only 1 penny.\n\n";
    }
    else
    {
      NumOrigPennies = NumPennies;
      Num100bills = NumPennies / 10000;
        if (Num100bills > 0)
        {
            cout << "Franklins : " << Num100bills << "\n";
            NumPennies = NumPennies % 10000;         
        }
        Num50bills = NumPennies / 5000;
        if (Num50bills > 0)
        {
            cout << "Grants : " << Num50bills << "\n";
            NumPennies = NumPennies % 5000;
        }
        Num20bills = NumPennies / 2000;
        if (Num20bills > 0)
        {
            cout << "Jacksons : " << Num20bills << "\n";
            NumPennies = NumPennies % 2000;
        }
        Num10bills = NumPennies / 1000;
        if (Num10bills > 0)
        {
            cout << "Hamiltons : " << Num10bills << "\n";
            NumPennies = NumPennies % 1000;
        }
        Num5bills = NumPennies / 500;
        if (Num5bills > 0)
        {   
            cout << "Lincolns : " << Num5bills << "\n";
            NumPennies = NumPennies % 500;
        }
        Num1bills = NumPennies / 100;
        if (Num1bills > 0)
        {
            cout << "Washingtons : " << Num1bills << "\n";
            NumPennies = NumPennies % 100;
        }   
        NumQuarters = NumPennies / 25;
        if (NumQuarters > 0)
        {
            cout << "Quarters : " << NumQuarters << "\n";
            NumPennies = NumPennies % 25;
        }
        NumDimes = NumPennies / 10;
        if (NumDimes > 0)
        {
            cout << "Dimes : " << NumDimes << "\n";
            NumPennies = NumPennies % 10;
        }
        NumNickels = NumPennies / 5;
        if (NumNickels > 0)
        {   
            cout << "Nickels : " << NumNickels << "\n";
            NumPennies = NumPennies % 5;
        }
        if (NumPennies > 0)
        {    
            cout << "Pennies : " << NumPennies << "\n";
        }
        cout << "\nOr in other words...\n";
        cout << NumOrigPennies / 100 << " dollars and " << NumOrigPennies % 100 << " cent(s).\n\n"; 
    }
}

// prob410.cpp
int main()
{
    double sales;
    bool finished = false;
    int ranges[9] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    system("cls");
    while (!finished)
    {  
        cout << "Enter the gross sales (a negative number will finish): ";
        cin >> sales;
        if (sales < 0)
        {
            system("cls");
            finished = true;
        }
        else
        {  
            sales = 200 + (sales * .09);
            sales = (int)sales;
            cout << "This employees salary, $" << sales << " is within catagory "; 
            if (sales >= 200 && sales <= 299)
            {
                ranges[0]++;
                cout << "A.";
            }
            else if (sales >= 300 && sales <= 399)
            {
                ranges[1]++;
                cout << "B.";
            }
            else if (sales >= 400 && sales <= 499)
            {
                ranges[2]++;
                cout << "C.";
            }
            else if (sales >= 500 && sales <= 599)
            {
                ranges[3]++;
                cout << "D.";
            }
            else if (sales >= 600 && sales <= 699)
            {
                ranges[4]++;	
                cout << "E.";
            }            
            else if (sales >= 700 && sales <= 799)
            {
                ranges[5]++;
                cout << "F.";
            }
            else if (sales >= 800 && sales <= 899)
            {
                ranges[6]++;
                cout << "G.";
            }
            else if (sales >= 900 && sales <= 999)
            {
                ranges[7]++;
                cout << "H.";
            }
            else if (sales >= 1000)
            {
                ranges[8]++;
                cout << "I.";
            }
            cout << endl << endl;
        }
    }
    if (ranges[0+1+2+3+4+5+6+7+8] > 0)
    {
      cout << "Final Statistics" << endl;
      cout << "--------------------------------" << endl;
      cout << ranges[0] << " catagory A employee(s) paid." << endl;
      cout << ranges[1] << " catagory B employee(s) paid." << endl;
      cout << ranges[2] << " catagory C employee(s) paid." << endl;
      cout << ranges[3] << " catagory D employee(s) paid." << endl;
      cout << ranges[4] << " catagory E employee(s) paid." << endl;
      cout << ranges[5] << " catagory F employee(s) paid." << endl;
      cout << ranges[6] << " catagory G employee(s) paid." << endl;
      cout << ranges[7] << " catagory H employee(s) paid." << endl;
      cout << ranges[8] << " catagory I employee(s) paid." << endl;
      cout << endl;
    }
    system("pause");
    return 0;
}

// 247.cpp
int main()
{
    int Stars;
    int Spaces;
    system("clear");
    cout << endl;
    Stars = 0;
    for (int LineCounter = 0; LineCounter <= 9; LineCounter++)
    {
        for (int StarCounter = Stars; StarCounter >= 0; StarCounter--)
        {
            cout << "*";
        }
        Stars++;
        cout << endl;
    }
    cout << endl;
    Stars = 0;
    for (int LineCounter = 0; LineCounter <= 10; LineCounter++)
    {
        Stars++; 
        for (int StarCounter = Stars; StarCounter <= 10; StarCounter++)
        {
            cout << "*";
        }
        cout << endl;
    }
    Stars = 0;
    Spaces = -1;
    for (int LineCounter = 0; LineCounter <= 10; LineCounter++)
    {
        Stars++; 
        for (int SpaceCounter = Spaces; SpaceCounter >= 0; SpaceCounter--) 
        {
            cout << " ";
        }
        for (int StarCounter = Stars; StarCounter <= 10; StarCounter++)
        {
            cout << "*";
        }
        Spaces++;
        cout << endl;    
    }  
    Stars = -1;
    Spaces = -1;
    for (int LineCounter = 0; LineCounter <= 10; LineCounter++)
    { 
        Spaces++;
        for (int SpaceCounter = Spaces; SpaceCounter <= 9; SpaceCounter++) 
        {
            cout << " ";
        }
        for (int StarCounter = Stars; StarCounter >= 0; StarCounter--)
        {
            cout << "*";
        }
        Stars++;
        cout << endl;    
    }
    cout << endl;
}

// randomGame.cpp
#include <cstdlib>
#include <ctime>
int rnd();
int main()
{
    system("cls");
    int userQuess;
    bool wantsToPlay = true;
    char playAgain;
    int secretNumber; 
    while (wantsToPlay != false)
    {
        secretNumber = 1 + rnd() & 1000; 
        cout << "cheat..." << secretNumber << endl;
        cout << "I have a number between 1 and 1000." << endl;
        cout << "Can you quess my number?" << endl;
        cout << "Please type your first quess..." << endl;
        cin >> userQuess;
        while (userQuess != secretNumber)
        {
            if (userQuess < secretNumber)
            {
            cout << "Too low. Try again." << endl;
        }
        else if (userQuess > secretNumber)
        {
            cout << "Too high. Try again." << endl;
        }
            cin >> userQuess;
        }
        cout << "Excellent, you quessed the right number!" << endl;
        cout << "Would you like to play again (y or n)" << endl;
        cin >> playAgain;
        if (playAgain == 'n')
        {
            wantsToPlay = false;
        }
    }
    system("pause");
}
int rnd()
{
    static bool init = false;
    if (!init)
    {
        init = true;
        srand(time(NULL));
    }
    return rand();    
}