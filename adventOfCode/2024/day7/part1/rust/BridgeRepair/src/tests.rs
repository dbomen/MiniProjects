use super::*; // da je vidna sva koda od parent (torej zunaj module-a)

#[test]
fn test01() {
    
    let mul_result = solution::get_total_calibration_result(get_reader("./tests/test01.in"));

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