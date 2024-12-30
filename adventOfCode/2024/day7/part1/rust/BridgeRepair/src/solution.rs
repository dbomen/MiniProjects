use std::{fs, io::{self, BufRead}};

struct Equation {

    result: i64,
    operands: Vec<i64>
}

impl Equation {

    /// correct string format: "result: operand1 operand2 <optional: ...>". Expecting at minimum 2 operands
    /// 
    /// "absorbs" string_equation (takes ownership and does not return it)
    fn get_equation_from_string(string_equation: String) -> Self {

        let colon_index: usize = string_equation.find(':').expect("INVALID STRING FORMAT. COULD NOT FIND `:`!");

        Equation {

            result: (&string_equation[..colon_index])
                .parse().expect("ERROR GETTING RESULT!"),

            // should not be out-of-bounds if correct string format
            operands: (&string_equation[(colon_index + 2)..])
                .split(" ")
                .map(|operand: &str| operand.parse::<i64>().expect("ERROR GETTING OPERANDS!"))
                .collect()
        }
    }

    /// recursively checks if we can produce the equation result by going through all the combinations
    /// of operators ('+' and '*') between the equation operands.
    fn can_equation_be_true(&self, current_cumulative_result: i64, operand_index: usize) -> bool {

        // ENDING CONDITION
        if operand_index == self.operands.len() {

            // we check if we produced the equation_result
            return self.result == current_cumulative_result;
        }

        // RECURSION
        if      self.can_equation_be_true(current_cumulative_result + self.operands[operand_index], operand_index + 1) { true } // we try '+'
        else if self.can_equation_be_true(current_cumulative_result * self.operands[operand_index], operand_index + 1) { true } // we try '*'
        else    { false }
    }
}

pub fn get_total_calibration_result(reader: io::BufReader<fs::File>) -> i64 {

    let mut total_calibration_result: i64 = 0;

    for line in reader.lines() {

        let line = line.expect("ERROR GETTING LINE!");

        let calibration_equation: Equation = Equation::get_equation_from_string(line);

        // recursively checks if the equation can be true
        if calibration_equation.can_equation_be_true(0, 0) {

            total_calibration_result += calibration_equation.result;
        }
    }

    total_calibration_result
}