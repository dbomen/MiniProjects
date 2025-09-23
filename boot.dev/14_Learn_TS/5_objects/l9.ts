export type MailPreferences = {
    doNotDisturb: boolean;
    outOfOffice: boolean;
    [key: string]: boolean | string;
};

// don't touch below this line

export function canContact(preferences: MailPreferences): boolean {
    return !(preferences.doNotDisturb || preferences.outOfOffice);
}

// TEST
console.log(canContact(
    {
        doNotDisturb: false,
        outOfOffice: false,
        theme: "dark",
    } as MailPreferences) + " == true");

console.log(canContact(
    {
        doNotDisturb: true,
        outOfOffice: false,
        delayDelivery: true,
    } as MailPreferences) + " == false");
