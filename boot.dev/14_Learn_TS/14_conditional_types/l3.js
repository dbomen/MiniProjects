"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.resetForm = resetForm;
function resetForm(form) {
    var result = {};
    for (var key in form) {
        result[key] = null;
    }
    return result;
}
console.log(resetForm({
    applicant: "Abigail",
    licenseType: "mining",
    yearsRequested: 2,
}));
