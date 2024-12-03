// we put these modules into scope
use std::io; // Input / Output
use std::fs; // File system. To handle files
use std::io::BufRead;

fn lower_bound(target: i32, list: &Vec<i32>) -> usize {

    upper_bound(target - 1, list) // returns usize from upper_bound (expression)
}

fn upper_bound(target: i32, list: &Vec<i32>) -> usize {

    let (mut low, mut high, mut res) = (0, list.len() - 1, list.len());

    while low <= high { // bisection

        let mid = low + (high - low) / 2;

        if list[mid] <= target { low = mid + 1; }
        else {
            res = mid;

            if mid == 0 { break; } // if mid == 0 then we stop (error if we try to give high the value -1, as it is unsigned int)
            high = mid - 1;
        }
    }

    res
}

fn get_similarity_score(file_path: &str) -> i64 {

    let mut similarity_score: i64 = 0;

    // PARSING FILE AND PUTTING DATA TO LIST (THE SAME AS PART1)
    // =============================================================================================
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
    // =============================================================================================

    // ALGORITHM FOR GETTING SIMILARITY SCORE
    // We can just iterate the left list for each element iterate the right list and get the result,
    // that would give us a time complexity of: O(N^2)
    // ---
    // A better way to do this is to sort both the left and right list and then iterate the left list
    // and for each element (with 2x bisection) get the upper and lower bound indexes of the right list 
    // for that element,
    // that would give us a time complexity of: O(NlogN) + O(NlogN) + O(N(2*logN)) = O(NlogN)
    // =============================================================================================

    // we sort both vectors
    left.sort(); // O(NlogN)
    right.sort(); // O(NlogN)

    // we iterate the left list and add values to the similarity score
    for i in 0..left.len() { // O(N(2*logN))

        similarity_score += i64::from(left[i]) * i64::try_from(upper_bound(left[i], &right) - lower_bound(left[i], &right))
                                    .expect("error getting i64 from usize");
    }

    similarity_score // we return the similarity value (this expression)
}

// PASTED FROM PART1 as it is pretty much the same (slight changes in names)
// =================================================================================================
// cargo run / cargo build + ./target/debug/HistorianHysteria
fn main() {

    let similarity_score = get_similarity_score("./tests/real.in");

    // write function output to real.res
    fs::write("./tests/real.res", similarity_score.to_string())
        .expect("Error while writing to file");
}

// cargo test
#[cfg(test)]
mod tests {
    use super::*; // da je vidna sva koda od parent (torej zunaj module-a)

    #[test]
    fn test01() {
        
        let similarity_score = get_similarity_score("./tests/test01.in");

        // write function output to test01.res
        fs::write("./tests/test01.res", similarity_score.to_string())
            .expect("Error while writing to file");

        // read the file contents from test01.out
        // and
        // assert equals for: test01.out == function output
        let correct_similarity_score = fs::read_to_string("./tests/test01.out")
            .expect("Erorr while reading file")
            .trim().parse()
                .expect("Error while parsing integer");

        assert_eq!(similarity_score, correct_similarity_score);
    }
}