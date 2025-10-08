"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ExecutiveMember = void 0;
var ExecutiveMember = /** @class */ (function () {
    function ExecutiveMember(balance, points) {
        this.balance = balance;
        this.points = points;
    }
    ExecutiveMember.prototype.isVip = function () {
        return (this.balance >= 10000);
    };
    ExecutiveMember.prototype.redeemPoints = function (amount) {
        if (this.points >= amount) {
            this.points -= amount;
            return true;
        }
        return false;
    };
    return ExecutiveMember;
}());
exports.ExecutiveMember = ExecutiveMember;
// TEST
var member = new ExecutiveMember(9999, 1);
console.log(member.isVip());
console.log(member.redeemPoints(2));
