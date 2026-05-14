package main

main :: proc() {
    if str, ok := value.(string); ok {
        // ...
    } else {
        // ...
    }

    defer if 1 == 1 {}     // same as if inside defer block

    // optional return value
    func :: proc() -> (int, bool)
    func2 :: proc() -> Maybe(int)
}
