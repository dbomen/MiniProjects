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
var _FeatureFlag_flags;
Object.defineProperty(exports, "__esModule", { value: true });
exports.FeatureFlag = void 0;
var FeatureFlag = /** @class */ (function () {
    function FeatureFlag() {
        _FeatureFlag_flags.set(this, void 0);
        __classPrivateFieldSet(this, _FeatureFlag_flags, new Set(), "f");
    }
    FeatureFlag.prototype.enable = function (flag) {
        __classPrivateFieldGet(this, _FeatureFlag_flags, "f").add(flag);
    };
    FeatureFlag.prototype.isEnabled = function (flag) {
        return __classPrivateFieldGet(this, _FeatureFlag_flags, "f").has(flag);
    };
    return FeatureFlag;
}());
exports.FeatureFlag = FeatureFlag;
_FeatureFlag_flags = new WeakMap();
