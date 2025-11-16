// http://blog.wolffmyren.com/2008/05/02/g-warning-deprecated-conversion-from-string-constant-to-%E2%80%98char%E2%80%99/
// http://www.lysator.liu.se/c/c-faq/c-3.html

#include <iostream>
#include <cstring>
#include <cstdlib>

int main(int argc, char* argv[])
{
	char cstringA[] = "wasd";
	std::cout << "cstringA: " << cstringA << "\t\t" << strlen(cstringA) << std::endl;

	char cstringB[] = {'w','a','s','d', '\0'};
	std::cout << "cstringB: " << cstringB << "\t\t" << strlen(cstringB) << std::endl;
	const char* cstringP = "cstringP";
	std::cout << "cstringP: " << cstringP << "\t" << strlen(cstringP) << std::endl;

	char* cstringM = (char*)malloc(strlen(cstringP));
	memcpy(cstringM, cstringP, strlen(cstringP)-1);
	std::cout << "cstringM: " << cstringM << "\t" << strlen(cstringM) << std::endl;
	delete cstringM;

	return 0;
}
