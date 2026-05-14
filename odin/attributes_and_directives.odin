package main

import "native:libc"

// attributes: are made on declarations, more: https://odin-lang.org/docs/overview/#attributes
@(private)
package_private_func :: proc() {}
@(private = "file")
file_private_func :: proc() {}

@(test)
test_func :: proc() {}     // called when odin test

@(require_results) // requires caller to handle result
load_texture :: proc(path: string) -> ([256]int, bool) {
    return [256]int{}, false
}

@(deprecated = "use func instead") // requires caller to handle result
deprecated_func :: proc() {}

counter :: proc() -> int {
    @(static) value := 0 // static local var
    value += 1
    return value
}

@(rodata) // exist in rodata (read-only data). runtime constants
Enemy_Names := []string{"slime", "bat", "goblin"}

@(export, link_name = "add_numbers") // export function with c name (linkname)
add_numbers :: proc "c" (a, b: i32) -> i32 {
    return a + b
}
foreign libc {     // import C function
    @(link_name = "puts")
    c_puts :: proc(s: cstring) -> i32 ---
}

@(thread_local) // each thread has copy
thread_temp_buffer: [1024]u8


// directives: change compiler behavior, more: https://odin-lang.org/docs/overview/#directives
#assert(true) // compile-time assert. assert is runtime assert

DEBUG_LOGGING :: #config(DEBUG_LOGGING, false) // compile configuration: odin run . -define:DEBUG_LOGGING=true
main :: proc() {
    when DEBUG_LOGGING {
        fmt.println("debug logging enabled")
    }
}

shader_source :: #load("shader.glsl", string) // load file contents into var

m :: proc() {
    State :: enum {
        Idle,
        Walking,
        Attacking,
    }
    handle :: proc(s: State) {
        #partial switch s {     // ignore non-exhausting switch statement
        case .Attacking:
            fmt.println("attack")
        }
    }
}

// debug (there are more)
check :: proc(ok: bool, expr := #caller_expression(ok), loc := #caller_location) {
    if !ok {
        fmt.printf("check failed at %s:%d: %s\n", loc.file, loc.line, expr)
        // eg.: check failed at m.odin:12: x < 5
    }
}
m :: proc() {
    x := 10
    check(x < 5)
}

Header :: struct #packed {
    // removes padding between fields (slower access tho)
    magic:   u16,
    version: u8,
    flags:   u8,
}

Value_Bits :: struct #raw_union {
    // C unions (fields share memory)
    i: i32,
    f: f32,
    u: u32,
}

square :: #force_inline proc(x: f32) -> f32 {     // for hot funcs
    return x * x
}
