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
var _RegularCustomer_balanceQueries;
Object.defineProperty(exports, "__esModule", { value: true });
exports.RegularCustomer = void 0;
var RegularCustomer = /** @class */ (function () {
    function RegularCustomer(firstName, lastName, balance) {
        _RegularCustomer_balanceQueries.set(this, void 0);
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        __classPrivateFieldSet(this, _RegularCustomer_balanceQueries, 0, "f");
    }
    RegularCustomer.prototype.getFullName = function () {
        return "".concat(this.firstName, " ").concat(this.lastName);
    };
    RegularCustomer.prototype.getBalance = function () {
        var _a;
        __classPrivateFieldSet(this, _RegularCustomer_balanceQueries, (_a = __classPrivateFieldGet(this, _RegularCustomer_balanceQueries, "f"), _a++, _a), "f");
        return this.balance;
    };
    RegularCustomer.prototype.getBalanceQueries = function () {
        return __classPrivateFieldGet(this, _RegularCustomer_balanceQueries, "f");
    };
    return RegularCustomer;
}());
exports.RegularCustomer = RegularCustomer;
_RegularCustomer_balanceQueries = new WeakMap();
// TEST
var regCustomer = new RegularCustomer("John", "Wick", 9999);
regCustomer.getBalance();
regCustomer.getBalance();
console.log(regCustomer.getBalanceQueries());
