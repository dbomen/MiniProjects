use std::{fs, io};
use std::io::BufRead;

use std::collections::{BTreeSet, BTreeMap};

// SOLVING THE PROBLEM
// N - number of page ordering rules
// M - number of page updates
// l - number of page_numbers in page update
// ===
// Map page_numbers -> Set of before_page_numbers (we only need to hold the before_page_numbers as
// we will only check these while iterating the page updates)
// We iterate page updates and check if its valid -> O(M) * O(l^2 * 1) -> O(M * l^2)
// =================================================================================================
struct PageNumberSets {

    before_page_numbers: BTreeSet<i32>,
}

impl PageNumberSets {

    fn new() -> PageNumberSets {

        PageNumberSets {

            before_page_numbers: BTreeSet::new(),
        }
    }
}

fn get_sum_of_middle_numbers_in_valid_updates(reader: io::BufReader<fs::File>) -> i64 {

    let mut sum_of_middle_numbers: i64 = 0;

    let mut page_numbers_map: BTreeMap<i32, PageNumberSets> = BTreeMap::new();

    // get page number ordering rules
    let mut line_iter = reader.lines().map(|line| line.expect("ERROR GETTING LINE FROM READER"));
    loop {

        let line = line_iter.next().expect("INVALID INPUT. ERROR GETTING LINE FROM ITERATOR");

        if line.eq("") { break; }

        let first_page: i32 = (&line[..2]).parse().unwrap();
        let second_page: i32 = (&line[3..]).parse().unwrap();

        // (2) add first_page to second_page before_page_numbers 
        match page_numbers_map.get_mut(&second_page) {

            Some(second_page_sets) => {

                second_page_sets.before_page_numbers.insert(first_page);
            }
            None => {

                // if not yet created, we create it and add the element
                let mut new_page_number_sets: PageNumberSets = PageNumberSets::new();
                new_page_number_sets.before_page_numbers.insert(first_page);

                page_numbers_map.insert(second_page, new_page_number_sets);
            }
        }
    }

    // iterate updates and get the sum
    'updates: loop  { // M times

        let line = match line_iter.next() { // we read lines until the iterator reaches end

            Some(l) => l,
            None => break,
        };

        // we get the page update page numbers
        let page_numbers: Vec<&str> = line.split(',').collect();
        for i in 0..page_numbers.len() { // l times

            for j in i + 1..page_numbers.len() { // l-1 || l-2 || ... || 1 times = l times

                let page_number_i: i32 = page_numbers.get(i).unwrap().parse().expect("ERROR PARSING");
                let page_number_j: i32 = page_numbers.get(j).unwrap().parse().expect("ERROR PARSING");

                if let Some(page_number_sets) = page_numbers_map.get(&page_number_i) {

                    if page_number_sets.before_page_numbers.contains(&page_number_j) { continue 'updates; }
                }
            }
        }

        // if we are here => update is valid
        let middle_page_number: i64 = page_numbers.get(page_numbers.len() / 2).unwrap().parse().expect("ERROR PARSING");
        sum_of_middle_numbers += middle_page_number;
    }

    sum_of_middle_numbers
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

    let mul_result = get_sum_of_middle_numbers_in_valid_updates(get_reader("./tests/real.in"));

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
        
        let mul_result = get_sum_of_middle_numbers_in_valid_updates(get_reader("./tests/test01.in"));

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