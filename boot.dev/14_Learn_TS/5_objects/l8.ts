export type MailPreferences = {
    [key: string]: boolean
};

// don't touch below this line

export function setPreference(
    preferences: MailPreferences,
    key: string,
    value: boolean,
) {
    const exists = key in preferences;
    preferences[key] = value;
    return exists;
}

// TEST
const preferences = {
    darkMode: true,
    outOfOffice: true,
    doNotDisturb: true,
}
console.log(setPreference(
    preferences,
    "darkMode",
    false) + " == true");
console.log(preferences);
