export type MailPreferences = {
    [key: PropertyKey]: boolean | string;
    doNotDisturb: boolean;
    outOfOffice: boolean;
};

// don't touch above this line

export const newMailPreferences = {
    doNotDisturb: false,
    outOfOffice: false,
    signature: "Boots, Support.ai Wizard Bear",
    theme: "dark",
} satisfies MailPreferences;
