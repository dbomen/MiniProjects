"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.reportOrders = reportOrders;
function sumOrders(orders) {
    var sum = 0;
    for (var _i = 0, orders_1 = orders; _i < orders_1.length; _i++) {
        var order = orders_1[_i];
        sum += order.amount;
    }
    return sum;
}
function reportOrders(orders) {
    return "Total amount for orders: ".concat(sumOrders(orders));
}
// TEST
console.log(reportOrders([
    {
        id: "19045",
        accountType: "free",
        amount: 0,
        contact: {
            email: "shelly@doubler.com",
            phone: "0123456789",
        },
    },
    {
        id: "28991",
        accountType: "premium",
        amount: 99,
        contact: {
            email: "ben@oneyedjacks.com",
            phone: "0111226789",
        },
    },
    {
        id: "31772",
        accountType: "premium",
        amount: 149,
        contact: {
            email: "sarah@example.com",
            phone: "0555666777",
        },
    },
]) + " == 248");
