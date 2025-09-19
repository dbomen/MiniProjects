"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.combinePrompts = combinePrompts;
function combinePrompts(systemPrompt, userPrompt) {
    return "".concat(systemPrompt, "\n").concat(userPrompt);
}
console.log(combinePrompts("[SYSTEM] HELLO", "[USER] HI"));
