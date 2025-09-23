"use strict";
// COMPILES WITH 'tsc' BUT LSP DOES NOT LIKE IT. IDK MAYBE THE LSP IS IN SOME STRICT MODE OR SMTHN?
Object.defineProperty(exports, "__esModule", { value: true });
exports.collectSupportData = collectSupportData;
function collectSupportData(id, resolved) {
    var supportData = [];
    supportData.push("Support session started");
    supportData.push(id);
    supportData.push(resolved);
    return supportData;
}
// TEST
console.log(collectSupportData(1984, true));
console.log(collectSupportData(1138, false));
