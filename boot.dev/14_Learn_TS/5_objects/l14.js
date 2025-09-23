"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.configurePreferences = configurePreferences;
// Do not touch below this line
function configurePreferences(doNotDisturb, outOfOffice, useSystemTheme, theme) {
    if (typeof outOfOffice === "string") {
        return {
            doNotDisturb: false,
            outOfOffice: false,
            useSystemTheme: doNotDisturb,
            theme: outOfOffice,
        };
    }
    else if (useSystemTheme !== undefined && theme !== undefined) {
        return {
            doNotDisturb: doNotDisturb,
            outOfOffice: outOfOffice,
            useSystemTheme: useSystemTheme,
            theme: theme,
        };
    }
    else {
        return {
            doNotDisturb: doNotDisturb,
            outOfOffice: outOfOffice,
        };
    }
}
