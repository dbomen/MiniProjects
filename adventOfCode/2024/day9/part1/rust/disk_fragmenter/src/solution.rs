// ==========
// - We get all files (Space) into a VecDeque and all free space (Space) into a seperate VecDeque.
// - we continue with the following algorithm until there is no more free space:
//  - pop_front() a file and add calculate checksum [first file]
//  - pop_front() a free space [first free space]
//      - pop_back() files until free space is "filled" and calculate checksums [last file]
// - calculate the remaining checksum

// T: O(N)
// ==========
use std::{collections::VecDeque, fs, io::{self, Read}};

#[derive(Debug)]
struct Space(usize, usize); // (start-including, end-excluding)

struct File {

    id: usize,
    space: Space
}

/// ID * (sum of range) = ID * [(end - start)/2 * (start + end - 1)]
fn get_checksum_for_range(id: usize, space: &Space) -> i64 {

    if (space.1 - space.0) % 2 == 0 {

        // println!("==0 | id: {}, space: {:?}, {}", id, space, (id * (((space.1 - space.0) / 2) * (space.0 + space.1 - 1))));
        (id * (((space.1 - space.0) / 2) * (space.0 + space.1 - 1))) as i64
    }
    else {

        // println!("==1 | id: {}, space: {:?}, {}", id, space, (id * (((space.1 - space.0) / 2) * (space.0 + space.1 - 1) + ((space.0 + space.1 - 1) / 2))));
        (id * (((space.1 - space.0) / 2) * (space.0 + space.1 - 1) + ((space.0 + space.1 - 1) / 2))) as i64
    }
}

pub fn get_compacted_hard_drive_filesystem_checksum(mut reader: io::BufReader<fs::File>) -> i64 {

    // SETUP
    // ---
    let mut disk_map: String = String::new();
    reader.read_to_string(&mut disk_map).expect("Error reading from file to String");

    let mut files: VecDeque<File> = VecDeque::new();
    let mut free_spaces: VecDeque<Space> = VecDeque::new();

    let mut position: usize = 0;
    for (i, c) in disk_map.chars().enumerate() {

        let len: usize = c as usize - '0' as usize;
        if len == 0 { continue; }

        if i % 2 == 0 { files.push_back(File { id: i / 2, space: Space(position, position + len) }); } // file
        else          { free_spaces.push_back(Space(position, position + len)); }                      // free space

        position += len;
    }
    // ---

    let mut checksum: i64 = 0;

    // we fill the free space and calculate checksums
    while free_spaces.len() > 0 && files.len() > 0 {

        let mut free_space = free_spaces.pop_front().unwrap();

        // we calculate checksum until first free space
        while files.len() > 0 {

            let file = files.pop_front().unwrap();
            checksum += get_checksum_for_range(file.id, &file.space);

            if file.space.1 == free_space.0 { break; } // when we get to the free space we handle it
        }

        // we "fill" the freespace and calculate the checksum
        while free_space.1 > free_space.0 && files.len() > 0 {

            let mut file = files.pop_back().unwrap();

            let file_len = file.space.1 - file.space.0;
            let free_space_len = free_space.1 - free_space.0;

            // we fill the free space and calculate checksum
            if free_space_len > file_len {

                checksum += get_checksum_for_range(file.id, &Space(free_space.0, free_space.0 + file_len)); // we partially "fill" the free space
                free_space.0 += file_len; // we fix the free space start position
            }
            else {

                checksum += get_checksum_for_range(file.id, &free_space);
                free_space.0 = free_space.1; // we fix the free space start position

                file.space.1 -= free_space_len; // we fix the file end position
                if file.space.1 > file.space.0 { files.push_back(file); } // if the file is not empty, we add it back
            }
        }
    }

    // we calculate the remaining checksums
    while files.len() > 0 {

        let file = files.pop_front().unwrap();
        checksum += get_checksum_for_range(file.id, &file.space);
    }
    
    checksum
}