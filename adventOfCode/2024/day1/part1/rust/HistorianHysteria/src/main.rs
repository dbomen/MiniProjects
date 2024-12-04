// we put these modules into scope
use std::io; // Input / Output
use std::fs; // File system. To handle files
use std::io::BufRead;

fn absolute_difference(a: i32, b: i32) -> i32 {

    let difference= a - b;
    if difference >= 0 { difference  }
    else               { -difference }
}

fn get_distance(file_path: &str) -> i32 {

    let mut distance: i32 = 0;

    // read the file contents from filePath
    let file: fs::File = fs::File::open(file_path)
        .expect("Error when reading file");
    // we get a BufReader
    let reader: io::BufReader<fs::File> = io::BufReader::new(file);

    // we prepare the left and right vectors
    let mut left: Vec<i32> = Vec::new();
    let mut right: Vec<i32> = Vec::new();

    // we read line by line
    for line in reader.lines() {

        // we handle possible IO errors
        let line: String = line.expect("Error getting line from Buffered reader");

        // we parse the 2 numbers from the line and put them into the coresponding vector
        let pair: Vec<&str> = line.split_whitespace().collect(); // we split the string by whitespace

        left.push(pair[0].parse().expect("Error parsing i32")); // we put the first one in the left vector
        right.push(pair[1].parse().expect("Error parsing i32")); // we put the second one in the right vector
    }

    // we sort both vectors
    left.sort();
    right.sort();

    // we now iterate through both and increment the distance variable by their absolute difference
    for i in 0..left.len() {

        distance += absolute_difference(left[i], right[i]);
    }

    distance // we return the distance value (this expression)
}

// cargo run / cargo build + ./target/debug/HistorianHysteria
fn main() {

    let distance = get_distance("./tests/real.in");

    // write function output to real.res
    fs::write("./tests/real.res", distance.to_string())
        .expect("Error while writing to file");
}

// cargo test
#[cfg(test)]
mod tests {
    use super::*; // da je vidna sva koda od parent (torej zunaj module-a)

    #[test]
    fn test01() {
        
        let distance = get_distance("./tests/test01.in");

        // write function output to real.res
        fs::write("./tests/test01.res", distance.to_string())
            .expect("Error while writing to file");

        // read the file contents from test01.out
        // and
        // assert equals for: test01.out == function output
        let correct_distance = fs::read_to_string("./tests/test01.out")
            .expect("Erorr while reading file")
            .trim().parse()
                .expect("Error while parsing integer");

        assert_eq!(distance, correct_distance);
    }
}