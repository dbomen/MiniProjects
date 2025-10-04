"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getRateLimitForTier = getRateLimitForTier;
function getRateLimitForTier(tier) {
    switch (tier) {
        case "Free":
            return 10;
        case "Basic":
            return 100;
        case "Pro":
            return 1000;
        case "Enterprise":
            return 10000;
    }
}
