"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.updateUser = updateUser;
function updateUser(user) {
    if (user.id) {
        return "can't update id";
    }
    if (user.email) {
        return "updating email to ".concat(user.email);
    }
    return "nothing to update";
}
