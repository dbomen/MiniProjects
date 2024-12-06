use std::{fs, io};
use std::io::BufRead;

enum Direction {
    LeftUp,
    Up,
    RightUp,
    Right,
    RightDown,
    Down,
    LeftDown,
    Left
}

struct Cell {

    i: usize,
    j: usize,
    value: char,
}

// SOLVING THE PROBLEM
// Finding XMAS inside the word_search_table. Valid XMAS if:
// vertical, horizontal, bacwards, diagonal or overlapping other words
// =================================================================================================
fn get_value_with_direction(i: usize, j: usize, word_search_table: &Vec<Vec<char>>, direction: &Direction) -> Cell {

    match direction {

        Direction::LeftUp => Cell {
            i: i.overflowing_sub(1).0, // we dont handle the overflow
            j: j.overflowing_sub(1).0,
            value: match word_search_table.get(i.overflowing_sub(1).0) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j.overflowing_sub(1).0) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::Up => Cell {
            i,
            j: j.overflowing_sub(1).0,
            value: match word_search_table.get(i) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j.overflowing_sub(1).0) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::RightUp => Cell {
            i: i + 1,
            j: j.overflowing_sub(1).0,
            value: match word_search_table.get(i + 1) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j.overflowing_sub(1).0) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::Right => Cell {
            i: i + 1,
            j,
            value: match word_search_table.get(i + 1) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::RightDown => Cell {
            i: i + 1,
            j: j + 1,
            value: match word_search_table.get(i + 1) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j + 1) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::Down => Cell {
            i,
            j: j + 1,
            value: match word_search_table.get(i) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j + 1) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::LeftDown => Cell {
            i: i.overflowing_sub(1).0,
            j: j + 1,
            value: match word_search_table.get(i.overflowing_sub(1).0) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j + 1) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }        },
        Direction::Left => Cell {
            i: i.overflowing_sub(1).0,
            j,
            value: match word_search_table.get(i.overflowing_sub(1).0) {

                None => '0',
                Some(inner_vector) => {
                    match inner_vector.get(j) {

                        None => '0',
                        Some(character) => *character,
                    }
                },
            }
        },
    }
}

fn is_xmas(current_char: char, direction: Direction, i: usize, j: usize, word_search_table: &Vec<Vec<char>>) -> bool {

    match current_char {

        // ending condition
        'S' => true,

        // recursion
        'X' => {

            // the next one has to be 'M'
            let next_cell: Cell = get_value_with_direction(i, j, word_search_table, &direction);
            if next_cell.value == 'M' { is_xmas('M', direction, next_cell.i, next_cell.j, word_search_table) }
            else { false }
        },
        'M' => {

            // the next one has to be 'A'
            let next_cell: Cell = get_value_with_direction(i, j, word_search_table, &direction);
            if next_cell.value == 'A' { is_xmas('A', direction, next_cell.i, next_cell.j, word_search_table) }
            else { false }
        }
        'A' => {

            // the next one has to be 'A'
            let next_cell: Cell = get_value_with_direction(i, j, word_search_table, &direction);
            if next_cell.value == 'S' { is_xmas('S', direction, next_cell.i, next_cell.j, word_search_table) }
            else { false }
        }

        _ => false,
    }
}

fn get_number_of_xmas_for_cell(word_search_table: &Vec<Vec<char>>, i: usize, j: usize) -> i64 {

    let mut number_of_xmas_for_cell: i64 = 0;

    // left-up
    if is_xmas('X', Direction::LeftUp, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // up
    if is_xmas('X', Direction::Up, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // right-up
    if is_xmas('X', Direction::RightUp, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // right
    if is_xmas('X', Direction::Right, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // right-down
    if is_xmas('X', Direction::RightDown, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // down
    if is_xmas('X', Direction::Down, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // left-down
    if is_xmas('X', Direction::LeftDown, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    // left
    if is_xmas('X', Direction::Left, i, j, word_search_table) { number_of_xmas_for_cell += 1; }

    number_of_xmas_for_cell 
}

fn get_number_of_xmas_in_input(reader: io::BufReader<fs::File>) -> i64 {

    let mut number_of_xmas: i64 = 0;

    let mut word_search_table: Vec<Vec<char>> = Vec::new();

    // we fill the word_search_table
    for line in reader.lines() {

        word_search_table.push(line.unwrap().chars().collect::<Vec<_>>());
    }

    // we iterate the word_search_table and check if there exists "XMAS" in any direction
    // (we can ignore bacwards "XMAS"s, because if it exists backwards then we will
    // handle it from another cell)
    for i in 0..word_search_table.len() {

        for j in 0..word_search_table[i].len() {

            // if the cell has the value="X", we check for any "XMAS"s
            if word_search_table[i][j] == 'X' {

                number_of_xmas += get_number_of_xmas_for_cell(&word_search_table, i, j);
            }
        }
    }

    number_of_xmas
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

    let mul_result = get_number_of_xmas_in_input(get_reader("./tests/real.in"));

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
        
        let mul_result = get_number_of_xmas_in_input(get_reader("./tests/test01.in"));

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