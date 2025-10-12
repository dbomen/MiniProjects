"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.runNext = runNext;
exports.createQueue = createQueue;
function runNext(q) {
    return q.next();
}
function createQueue() {
    var jobs = [];
    return {
        push: function (job) {
            jobs.push(job);
        },
        next: function () {
            return jobs.shift();
        },
    };
}
