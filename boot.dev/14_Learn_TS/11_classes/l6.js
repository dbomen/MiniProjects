"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.RegularCustomer = void 0;
var RegularCustomer = /** @class */ (function () {
    function RegularCustomer(firstName, lastName, balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
    RegularCustomer.prototype.getFullName = function () {
        return "".concat(this.firstName, " ").concat(this.lastName);
    };
    RegularCustomer.prototype.getBalance = function () {
        return this.balance;
    };
    return RegularCustomer;
}());
exports.RegularCustomer = RegularCustomer;
