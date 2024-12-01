- cargo expects that your code is in the /src folder.
- if you want to make a library you name the file /lib.rs instead of /main.rs
- in the project folder you also have to create a /Cargo.toml file, that is a configuration file for cargo
- build and run project: 
```console
$ cargo build
$ ./target/debug/cargo_hello_world
```
- with one command (re-compiles if files changed, otherwise runs the already compiled binary):
```console
$ cargo run
```
- quick-start with cargo:
```console
$ cargo new hello_world --bin
```
This generates the /Cargo.toml file and the /src directory with the /main.rs file inside.
