"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.stripID = stripID;
function stripID(user) {
    var name = user.name, email = user.email, age = user.age;
    return { name: name, email: email, age: age };
}
