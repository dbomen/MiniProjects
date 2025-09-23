"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.setPreference = setPreference;
// don't touch below this line
function setPreference(preferences, key, value) {
    var exists = key in preferences;
    preferences[key] = value;
    return exists;
}
// TEST
var preferences = {
    darkMode: true,
    outOfOffice: true,
    doNotDisturb: true,
};
console.log(setPreference(preferences, "darkMode", false) + " == true");
console.log(preferences);
