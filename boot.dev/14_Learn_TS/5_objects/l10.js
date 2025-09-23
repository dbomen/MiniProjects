"use strict";
var _a, _b;
Object.defineProperty(exports, "__esModule", { value: true });
exports.BIOMETRIC_LOCK = exports.TWO_FACTOR = void 0;
exports.isSecure = isSecure;
exports.TWO_FACTOR = Symbol("twoFactor");
exports.BIOMETRIC_LOCK = Symbol("biometricLock");
// don't touch above this line
function isSecure(preferences) {
    if (preferences[exports.TWO_FACTOR] || preferences[exports.BIOMETRIC_LOCK])
        return true;
    return false;
}
// TEST
console.log(isSecure((_a = {
        doNotDisturb: false,
        outOfOffice: false
    },
    _a[exports.BIOMETRIC_LOCK] = true,
    _a[exports.TWO_FACTOR] = true,
    _a.bloodType = "A+",
    _a.organDonor = true,
    _a)) + " == true");
console.log(isSecure((_b = {
        doNotDisturb: true,
        outOfOffice: false
    },
    _b[exports.BIOMETRIC_LOCK] = true,
    _b[exports.TWO_FACTOR] = false,
    _b.signature = "Jonathan Harker",
    _b.lawyer = true,
    _b)) + " == true");
