// ==========
// Simple solution: O((N * M)^2)

// Complicated solution to practice Rust (worse time complexity)

// Puzzle input: N*M map
// - We get all Antennas into a Set
// - We iterate the map (puzzle input) and we check if there is a antinode in the current Position
//  - for each Position we do BFS for Antennas, until the "jump" into the antipodal position is out of bounds
// ==========
use std::{collections::{HashSet, VecDeque}, fs, hash::Hash, io::{self, BufRead}};

#[derive(PartialEq, Eq, Hash, Clone, Debug)]
struct Position {
    x: usize,
    y: usize
}
impl Position {

    /// return true if valid position based on bounds - all including
    fn is_valid_position_bounds(&self, height_upper_bound: usize, height_lower_bound: usize, 
        width_upper_bound: usize, width_lower_bound: usize,) -> bool {

        self.x <= height_upper_bound && self.x >= height_lower_bound && self.y <= width_upper_bound && self.y >= width_lower_bound
    }
    fn is_valid_position(&self, height: usize, width: usize) -> bool {

        self.x < height && self.y < width
    }

    fn get_neighbours(&self, height: usize, width: usize) -> Vec<Position> {

        let directions = [
            (-1, -1), (-1, 0), (-1, 1), // UpLeft, Up, UpRight
            (0, -1),           (0, 1),  // Left, Right
            (1, -1),  (1, 0),  (1, 1),  // DownLeft, Down, DownRight
        ];

        directions
            .iter()
            // we get the new positions for each direction. We filter out possible underflows
            .filter_map(|(dx, dy)| {
                let new_x = self.x.checked_add_signed(*dx)?;
                let new_y = self.y.checked_add_signed(*dy)?;
                Some(Position { x: new_x, y: new_y })
            })
            // we filter out possible out of bounds positions
            .filter(|pos| pos.clone().is_valid_position(height, width))
            // we count the number of valid positions (neighbours)
            .collect()
    }
}

#[derive(Debug)]
struct Antenna {

    position: Position,
    freq: char
}
impl Antenna {

    fn element(freq: char, x: usize, y: usize) -> Self {

        Antenna {
            freq,
            position: Position {x, y},
        }
    }
}
impl Hash for Antenna {

    fn hash<H: std::hash::Hasher>(&self, state: &mut H) {
        self.position.hash(state);
    }
}
impl Eq for Antenna {}
impl PartialEq for Antenna {
    fn eq(&self, other: &Self) -> bool {
        self.position == other.position
    }
}

/// checks for antennas with BFS
fn search_for_antennas_bfs(position: Position, height: usize, width: usize, antennas: &HashSet<Antenna>) -> bool {

    // optimization: we only need to do BFS for [N/2 * M/2] positions, if there is an antenna after that
    // when we "jump" to check for an antenna in the antipodal position we will fall outside the bounds
    let bounds = (
        position.x + ((height - 1 - position.x) / 2),
        (position.x + 1) / 2,
        position.y + ((width - 1 - position.y) / 2),
        (position.y + 1) / 2,
    ); // (height_upper, heigh_lower, width_upper, width_lower) - including

    // println!("--- starting position:{:?} | bounds:{:?}", position, bounds);

    let mut visited_positions: HashSet<Position> = HashSet::new();
    let mut to_be_checked_q: VecDeque<Position> = VecDeque::new();
    
    to_be_checked_q.push_back(position.clone());
    visited_positions.insert(position.clone());

    while !to_be_checked_q.is_empty() {

        let current_position = to_be_checked_q.pop_front().unwrap();

        let neighbours: Vec<Position> = current_position.get_neighbours(width, height);

        for neighbour in neighbours {

            // print!("HI:{:?} ---", neighbour);

            // we ignore if the positions has already been visited or if the position is invalid
            if !visited_positions.contains(&neighbour) && neighbour.is_valid_position_bounds(bounds.0, bounds.1, bounds.2, bounds.3) {

                // print!("looking at position:{:?}", neighbour);

                // we check for antennas
                if let Some(antenna) = antennas.get(&Antenna { position: neighbour.clone(), freq: 'A'}) { // freq can be whatever, see the PartialEq implementation

                    // print!(" -> ANTENNA!");

                    // we "jump" to check for an antenna with the same freq in the antipodal position
                    let new_x: isize = neighbour.x.try_into().unwrap();
                    let new_y: isize = neighbour.y.try_into().unwrap();
                    let x_diff: isize = (new_x.checked_sub_unsigned(position.x)).unwrap();
                    let y_diff: isize = (new_y.checked_sub_unsigned(position.y)).unwrap();
                    let jump_position = Position { x: neighbour.x.checked_add_signed(x_diff).unwrap(), y: neighbour.y.checked_add_signed(y_diff).unwrap() };
                    if let Some(jump_antenna) = antennas.get(&Antenna { position: jump_position.clone(), freq: 'A'}) {

                        if antenna.freq == jump_antenna.freq { 
                            // println!("found antipodal!:{:?}", jump_antenna); 
                            return true; 
                        }
                    }

                    // print!(" -> no antipodal at position:{:?}!", jump_position);
                }

                // if nothing was found we continue BFS
                to_be_checked_q.push_back(neighbour.clone());
                visited_positions.insert(neighbour);
            }
            // println!();
        }
    }

    false
}

pub fn count_unique_antinodes(reader: io::BufReader<fs::File>) -> i64 {

    let mut antennas: HashSet<Antenna> = HashSet::new();
    let mut antinodes: HashSet<Position> = HashSet::new();

    let mut height: usize = 0; // N
    let mut width: usize = 0; // M

    for line in reader.lines() {

        let line = line.expect("ERROR GETTING LINE!");

        width = 0;
        for c in line.chars() {

            if c != '.' { antennas.insert(Antenna::element(c, height, width)); }
            width += 1;
        }

        height += 1;
    }

    for i in 0..height {

        for j in 0..width {

            let position: Position = Position { x: i, y: j };

            if antinodes.contains(&position) { continue; }; // if the position already contains an antinode

            if search_for_antennas_bfs(position.clone(), height, width, &antennas) {

                antinodes.insert(position);
                continue;
            }
        }
    }

    antinodes.len().try_into().unwrap()
}