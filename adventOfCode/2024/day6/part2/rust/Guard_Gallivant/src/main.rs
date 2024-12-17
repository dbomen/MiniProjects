use std::{fs, io};
use std::io::BufRead;

// SOLVING THE PROBLEM
// Copied code from part1. Now we just add an obstacle on any of the 'X's in the guards path and check
// if this change makes the guard go in a loop (we do this for all 'X's).
// => The guard is in a loop if she lands in the SAME CELL in the board with the SAME DIRECTION
// => Otherwise the guard is not in a loop and will eventaully exit the place
// Changes:
//      - board is not Vec<Vec<BoardElement>>: BoardElement<value: char, direction_when_stepped: Option<Direction>>
//      - 

// Time Complexitiy => O(N^4) - worst case scenario the guard originally steps on all the cells
// For real.in -> N=130 -> 2.3s

// Note: the point of today was me getting experience with structs, enums, derived (and also not
// derived traits <see BoardElement>) and not time, so im not too woried about the O(N^4) complexity :p
// =================================================================================================
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
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

#[derive(Clone, Copy)]
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

    fn check_if_loop_and_update(&mut self, board: &mut Vec<Vec<BoardElement>>) -> Option<bool> {

        match board.get(self.x) {

            Some(row) => {
                
                match row.get(self.y) {

                    Some(board_element) => {
                        
                        match board_element.value {

                            '.' => {

                                // we update the board
                                board[self.x][self.y].value = 'X';
                                board[self.x][self.y].direction_when_stepped = Some(self.direction);
                                Some(false)
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
                                Some(false)
                            },
                            'X' => {

                                // we check if the guard is in a loop
                                match board_element.direction_when_stepped {

                                    Some(board_direction) => {
                                        
                                        Some(board_direction == self.direction) // if the same direction => returns Some(true)
                                    },
                                    None => panic!("INVALID DIRECTION FOR BOARD ELEMENT=[{},{:?},x:{},y:{}]. DIRECTION SHOULD HAVE BEEN UPDATED", board_element.value, board_element.direction_when_stepped, self.x, self.y),
                                }
                            }
                            c => panic!("INVALID CHARACTER:[{c}] FOUND IN BOARD!")
                        }
                    },
                    None => None, // if out-of-bounds the guard left
                }
            },
            None => None, // if out-of-bounds the guard left
        }
    }

    /// steps and returns if the guard is in a loop
    fn step(&mut self, board: &mut Vec<Vec<BoardElement>>) -> Option<bool> {

        match self.direction {

            Direction::Up => {

                match self.x.checked_sub(1) {

                    Some(x) => {
                        
                        self.x = x;
                        self.check_if_loop_and_update(board)
                    },
                    None => None,
                }
            },
            Direction::Right => {

                self.y += 1;
                self.check_if_loop_and_update(board)
            },
            Direction::Down => {

                self.x += 1;
                self.check_if_loop_and_update(board)
            },
            Direction::Left => {

                match self.y.checked_sub(1) {

                    Some(y) => {
                        
                        self.y = y;
                        self.check_if_loop_and_update(board)
                    },
                    None => None,
                }
            },
        }
    }
}

#[derive(Clone, Copy)]
struct BoardElement {

    value: char,
    direction_when_stepped: Option<Direction>
}
impl std::fmt::Debug for BoardElement {

    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{}", self.value)
    }
}

fn get_number_of_different_obstruction_positions(reader: io::BufReader<fs::File>) -> i64 {

    let mut number_of_different_obstruction_positions: i64 = 0; // guard starts at one position

    // get board and Guard position
    let mut original_guard: Option<Guard> = None;
    let mut guard_starting_position: Option<(usize, usize)> = None;

    let mut original_board: Vec<Vec<BoardElement>> = Vec::new();

    for line in reader.lines() {

        let mut line = line.expect("ERROR GETTING LINE!");

        // if we do not have the guard yet we check if guard is in this line (row)
        if original_guard.is_none() {

            if let Some((index, c)) = line.chars().enumerate().find(|&(_, c)| matches!(c, '^' | '>' | 'v' | '<')) {

                match c {

                    '^' => original_guard = Some(Guard::new(original_board.len(), index, Direction::Up)),
                    '>' => original_guard = Some(Guard::new(original_board.len(), index, Direction::Right)),
                    'v' => original_guard = Some(Guard::new(original_board.len(), index, Direction::Down)),
                    '<' => original_guard = Some(Guard::new(original_board.len(), index, Direction::Left)),
                    _   => ()
                };

                unsafe { line.as_bytes_mut()[index] = b'X'; } // we mark this spot, since the guard starts here
                guard_starting_position = Some((original_board.len(), index));
            }
        }

        // we add the line (row) to the board after converting its characters to BoardElement
        original_board.push(
            line.chars()
                .map(|c| BoardElement { // we map the chars -> BoardElement
                    value: c,
                    direction_when_stepped: if c == 'X' { Some(original_guard.expect("GUARD SHOULD HAVE BEEN GIVEN BY NOW!?").direction) } else { None },
                })
                .collect() // we collect the result
        );
    }

    let original_guard: Guard = original_guard.expect("ERROR: GUARD NOT GIVEN!");
    let guard_starting_position = guard_starting_position.expect("ERROR: GUARD POSITION NOT GIVEN!");

    let mut guard = original_guard.clone(); // we clone the original_guard into guard
    let mut board = original_board.to_vec(); // we clone the original_board into board

    loop { // we monitor the guard movement until she leaves and get the board with 'X's

        // DEBUG:
        // print!("{}, {}, {:?}\n", guard.x, guard.y, guard.direction);

        match guard.step(&mut board) {

            Some(_) => (),
            None => break
        }
    }

    guard = original_guard.clone(); // we reset the guard

    // 2nd Part of the puzzle
    // ---
    // We iterate the board (containing the 'X's) and we replace the 'X's with '#' and simulate
    // the guard walking in that board
    for i in 0..board.len() {

        for j in 0..board[i].len() {

            let board_element: BoardElement = board[i][j];

            if board_element.value == 'X' {

                // we cannot put the obstruction into the guard starting position
                if (i, j) == guard_starting_position { continue; }

                // we add the obstruction to the original board
                let original_value = original_board[i][j].value;
                original_board[i][j].value = '#';

                let mut board_simulation = original_board.clone();

                // DEBUG:
                // print!("i:{i}, j:{j}\n-------------------------------\n");
                // print!("{:?}", original_board);

                // we simulate the guard movement in the new board
                loop {

                    // DEBUG:
                    // print!("{}, {}, {:?}\n", guard.x, guard.y, guard.direction);
        
                    match guard.step(&mut board_simulation) { // we pass in a copy of the original board with the new obstruction
            
                        Some(is_guard_in_loop) => if is_guard_in_loop {

                            number_of_different_obstruction_positions += 1;
                            break;
                        },
                        None => break
                    }
                }

                // DEBUG:
                // print!("-------------------------------\n");

                // we remove the obstruction from the original board
                original_board[i][j].value = original_value;
            }

            guard = original_guard.clone(); // we reset the guard
        }
    }
    // ---

    number_of_different_obstruction_positions
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

    let mul_result = get_number_of_different_obstruction_positions(get_reader("./tests/real.in"));

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
        
        let mul_result = get_number_of_different_obstruction_positions(get_reader("./tests/test01.in"));

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