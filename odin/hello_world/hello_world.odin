package main// colection of odin files

import "core:fmt"
import foo "core:fmt"

@(private)
private_to_package: int = 123
@(private = "file")
private_to_file: int = 123

main :: proc() {
    x := "Hello World!"
    //x := "Hellope!"
    x = "Hellope!"
    fmt.println(x)
    fmt.println(len(x))

    y: int = 1_000_000
    foo.println(y)
    foo.println(1e9)
    foo.println(1.0e9)
    foo.println(0xdead)

    constant: int : 123 + 1
    constant2 :: "A"
}
