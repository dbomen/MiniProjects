export function formatAddresses(addresses) {
    let formatted = "";
    for (const address of addresses) {
        if (address.kind === "internal") {
            formatted += `${address.firstName}.${address.lastName}@support.ai, `;
        }
        if (address.kind === "external") {
            formatted += `${address.username}@${address.domain}, `;
        }
    }
    return formatted.slice(0, -2);
}
