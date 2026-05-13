package main

import "core:fmt"
main :: proc() {
    // SUBTYPE POLYMORPHISM (INHERITANCE-LIKE)
    foo :: proc(entity: Entity) {
        fmt.println(entity.x, entity.y, entity.z)
    }
    Entity :: struct {
        x: int,
        y: int,
        z: int,
    }
    Frog :: struct {
        ribbit_volume: f32,
        using entity:  Entity,
    }
    frog: Frog
    frog.x = 123
    foo(frog)

    // PARAMETRIC POLYMORPHISM (GENERICS / PARAPOLY)
    // explicit
    // to specify that a parameter is a compile-time constant ($ on param)
    make_f32_array :: #force_inline proc($N: int, $val: f32) -> (res: [N]f32) {
        // here N has to be known at compile-time, since array size has to be known at compile-time!
        for _, i in res {
            res[i] = val * val
        }
        return
    }
    array := make_f32_array(3, 2)

    // implicit
    // parameter type is inferred from its input ($ on type)
    print_generic :: proc(el: $T) {
        fmt.println(el)
    }
}

// used for compile-time checking (explicit/implicit param poly, array len, ...)
// eg. type restrictions, array len check, ...
where_ :: proc() {
    foo :: proc(x: [$N]int) -> bool where N > 2 {
        fmt.println(#procedure, "was called with the parameter", x)
        return true
    }
}
