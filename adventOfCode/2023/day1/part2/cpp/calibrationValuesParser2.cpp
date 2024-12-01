#include <iostream>
#include <fstream>
#include <algorithm>
#include <map>

bool isDigit(char c) {

    if (c >= '0' && c <= '9')  return true;
    return false;
}

enum StringType {
    REVERSED,
    NOT_REVERSED
};

// line - reference to the current line we got from the file
// type - tells the function the type of the parsing - is the function getting a reverse string
//        or not. If yes then we append at the front of the buffer, otherwise we append at the end of the buffer.
//        Also if yes the substrings we look at go from the top and if no from the bottom. SEE EXAMPLE BELOW!
// wordToDigit - reference to the wordToDigit mapping
int parseFirstDigit(std::string& line, StringType type, std::map<std::string, int>& wordToDigit) {

    std::string buffer;
    for (int i = 0; i < line.size(); i++) {

        char currentChar = line.at(i);

        // we first check if the current char is a digit
        if (isDigit(currentChar)) {

            return currentChar - '0';
        }

        // if not we add the currentChar to the buffer
        switch (type) {
            case REVERSED:
                buffer.insert(0, 1, currentChar); // "push_front"
                break;
            case NOT_REVERSED:
                buffer.push_back(currentChar);
                break;
        }    

        // if the buffer.size() >= 3, we start looking if there exists a substring that
        // matches the word in the map. If it does we end get the value and return the digit.
        // We are only checking new substrings. Also if we have a reversed string we have to reverse
        // this, so instead of looking at the bottom substring, we should be looking at the top substring.
        // EXAMPLE FOR NOT REVERSED SUBSTRING -> line="ooone"
        // +--------------------------------------+
        // | it|buf    |what we check             |
        // +--------------------------------------+
        // |  1|"o"    | => /                     |
        // |  2|"oo"   | => /                     |
        // |  3|"ooo"  | => "ooo"                 |
        // |  4|"ooon" | => "ooon", "oon"         |
        // |  5|"ooone"| => "ooone", "oone", "one"|
        // +--------------------------------------+
        
        // EXAMPLE FOR REVERSED SUBSTRING -> line="enooo" (we get "one" immidiately because we 'push_front')
        // for the purpose of this example I will show what substring we would be looking at if we would not return
        // +--------------------------------------+
        // | it|buf    |what we check             |
        // +--------------------------------------+
        // |  1|"o"    | => /                     |
        // |  2|"oo"   | => /                     |
        // |  3|"ooo"  | => "one" (returns 1)     |
        // |  4|"ooon" | => "oon", "oone"         | [if not returned, what would happen]
        // |  5|"ooone"| => "ooo", "ooon", "ooone"| [if not returned, what would happen]
        // +--------------------------------------+
        int bufferSize = i + 1;
        if (bufferSize >= 3) {

            // we go through the new substrings and check if they match any of the keys
            for (int j = 0; j <= bufferSize - 3; j++) {

                std::string substring;
                switch (type) {
                    case REVERSED:
                        substring = buffer.substr(0, j + 3);
                        break;
                    case NOT_REVERSED:
                        substring = buffer.substr(j);
                        break;
                }  

                // we check if the substring matches any of the keys from the map
                if (wordToDigit.count(substring) != 0) {

                    return wordToDigit.at(substring);
                }
            }
        }
    }

    throw std::runtime_error(buffer);
}

int main() {

    std::map<std::string, int> wordToDigit = {
        {"one", 1},
        {"two", 2},
        {"three", 3},
        {"four", 4},
        {"five", 5},
        {"six", 6},
        {"seven", 7},
        {"eight", 8},
        {"nine", 9},
    };

    std::string line;
    std::ifstream file("input.in");

    int sum = 0;

    // we read from file line by line (till file.eof() - eof bit is set)
    while (std::getline(file, line)) {

        // we get the first digit
        int firstDigit = parseFirstDigit(line, StringType::NOT_REVERSED, wordToDigit);

        // we reverse the string and get the last digit
        // AFTER DEBUG NOTE: we have to get rid of the '\n', because the reverse function will put it
        // at the 0 index. Classic '\n' causing problems.
        std::reverse(line.begin(), line.end());
        line = line.substr(1);
        int lastDigit = parseFirstDigit(line, StringType::REVERSED, wordToDigit);

        // we get the calibration value AND add it to the sum
        int calibrationValue = firstDigit * 10 + lastDigit;
        sum += calibrationValue;
    }

    std::cout << sum;

    file.close();
}