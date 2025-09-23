export function getFileLength(files: Map<string, string>, filename: string) {

    const byteSize = (str: string) => new Blob([str]).size;

    for (const [_filename, fileContents] of files) {
        if (filename === _filename) {
            return byteSize(fileContents);
        }
    }
}

// TEST
console.log(getFileLength(
    new Map<string, string>([
        ["treasure.txt", "He left it all in One Piece"],
        ["ships_log.txt", "Weather: sunny"],
    ]),
    "ships_log.txt") + " == 14");

console.log(getFileLength(
    new Map<string, string>([
        ["treasure.txt", "He left it all in One Piece"],
        ["ships_log.txt", "Weather: sunny"],
        ["flags.txt", "🏴‍☠️, 🏴‍☠️, 🏴‍☠️"],
    ]),
    "flags.txt") + " == 43");
