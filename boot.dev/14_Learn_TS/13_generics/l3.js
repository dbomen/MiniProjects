"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.pluckEmails = pluckEmails;
function pluckEmails(arr) {
    var out = [];
    for (var _i = 0, arr_1 = arr; _i < arr_1.length; _i++) {
        var el = arr_1[_i];
        out.push(el.email);
    }
    return out;
}
