package main

import "core:fmt"

// same calling convention as C, but:
//  - if more efficient it promotes value to a pointer (can be done since all params are immutable)
//  - it includes a pointer to the current context as an implicit additional argument
// You can change calling conventions. Its C's calling convention in foreign blocks: https://odin-lang.org/docs/overview/#calling-conventions

// parameters and named results can have default values
f :: proc(a: int = 0) -> int {     // parameters are immutable by default
    a := a // explicit mutation (also shadowing)
    return 0
}
sum :: proc(nums: ..int) -> (result: int, err := 0) {
    for n in nums do result += n
    return
}

// procedure overloading
bool_to_string :: proc(b: bool) -> string {return "bool"}
int_to_string :: proc(i: int) -> string {return "int"}
to_string :: proc {
    bool_to_string,
    int_to_string,
}

main :: proc() {
    f(a = 1)
    callback :: proc(x: int) -> bool

    fmt.println(sum()) // 0
    fmt.println(sum(1, 2, 3, 4, 5)) // 15

    odds := []int{1, 3, 5}
    fmt.println(sum(..odds)) // 9, passing a slice as varargs
}
