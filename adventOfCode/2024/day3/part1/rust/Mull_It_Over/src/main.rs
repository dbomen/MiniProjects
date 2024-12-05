use std::{fs, io};
use std::io::Read;
use regex::Regex;

// SOLVING THE PROBLEM
// =================================================================================================
fn get_result_of_real_mul_instructions(mut reader: io::BufReader<fs::File>) -> i64 {

    let mut result: i64 = 0;

    // we get the instructions into a string
    let mut instructions = String::new();
    reader.read_to_string(&mut instructions).expect("Error reading from file to String");

    // we find all correct "mul" instructions and put them into a vector
    let mul_instruction_regex: Regex = Regex::new(r"mul\((\d|[1-9]\d{1,2}),(\d|[1-9]\d{1,2})\)").expect("Error getting mul_instruction_regex");
    let correct_mul_instructions: Vec<&str> = mul_instruction_regex.find_iter(&instructions).map(|m| m.as_str()).collect();
    
    // we iterate over all of the correct "mul" instructions and add the result into the 'result' variable
    for mul_instruction in correct_mul_instructions {

        let mul_parameters_regex: Regex = Regex::new(r"(\(\d+|\d+\))").expect("Error getting mul_parameters_regex");
        let mul_parameters: Vec<&str> = mul_parameters_regex.find_iter(&mul_instruction).map(|m| m.as_str()).collect();

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

    let mul_result = get_result_of_real_mul_instructions(get_reader("./tests/real.in"));

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
        
        let mul_result = get_result_of_real_mul_instructions(get_reader("./tests/test01.in"));

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