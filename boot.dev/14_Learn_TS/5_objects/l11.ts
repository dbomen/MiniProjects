export type MailPreferences = {
    [key: PropertyKey]: boolean | string;
    readonly doNotDisturb: boolean;
    readonly outOfOffice: boolean;
};
