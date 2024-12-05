use std::ops::Range;
use std::{fs, io};
use std::io::Read;
use regex::Regex;

// SOLVING THE PROBLEM (COPIED AND IMPROVED FROM PART1)
// a: 1-3 digit number | b:
// - mul(a, a)  | multiplies numbers
// - do()       | enables mul()
// - don't()    | disables mul()
// mul() is enabled on start
// =================================================================================================
fn index_in_range(index: usize, vector: &Vec<Range<usize>>) -> bool {

    for range in vector {

        if range.contains(&index) { return true; }
    }

    false
}

fn get_matches_from_regex(regex: Regex, instructions: &String) -> Vec<Range<usize>> {

    regex.find_iter(&instructions)
        .map(|mat| (mat.start()..mat.end()))
        .collect()
}

fn get_result_of_enabled_real_mul_instructions(mut reader: io::BufReader<fs::File>) -> i64 {

    let mut result: i64 = 0;

    // we get the instructions into a string
    let mut instructions = String::new();
    reader.read_to_string(&mut instructions).expect("Error reading from file to String");

    // we get the ranges of do() and don't() instructions
    let do_instruction_regex: Regex = Regex::new(r"do\(\)").expect("Error getting do_instruction_regex");
    let do_matches = get_matches_from_regex(do_instruction_regex, &instructions);
    
    let dont_instruction_regex: Regex = Regex::new(r"don't\(\)").expect("Error getting dont_instruction_regex");
    let dont_matches = get_matches_from_regex(dont_instruction_regex, &instructions);

    // we get the ranges where mul is enabled
    let mut enabled_ranges: Vec<Range<usize>> = Vec::new();
    enabled_ranges.push(0..dont_matches.get(0).unwrap().start); // we add the first range (0..<first don't()>)
    
    let mut before_dont_value: usize = dont_matches.get(0).unwrap().start;
    let mut do_index: usize = 0; // index for do() ranges
    let mut dont_index: usize = 1; // index for don't() ranges
    while do_index < do_matches.len() || dont_index < dont_matches.len() {

        let do_captures_valid: bool = do_index < do_matches.len();
        let dont_captures_valid: bool = dont_index < dont_matches.len();

        if do_captures_valid && dont_captures_valid {

            let do_value = do_matches.get(do_index).unwrap().start;
            let dont_value = dont_matches.get(dont_index).unwrap().start;

            if do_value < before_dont_value {

                do_index += 1;
                continue;
            }
            if dont_value < do_value {

                dont_index += 1;
                continue;
            }
            else {

                enabled_ranges.push(do_value..dont_value);
                before_dont_value = dont_value;
            }
        }
        else if do_captures_valid && !dont_captures_valid {

            enabled_ranges.push(do_matches.get(do_index).unwrap().start..instructions.len());
            break;
        }

        do_index += 1;
        dont_index += 1;
    }
    // println!("{:?}", enabled_ranges);

    // we iterate over all of the "mul" instructions and if mul is enabled, we add the result into the 'result' variable.
    let mul_instruction_regex: Regex = Regex::new(r"mul\((\d|[1-9]\d{1,2}),(\d|[1-9]\d{1,2})\)").expect("Error getting mul_instruction_regex");
    for mul_match in mul_instruction_regex.find_iter(&instructions) {

        let range_start_index = mul_match.start();

        let mul_string = mul_match.as_str();

        // we check that mul() is enabled
        if !index_in_range(range_start_index, &enabled_ranges) { /* println!("(-) {mul_string} | {range_start_index}"); */ continue; }

        // println!("(+) {mul_string} | {range_start_index}");

        let mul_parameters_regex: Regex = Regex::new(r"(\(\d+|\d+\))").expect("Error getting mul_parameters_regex");
        let mul_parameters: Vec<&str> = mul_parameters_regex.find_iter(&mul_string).map(|m| m.as_str()).collect();

        // we get the mul_parameters
        let first_parameter: i64 = (&(mul_parameters[0])[1..]).parse().expect("Error parsing i64 from String");
        let second_parameter: i64 = (&(mul_parameters[1])[..mul_parameters[1].len()-1]).parse().expect("Error parsing i64 from String");

        result += first_parameter * second_parameter;
    }

    result
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

    let mul_result = get_result_of_enabled_real_mul_instructions(get_reader("./tests/real.in"));

    // write function output to real.res
    fs::write("./tests/real.res", mul_result.to_string())
        .expect("Error while writing to file");
}

// cargo test
#[cfg(test)]
mod tests {
    use super::*; // da je vidna sva koda od parent (torej zunaj module-a)

    #[test]
    fn test01() {
        
        let mul_result = get_result_of_enabled_real_mul_instructions(get_reader("./tests/test01.in"));

        // write function output to test01.res
        fs::write("./tests/test01.res", mul_result.to_string())
            .expect("Error while writing to file");

        // read the file contents from test01.out
        // and
        // assert equals for: test01.out == function output
        let correct_mul_result = fs::read_to_string("./tests/test01.out")
            .expect("Erorr while reading file")
            .trim().parse()
                .expect("Error while parsing integer");

        assert_eq!(mul_result, correct_mul_result);
    }
}
// =================================================================================================