use std::{fs, io};

fn get_reader(file_path: &str) -> io::BufReader<fs::File> {

    // read the file contents from filePath
    let file: fs::File = fs::File::open(file_path)
        .expect("Error when reading file");

    // we return a BufReader
    io::BufReader::new(file)
}

// cargo run || cargo build + ./target/debug/Red-Nosed_Reports
fn main() {

    let mul_result = solution::count_unique_antinodes(get_reader("./tests/real.in"));

    // write function output to real.res
    fs::write("./tests/real.res", mul_result.to_string())
        .expect("Error while writing to file");
}

// cargo test
#[cfg(test)]
mod tests;

mod solution;