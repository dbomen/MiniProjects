"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.canContact = canContact;
// don't touch below this line
function canContact(preferences) {
    return !(preferences.doNotDisturb || preferences.outOfOffice);
}
// TEST
console.log(canContact({
    doNotDisturb: false,
    outOfOffice: false,
    theme: "dark",
}) + " == true");
console.log(canContact({
    doNotDisturb: true,
    outOfOffice: false,
    delayDelivery: true,
}) + " == false");
