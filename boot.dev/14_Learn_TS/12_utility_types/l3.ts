export interface ContactInfo {
    email?: string;
    phoneNumber?: string;
}

export function addBillingInfo(info: Required<ContactInfo>) {
    return `billing info: ${info.email}, ${info.phoneNumber}`;
}
