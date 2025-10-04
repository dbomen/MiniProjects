"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.InternalErrors = void 0;
exports.getErrorLabel = getErrorLabel;
var InternalErrors;
(function (InternalErrors) {
    InternalErrors[InternalErrors["InvalidPrompt"] = 1001] = "InvalidPrompt";
    InternalErrors[InternalErrors["ContextWindowOverflow"] = 1004] = "ContextWindowOverflow";
    InternalErrors[InternalErrors["ModelOverload"] = 1420] = "ModelOverload";
    InternalErrors[InternalErrors["EthicalGuardrailTriggered"] = 2233] = "EthicalGuardrailTriggered";
    InternalErrors[InternalErrors["TokenLimitExceeded"] = 2401] = "TokenLimitExceeded";
    InternalErrors[InternalErrors["SelfAwarenessAchieved"] = 9999] = "SelfAwarenessAchieved";
})(InternalErrors || (exports.InternalErrors = InternalErrors = {}));
// don't touch above this line
function getErrorLabel(errCode) {
    var errName = InternalErrors[errCode];
    if (errName !== undefined)
        return "".concat(errCode, ": ").concat(InternalErrors[errCode]);
    else
        return "".concat(errCode, ": Unknown error");
}
// TEST
console.log(getErrorLabel(1001));
console.log(getErrorLabel(1004));
console.log(getErrorLabel(9999));
console.log(getErrorLabel(404));
