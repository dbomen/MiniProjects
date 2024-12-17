use std::{fs, io};
use std::io::BufRead;

// SOLVING THE PROBLEM
// Yes I know you can probably do this in like 5 lines, but I overengineered this on purpose, 
// to practice structs and enums, from chapters 5 and 6 (rust book)
// Time Complexitiy => O(N^2)
// =================================================================================================
enum Direction {

    Up,
    Right,
    Down,
    Left
}
impl Direction {
    
    fn turn_90_degrees(&self) -> Self {

        match self {

            Self::Up => Self::Right,
            Self::Right => Self::Down,
            Self::Down => Self::Left,
            Self::Left => Self::Up
        }
    }

    fn turn_180_degrees(&self) -> Self {

        match self {

            Self::Up => Self::Down,
            Self::Right => Self::Left,
            Self::Down => Self::Up,
            Self::Left => Self::Right
        }
    }
}

struct Guard {

    x: usize,
    y: usize,
    direction: Direction
}
impl Guard {
    
    fn new(x: usize, y: usize, direction: Direction) -> Self {

        Self {
            x,
            y,
            direction
        }
    }

    fn get_step_result_and_update(&mut self, board: &mut Vec<String>) -> Option<bool> {

        match board.get(self.x) {

            Some(row) => {
                
                match row.chars().nth(self.y) {

                    Some(char_value) => {
                        
                        match char_value {

                            '.' => {

                                unsafe { board[self.x].as_bytes_mut()[self.y] = b'X'; } // we update the board
                                Some(true) // unique position
                            },
                            '#' => {

                                // we go back
                                match self.direction.turn_180_degrees() {

                                    Direction::Up => self.x -= 1,
                                    Direction::Right => self.y += 1,
                                    Direction::Down => self.x += 1,
                                    Direction::Left => self.y -= 1,
                                };

                                self.direction = self.direction.turn_90_degrees(); // we update the direction
                                Some(false) // not unique position
                            },
                            'X' => Some(false), // not unique position
                            c => panic!("INVALID CHARACTER:[{c}] FOUND IN BOARD!")
                        }
                    },
                    None => None, // if out-of-bounds the guard left
                }
            },
            None => None, // if out-of-bounds the guard left
        }
    }

    /// steps and returns if the guard stepped on a new distinch position
    fn step(&mut self, board: &mut Vec<String>) -> Option<bool> {

        match self.direction {

            Direction::Up => {

                match self.x.checked_sub(1) {

                    Some(x) => {
                        
                        self.x = x;
                        self.get_step_result_and_update(board)
                    },
                    None => None,
                }
            },
            Direction::Right => {

                self.y += 1;
                self.get_step_result_and_update(board)
            },
            Direction::Down => {

                self.x += 1;
                self.get_step_result_and_update(board)
            },
            Direction::Left => {

                match self.y.checked_sub(1) {

                    Some(y) => {
                        
                        self.y = y;
                        self.get_step_result_and_update(board)
                    },
                    None => None,
                }
            },
        }
    }
}

fn get_number_of_distinct_positions(reader: io::BufReader<fs::File>) -> i64 {

    let mut number_of_distinct_positions: i64 = 1; // guard starts at one position

    // get board and Guard position
    let mut guard: Option<Guard> = None;
    let mut board: Vec<String> = Vec::new();

    for line in reader.lines() {

        let mut line = line.expect("ERROR GETTING LINE!");

        // if we do not have the guard yet we check if guard is in this line (row)
        if guard.is_none() {

            if let Some((index, c)) = line.chars().enumerate().find(|&(_, c)| matches!(c, '^' | '>' | 'v' | '<')) {

                match c {

                    '^' => guard = Some(Guard::new(board.len(), index, Direction::Up)),
                    '>' => guard = Some(Guard::new(board.len(), index, Direction::Right)),
                    'v' => guard = Some(Guard::new(board.len(), index, Direction::Down)),
                    '<' => guard = Some(Guard::new(board.len(), index, Direction::Left)),
                    _   => ()
                };

                unsafe { line.as_bytes_mut()[index] = b'X'; } // we mark this spot, since the guard starts here
            }
        }

        // we add the line (row) to the board
        board.push(line);
    }

    let mut guard: Guard = guard.expect("ERROR: GUARD NOT GIVEN!");

    loop { // we monitor the guard movement until she leaves

        // DEBUG:
        // print!("{}, {}, {}\n", guard.x, guard.y, match guard.direction {
        //     Direction::Up => "UP",
        //     Direction::Right => "RIGHT",
        //     Direction::Down => "DOWN",
        //     Direction::Left => "LEFT",
        // }); 

        match guard.step(&mut board) {

            Some(unique_position) => if unique_position { number_of_distinct_positions += 1 },
            None => break
        }
    }

    number_of_distinct_positions
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

    let mul_result = get_number_of_distinct_positions(get_reader("./tests/real.in"));

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
        
        let mul_result = get_number_of_distinct_positions(get_reader("./tests/test01.in"));

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