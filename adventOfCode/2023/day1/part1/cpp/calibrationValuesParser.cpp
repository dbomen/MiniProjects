#include <iostream>
#include <fstream>
#include <algorithm>
#include <stdexcept>

bool isDigit(char c) {

    if (c >= '0' && c <= '9')  return true;
    return false;
}

int parseFirstDigit(std::string& buffer) {

    for (int i = 0; i < buffer.size(); i++) {

        char currentChar = buffer.at(i);
        if (isDigit(currentChar)) {

            return currentChar - '0';
        }
    }

    throw std::runtime_error("NO DIGIT FOUND");
}

int main() {

    std::string buffer;
    std::ifstream file("input.in");

    int sum = 0;

    // we read from file line by line (till file.eof() - eof bit is set)
    while (std::getline(file, buffer)) {

        // we get the first digit
        int firstDigit = parseFirstDigit(buffer);

        // we reverse the string and get the last digit
        std::reverse(buffer.begin(), buffer.end());
        int lastDigit = parseFirstDigit(buffer);

        // we get the calibration value AND add it to the sum
        int calibrationValue = firstDigit * 10 + lastDigit;
        sum += calibrationValue;
    }

    std::cout << sum;

    file.close();
}