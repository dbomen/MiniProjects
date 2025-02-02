use super::*; // da je vidna sva koda od parent (torej zunaj module-a)

#[test]
fn test01() {
    
    let mul_result = solution::get_compacted_hard_drive_filesystem_checksum(get_reader("./tests/test01.in"));

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

#[test]
fn test02() {
    
    let mul_result = solution::get_compacted_hard_drive_filesystem_checksum(get_reader("./tests/test02.in"));

    // write function output to test02.res
    fs::write("./tests/test02.res", mul_result.to_string())
        .expect("Error while writing to file");

    // read the file contents from test02.out
    // and
    // assert equals for: test02.out == function output
    let correct_mul_result = fs::read_to_string("./tests/test02.out")
        .expect("Erorr while reading file")
        .trim().parse()
            .expect("Error while parsing integer");

    assert_eq!(mul_result, correct_mul_result);
}

#[test]
fn test03() {
    
    let mul_result = solution::get_compacted_hard_drive_filesystem_checksum(get_reader("./tests/test03.in"));

    // write function output to test02.res
    fs::write("./tests/test03.res", mul_result.to_string())
        .expect("Error while writing to file");

    // read the file contents from test02.out
    // and
    // assert equals for: test02.out == function output
    let correct_mul_result = fs::read_to_string("./tests/test03.out")
        .expect("Erorr while reading file")
        .trim().parse()
            .expect("Error while parsing integer");

    assert_eq!(mul_result, correct_mul_result);
}

#[test]
fn test04() {
    
    let mul_result = solution::get_compacted_hard_drive_filesystem_checksum(get_reader("./tests/test04.in"));

    // write function output to test02.res
    fs::write("./tests/test04.res", mul_result.to_string())
        .expect("Error while writing to file");

    // read the file contents from test02.out
    // and
    // assert equals for: test02.out == function output
    let correct_mul_result = fs::read_to_string("./tests/test04.out")
        .expect("Erorr while reading file")
        .trim().parse()
            .expect("Error while parsing integer");

    assert_eq!(mul_result, correct_mul_result);
}

#[test]
fn test05() {
    
    let mul_result = solution::get_compacted_hard_drive_filesystem_checksum(get_reader("./tests/test05.in"));

    // write function output to test02.res
    fs::write("./tests/test05.res", mul_result.to_string())
        .expect("Error while writing to file");

    // read the file contents from test02.out
    // and
    // assert equals for: test02.out == function output
    let correct_mul_result = fs::read_to_string("./tests/test05.out")
        .expect("Erorr while reading file")
        .trim().parse()
            .expect("Error while parsing integer");

    assert_eq!(mul_result, correct_mul_result);
}