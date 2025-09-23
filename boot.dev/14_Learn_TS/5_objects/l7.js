"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getFileLength = getFileLength;
function getFileLength(files, filename) {
    var byteSize = function(str) { return new Blob([str]).size; };
    for (var _i = 0, files_1 = files; _i < files_1.length; _i++) {
        var _a = files_1[_i], _filename = _a[0], fileContents = _a[1];
        if (filename === _filename) {
            return byteSize(fileContents);
        }
    }
}
// TEST
console.log(getFileLength(new Map([
    ["treasure.txt", "He left it all in One Piece"],
    ["ships_log.txt", "Weather: sunny"],
]), "ships_log.txt") + " == 14");
console.log(getFileLength(new Map([
    ["treasure.txt", "He left it all in One Piece"],
    ["ships_log.txt", "Weather: sunny"],
    ["flags.txt", "🏴‍☠️, 🏴‍☠️, 🏴‍☠️"],
]), "flags.txt") + " == 43");
