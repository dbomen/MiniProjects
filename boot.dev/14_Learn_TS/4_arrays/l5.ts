// COMPILES WITH 'tsc' BUT LSP DOES NOT LIKE IT. IDK MAYBE THE LSP IS IN SOME STRICT MODE OR SMTHN?

export function collectSupportData(id: number, resolved: boolean) {
    const supportData = [];

    supportData.push("Support session started");
    supportData.push(id);
    supportData.push(resolved);

    return supportData;
}

// TEST
console.log(collectSupportData(1984, true));
console.log(collectSupportData(1138, false));
