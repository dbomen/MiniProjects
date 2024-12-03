use std::{fs, io};
use std::io::BufRead;

// SOLVING THE PROBLEM
// =================================================================================================
fn abs_difference(a: i32, b: i32) -> i32 {

    let dif = b - a;
    if dif < 0 { -dif }
    else       { dif }
}

fn get_number_of_safe_reports(reader: io::BufReader<fs::File>) -> i32 {

    let mut number_of_safe_reports: i32 = 0;

    // we go line by line (report by report) and we check if the report is good:
    // - The levels are either all increasing or all decreasing.
    // - Any two adjacent levels differ by at least one and at most three.
    // time complexity: O(N) * O(M) = O(N * M) | N - number of reports | M - number of levels in a report
    for line in reader.lines() {

        // we get all numbers into a vector (represented by a string, will be parsed while checking)
        let line: String = line.expect("Error getting line from Buffered reader");
        let levels: Vec<&str> = line.split_whitespace().collect();

        if levels.len() < 2 { number_of_safe_reports += 1; continue; }

        // At the start we check the direction of change (false - decreasing | true - increasing)
        let increasing: bool = {
            
            let a: i32 = levels[0].parse().expect("Error parsing i32 from &str");
            let b: i32 = levels[1].parse().expect("Error parsing i32 from &str");
        
            let dir: bool = if a < b { true } else { false };

            if abs_difference(a, b) > 3 || abs_difference(a, b) == 0 { continue; }
            dir
        };

        // we go level by level, each time checking correctness with the right adjacent level.
        let mut safe: bool = true;
        for i in 1..(levels.len() - 1) {

            let a: i32 = levels[i].parse().expect("Error parsing i32 from &str");
            let b: i32 = levels[i + 1].parse().expect("Error parsing i32 from &str");

            // we check direction and difference correctness
            if increasing && a >= b || !increasing && a <= b || abs_difference(a, b) > 3 {

                safe = false;
                break;
            }
        }

        if safe { number_of_safe_reports += 1; }
    }

    number_of_safe_reports
}
// =================================================================================================

// SETUP AND TESTS
// =================================================================================================
fn get_reader(file_path: &str) -> io::BufReader<fs::File> {

    // read the file contents from filePath
    let file: fs::File = fs::File::open(file_path)
        .expect("Error when reading file");

    // we return a BufReader
    io::BufReader::new(file)
}

// cargo run / cargo build + ./target/debug/Red-Nosed_Reports
fn main() {

    let number_of_safe_reports = get_number_of_safe_reports(get_reader("./tests/real.in"));

    // write function output to real.res
    fs::write("./tests/real.res", number_of_safe_reports.to_string())
        .expect("Error while writing to file");
}

// cargo test
#[cfg(test)]
mod tests {
    use super::*; // da je vidna sva koda od parent (torej zunaj module-a)

    #[test]
    fn test01() {
        
        let number_of_safe_reports = get_number_of_safe_reports(get_reader("./tests/test01.in"));

        // write function output to test01.res
        fs::write("./tests/test01.res", number_of_safe_reports.to_string())
            .expect("Error while writing to file");

        // read the file contents from test01.out
        // and
        // assert equals for: test01.out == function output
        let correct_number_of_safe_reports = fs::read_to_string("./tests/test01.out")
            .expect("Erorr while reading file")
            .trim().parse()
                .expect("Error while parsing integer");

        assert_eq!(number_of_safe_reports, correct_number_of_safe_reports);
    }
}
// =================================================================================================