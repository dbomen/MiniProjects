"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.RegularCustomer = exports.Customer = void 0;
var Customer = /** @class */ (function () {
    function Customer(firstName, lastName, balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
    Customer.prototype.getFullName = function () {
        return "".concat(this.firstName, " ").concat(this.lastName);
    };
    return Customer;
}());
exports.Customer = Customer;
var RegularCustomer = /** @class */ (function (_super) {
    __extends(RegularCustomer, _super);
    function RegularCustomer(firstName, lastName, balance) {
        return _super.call(this, firstName, lastName, balance) || this;
    }
    RegularCustomer.prototype.getBalance = function () {
        return this.balance;
    };
    return RegularCustomer;
}(Customer));
exports.RegularCustomer = RegularCustomer;
