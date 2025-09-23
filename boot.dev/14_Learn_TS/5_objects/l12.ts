export const defaultPreferences = {
    name: "Kreese",
    doNotDisturb: false,
    outOfOffice: false,
} as const;

// TEST
defaultPreferences.name = "a";
