export type MailPreferences = {
    [key: PropertyKey]: boolean | string;
    doNotDisturb: boolean;
    outOfOffice: boolean;
};

export function configurePreferences(
    doNotDisturb: boolean,
    outOfOffice: boolean,
): MailPreferences;

// Do not touch below this line

export function configurePreferences(
    doNotDisturb: boolean,
    outOfOffice: boolean | string,
    useSystemTheme?: boolean,
    theme?: string,
): MailPreferences {
    if (typeof outOfOffice === "string") {
        return {
            doNotDisturb: false,
            outOfOffice: false,
            useSystemTheme: doNotDisturb,
            theme: outOfOffice,
        };
    } else if (useSystemTheme !== undefined && theme !== undefined) {
        return {
            doNotDisturb: doNotDisturb,
            outOfOffice: outOfOffice,
            useSystemTheme: useSystemTheme,
            theme: theme,
        };
    } else {
        return {
            doNotDisturb: doNotDisturb,
            outOfOffice: outOfOffice,
        };
    }
}
