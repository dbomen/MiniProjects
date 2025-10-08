"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Customer = void 0;
var Customer = /** @class */ (function () {
    function Customer(firstName, lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    Customer.prototype.getFullName = function () {
        return this.firstName + " " + this.lastName;
    };
    return Customer;
}());
exports.Customer = Customer;
// TEST
console.log(new Customer("John", "Wick").getFullName());
