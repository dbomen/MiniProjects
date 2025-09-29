"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// TEST
console.log({
    name: "EchoBot",
    status: "online",
    reply: function (text) { return "Echo: ".concat(text); },
    summarize: function (lines) { return "Summary: ".concat(lines.join(" / ")); },
    takeAction: function (action) { return console.log("Action: ".concat(action)); },
    shutdown: function () { return "Shutting down EchoBot..."; },
}.shutdown());
