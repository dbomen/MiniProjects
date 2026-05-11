package main

import "core:fmt"
import "core:slice"

// theres a lot of types: https://odin-lang.org/docs/overview/#basic-types
main :: proc() {
    i: int // vars without an explicit value, are given the type appropriate zero type
    f := f64(i) // cast
    f2 := transmute(u64)f // bit cast
    i2: int = --- // explicitly uninitialize variable

    s: string : "Hello" // pointer + len
    s1: cstring : "Hello" // null terminated string (C-style)
    s2 := string(s1) // convert in O(n)
    fmt.println(len(s)) // O(1)
    fmt.println(len(s1)) // O(n)
    // more on string type conversion: https://odin-lang.org/docs/overview/#string-type-conversions

    // untyped types dont have a type but are implicitly converted to a "typed" type when needed
    I :: 42

    // doesnt have pointer arithmetic
    pt: ^int = &i // ptr to one int
    i_: int = pt^ // dereference
    pt2: rawptr // address. Has to be cast to a type to be used
    pt3: [^]int // pointer to many ints (C like ptr). Shouldnt rly be used since we have slices

    // COLLECTIONS (are bounds checked, unless u use #no_bounds_check)
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::
    arr := [5]int{1, 2, 3, 4, 5} // fixed arrays
    arr2 := [?]int{6, 7, 8, 9, 10}
    fmt.println(len(arr + arr2)) // 5 (7, 9, 11, 13, 15)
    mat := matrix[2, 3]f32{     // matrix (fixed array, but has access to some matrix API: https://odin-lang.org/docs/overview/#matrix-type)
        1, 9, -13,
        20, 5, -6,
    }

    // Dynamic array: pointer + len (current len) + cap (current underlying capacity)
    dynarr := make([dynamic]int, 0, 16) // [] 0 16
    defer delete(dynarr)
    append(&dynarr, 1, 2, 3) // [1, 2, 3] 3 16
    inject_at(&dynarr, 0, 10) // [10, 1, 2, 3] 4 16
    inject_at(&dynarr, 5, 10) // [10, 1, 2, 3, 0, 10] 6 16
    pop(&dynarr) // O(1): [10, 1, 2, 3, 0] 5 16
    unordered_remove(&dynarr, 0) // O(1): [0, 1, 2, 3] 4 16 (takes the last element and puts it at the index)
    ordered_remove(&dynarr, 0) // O(n): [1, 2, 3] 3 16
    clear(&dynarr) // O(1): [] 0 16
    // more API here: https://odin-lang.org/docs/overview/#dynamic-arrays

    // Slice: pointer + runtime length.
    // Here it starts with len(arr), but that length is not part of the type.
    // Once passed as []int, code must treat the length as runtime data.
    // can slice fixed arrays or dynamic arrays
    sl: []int = arr[:]
    slice.sort(sl)
    sl2 := []int{1, 2, 3, 4, 5} // create backing memory
    sl3 := make([]int, 5) // backing memory on the heap
    defer delete(sl3)

    // Map
    m := make(map[string]int)
    defer delete(m)
    m["Bob"] = 2
    delete_key(&m, "Bob")
    ok := "Bob" in m // false
    // has all (or alot) API as dynamic arrays
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::

    // RECORDS
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // Enums
    Permission :: enum {
        Read,
        Write,
        Execute,
    }
    for perm, index in Permission {}

    // Bit sets (on/off flags for enum or range of values)
    Permissions :: bit_set[Permission]
    p: Permissions = {.Read}
    p2: Permissions = {.Write}
    if .Read in p {}     // has read perms
    if p + p2 == {.Read + .Write} {}     // has read and write perms

    // Structs, more: https://odin-lang.org/docs/overview/#structs
    //odinfmt: disable
    Vector3   :: distinct struct { x, y, z: f32 } // distinct type
    Vector3_  :: struct { x, y, z: f32 } // type alias
    //odinfmt: enable
    v := Vector3{} // zeroed values

    // Bit fields (compact bit struct)
    IPv4_First_Byte :: bit_field u8 {
        ihl:     u8 | 4, // accessed as u8, stored in 4 bits
        version: u8 | 4,
    }

    // Unions, more: https://odin-lang.org/docs/overview/#unions
    //odinfmt: disable
    Value :: union { bool, i32, f32, string }
    //odinfmt: enable
    value: Value = "Hellope"
    switch v in value {
    case string:
        #assert(type_of(v) == string)
    case bool:
        #assert(type_of(v) == bool)
    case i32, f32:
        #assert(type_of(v) == Value)
    case: // = nil
    }
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::
}
