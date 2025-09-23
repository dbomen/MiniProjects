export const TWO_FACTOR = Symbol("twoFactor");
export const BIOMETRIC_LOCK = Symbol("biometricLock");

export type MailPreferences = {
    [key: PropertyKey]: boolean | string;
    doNotDisturb: boolean;
    outOfOffice: boolean;
    [TWO_FACTOR]: boolean;
    [BIOMETRIC_LOCK]: boolean;
};

// don't touch above this line

export function isSecure(preferences: MailPreferences) {
    if (preferences[TWO_FACTOR] || preferences[BIOMETRIC_LOCK]) return true;
    return false;
}

// TEST
console.log(isSecure(
    {
        doNotDisturb: false,
        outOfOffice: false,
        [BIOMETRIC_LOCK]: true,
        [TWO_FACTOR]: true,
        bloodType: "A+",
        organDonor: true,
    } as MailPreferences) + " == true");

console.log(isSecure(
    {
        doNotDisturb: true,
        outOfOffice: false,
        [BIOMETRIC_LOCK]: true,
        [TWO_FACTOR]: false,
        signature: "Jonathan Harker",
        lawyer: true,
    } as MailPreferences) + " == true");
