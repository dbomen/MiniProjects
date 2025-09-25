"use strict";
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.tokenize = tokenize;
function tokenize(input) {
    var tokens = input.split(" ");
    var cost = tokens.length / 100;
    return __spreadArray([cost], tokens, true);
}
// TEST
console.log(tokenize("Dude! You got a tattoo!"));
console.log(tokenize("So do you, dude!"));
