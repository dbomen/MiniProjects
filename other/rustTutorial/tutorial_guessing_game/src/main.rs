// v Rustu imamo crates. Crates so a package of Rust code. Poznamo BINARY in LIBRARY crates.
// Binary crates are exectuables, Library crates pa vsebuje kodo, ki naj bi bila uporabljena
// v nasem projektu.
// v Cargo.toml dodamo dependency: 'rand = "0.3.0"', kar je shorthand for ^0.3.0, kar pomeni,
// da naj importa anything compatible with 0.3.0. Ce hocemo nek specific version bi dali '=0.3.0'.
// Cargo fetches a copy of data from Crates.io, kjer so vsi crates, kamor people dajejo svoje open-source projects.
// Ceprav smo rekli, da rabimo samo rand, se importa a copy of libc, saj rand DEPENDS ON libc.

// Kaj pa ce pride new version in vse breaka? Ali bo Cargo kar ta new version importal? NE
// Cargo shrani v Cargo.lock file, ko buildamo for the first time, in ugotovi all the versions
// that fit our criteria in jih napise v ta file. When we build the project in the future, bo
// Cargo pogledal v Cargo.lock in bo uzel tiste version, tudi ce so new versions. Ce hocemo uporabljati
// new versions, uporabimo command update, s cim povemo "ignore the lock, figure out the latest versions
// that fit what we've specified. If that works write them to Cargo.lock". To bi v nasem primeru za
// rand delovalo samo za 0.3.x version. Ce recimo obstaja 0.4.x version in hocemo upgradat
// moramo updatat Cargo.toml file directly. Ko potem buildamo bo Cargo updatal index in vse porihtal.
extern crate rand;

// by default Rust importa "the prelude", kar je nek minimalni standardni nabor knjiznic
// kar ni v prelude-u rabimo importat z besedico 'use'
use std::io; // uporabljamo io library

// IDEA: methods are defined on 'traits' and for the method to work, it needs the trait to be in scope.
use rand::Rng; // uporabljamo Rng 'trait'

use std::cmp::Ordering; // damo type Ordering into scope

// main function, entry point to the program
// no return type => je return type () - an empty touple
fn main() {

    println!("Guess the number!"); // macro, ki printa text to the screen

    // deklariramo immutable variable binding named secret_number
    // ---
    // with the function rand::thread_rng(), dobimo a copy of a random number generator, which
    // is local to the particular thread of execution we're in.
    // ---
    // ker smo uporabili rand::Rng imamo na voljo metodo 'gen_range', ki vzame 2 numbers
    // in generira a number between them (low: inclusive, high: exclusive).
    // Za type, Rust inferes i32 (32bit integer), kar je default, lahko bi pa tudi explicitno povedali.
    // --Ampak tukaj infere-a u32 im guessing, da zato ker bo potem v programu primerjal z u32--
    let secret_number = rand::thread_rng().gen_range(1, 101);

    // to je infinite loop. V drugih jezikih bi lahko samo dali while(true){}, ampak bi
    // v Rustu nebi compile-alo to
    loop {

        println!("Please input your guess.");

        // 'let' => creates variable bindings
        // by default je variable binding immutable, ce damo 'mut' je mutable
        // we create a mutable binding named guess and THAT IS BOUND TO a String
        // ---
        // String is a type provided by the std library (growable text)
        // ::new() je 'associated function' (pravzaprav static function), ker je associated
        // with the type (in this case String) itself, ne pa with a particular instance of that type.
        // new() creates a new empty String
        let mut guess = String::new(); // the type String is infered. Ni treba explicitno

        // 'io::stdin()' => je associated function, ki vrne handle to the std input za nas terminal 
        // (ce nebi dali 'use' prej, bi mogli napisat std::io::stdin())
        // ---
        // '.read_line(&mut guess)' => poklicemo .read_line() metodo on our handle. Metode so kot associated function
        // (aka static functions), samo da so available za particular instance of a type.
        // passa-amo tudi parameter &mut guess, saj read_line() ne accepta 'String' ampak '&mut String'
        // To je REFERENCE (kot nek pointer) na String. REFERENCEs are immutable by default zato damo
        // &mut guess namesto &guess. read_line rabi mutable reference, zato ker bo rabu v ta String
        // buffer pisat, kar dobi v vhodu.
        // ---
        // read_line vrne 'Result', ki je prav tako type in ima svoje metode. The purpose of this return
        // type je, da ce je kej narobe lahko v tem typu encodamo error handling info. Ena od teh metod
        // je .expect(), ki ce value v Result ni a successful one potem naredi 'panic!' in crasha
        // program, display your passed in message. Ce ne callamo expect() => se compila ampak dobimo warning,
        // saj nismo uporabili Result type-a (Rust z tem pove, da nismo handle-ali a possible error).
        io::stdin().read_line(&mut guess)
            .expect("Failed to read line");

        // Convertamo String -> int (u32, kar smo explicitno napisali)
        // ---
        // Ali nimamo ze variable za guess? DA, res je ampak Rust dovoljuje, da 'shadow'-amo prejsno
        // guess z novo (we re-use the name). To se uporablja, ko prejsne ne uporabljamo vec, v nasem primeru 
        // res ne saj nas zanima samo int value od kar smo dobili na vhodu.
        // So we bind guess to an expression 'guess.trim().parse()', 'guess' referes to the old guess.
        // trim() - da stran whitespace (tudi npr '\n', ki je tudi del Stringa, ko kliknemo enter)
        // 'let guess: u32' - ':' after guess tells Rust that we are going to annotate its type.
        // ---
        // parse() - parses the string to some kind of number. Podobno, kot 'read_line()', lahko
        // 'parse()' cause-a an error (npr, ce nemormo parsat stevila iz Stringa, ker ga pac ni noter),
        // zato damo '.expect()', da crash-a, ce je error with our message (dobili bi warning ce nebi handle-ali ta possible error).
        // OLD CODE: crash if non-number
        // let guess: u32 = guess.trim().parse()
        //     .expect("Please type a number!");
        // NEW CODE: ignore if non-number
        // To je klasicno kaj naredimo, ce hocemo iti iz 'crash on error' v 'handle on error'
        // Namesto, da delamo '.expect()' nad Result, matche-amo ta Result, ki ga dobimo iz '.parse()'.
        // Result je enum (kot Ordering) ter ima vsak variant (Ok in Err) nek value zraven: 
        // Ok: is success, Err: is failure.
        let guess: u32 = match guess.trim().parse() {

            // 'Ok' ma wrappan nek type (v nasem primeru u32), ki ga potem returnamo
            Ok(num) => num,
            // 'Err' pa ima wrappan nek Error type, nam je vseeno kaksen error je (zato damo '_', namesto imena)
            // kar pomeni da se matcha z vsem kar ni Ok. Potem pa continue-amo zanko (da gremo v novo iteracijo zanke) 
            Err(_)  => continue,
        };

        // '{}' je samo placeholder, kateremu passamo argument. Lahko imas tudi vec '{}' in passas
        // vec argumentov
        println!("You guessed: {}", guess);

        // cmp() method can be called on anything that can be compared and it takes a reference to the
        // thing you want to compare it to. Returna Ordering type (ki smo ga 'use'-ali zgoraj).
        // Seveda morata bit oba elementa istega tipa!
        // ---
        // Uporabimo match statement, da ugotovimo kaksen Ordering je.
        // Ordering je enum (values: Less, Greater, Equal). Match statement takes a value of a type
        // and lets you create an 'arm' for each possible value (torej kaj naredit za vsak value).
        // (Match je podoben switchu, vendar je baje more useful)
        match guess.cmp(&secret_number) {
            Ordering::Less    => println!("Too small!"), // if Less, we print "Too small!"
            Ordering::Greater => println!("Too big!"),
            Ordering::Equal   => {
                println!("You win!");
                break; // we break out of the loop (thus ending the game and the program) if the user wins the game
            }
        }    
    }
}
