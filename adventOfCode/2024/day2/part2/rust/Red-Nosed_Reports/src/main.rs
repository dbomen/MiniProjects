use std::{fs, io};
use std::io::BufRead;

// SOLVING THE PROBLEM
// =================================================================================================
fn abs_difference(a: i32, b: i32) -> i32 {

    let dif = b - a;
    if dif < 0 { -dif }
    else       { dif }
}

fn check_level_safeness(a: i32, b: i32, increases: &mut i64, decreases: &mut i64) -> bool {

    if      a < b { *increases += 1; }
    else if a > b { *decreases += 1; }

    // direction errors
    if *increases >= 1 && *decreases >= 1 {

        return false;
    }

    // difference errors
    if abs_difference(a, b) > 3 || abs_difference(a, b) == 0 {

        return false;
    }

    true
}

fn is_safe_report(levels: Vec<&str>, mut increases: i64, mut decreases: i64, inner: bool) -> bool {

    // we go level by level, each time checking correctness with the right adjacent level.
    for i in 0..(levels.len() - 1) {

        let a: i32 = levels[i].parse().expect("Error parsing i32 from &str");
        let b: i32 = levels[i + 1].parse().expect("Error parsing i32 from &str");

        if !check_level_safeness(a, b, &mut increases, &mut decreases) {

            // if we are not already looking with a vector with a deleted element
            if inner { return false; }

            // then we try to delete one of the elements that came before this error
            // and see if the report is, by deleting it (tolerating it), safe
            for j in 0..=i+1 {

                let mut levels_without_j: Vec<&str> = levels.clone();
                levels_without_j.remove(j);
    
                if is_safe_report(levels_without_j, 0, 0, true) { return true; }
            }

            return false;
        }
    }

    true
}

fn get_number_of_safe_reports(reader: io::BufReader<fs::File>) -> i32 {

    let mut number_of_safe_reports: i32 = 0;

    // we go line by line (report by report) and we check if the report is good:
    // - The levels are either all increasing or all decreasing.
    // - Any two adjacent levels differ by at least one and at most three.
    // - Tolerate a single bad level.
    // time complexity: (not so good, -4h cuz im bad lmao, I dont want to calculate this now)
    for line in reader.lines() {

        // we get all numbers into a vector (represented by a string, will be parsed while checking)
        let line: String = line.expect("Error getting line from Buffered reader");
        let levels: Vec<&str> = line.split_whitespace().collect();

        if levels.len() < 2 { number_of_safe_reports += 1; continue; }

        if is_safe_report(levels, 0, 0, false) { number_of_safe_reports += 1; }
    }

    number_of_safe_reports
}
// =================================================================================================

// SETUP AND TESTS (COPIED FROM PART1)
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