"use strict";
var __classPrivateFieldSet = (this && this.__classPrivateFieldSet) || function (receiver, state, value, kind, f) {
    if (kind === "m") throw new TypeError("Private method is not writable");
    if (kind === "a" && !f) throw new TypeError("Private accessor was defined without a setter");
    if (typeof state === "function" ? receiver !== state || !f : !state.has(receiver)) throw new TypeError("Cannot write private member to an object whose class did not declare it");
    return (kind === "a" ? f.call(receiver, value) : f ? f.value = value : state.set(receiver, value)), value;
};
var __classPrivateFieldGet = (this && this.__classPrivateFieldGet) || function (receiver, state, kind, f) {
    if (kind === "a" && !f) throw new TypeError("Private accessor was defined without a getter");
    if (typeof state === "function" ? receiver !== state || !f : !state.has(receiver)) throw new TypeError("Cannot read private member from an object whose class did not declare it");
    return kind === "m" ? f : kind === "a" ? f.call(receiver) : f ? f.value : state.get(receiver);
};
var _Customer_balance;
Object.defineProperty(exports, "__esModule", { value: true });
exports.Customer = void 0;
var Customer = /** @class */ (function () {
    function Customer(firstName, lastName, balance) {
        _Customer_balance.set(this, void 0);
        this.firstName = firstName;
        this.lastName = lastName;
        __classPrivateFieldSet(this, _Customer_balance, balance, "f");
    }
    Customer.prototype.getFullName = function () {
        return "".concat(this.firstName, " ").concat(this.lastName);
    };
    Customer.prototype.purchase = function (cost) {
        __classPrivateFieldSet(this, _Customer_balance, __classPrivateFieldGet(this, _Customer_balance, "f") - cost, "f");
        return __classPrivateFieldGet(this, _Customer_balance, "f");
    };
    return Customer;
}());
exports.Customer = Customer;
_Customer_balance = new WeakMap();
// TEST
var customer = new Customer("John", "Wick", 100);
console.log(customer.purchase(10));
console.log(customer.purchase(50));
