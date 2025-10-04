export enum InternalErrors {
    InvalidPrompt = 1001,
    ContextWindowOverflow = 1004,
    ModelOverload = 1420,
    EthicalGuardrailTriggered = 2233,
    TokenLimitExceeded = 2401,
    SelfAwarenessAchieved = 9999,
}

// don't touch above this line

export function getErrorLabel(errCode: number): string {
    let errName: string = InternalErrors[errCode];
    if (errName !== undefined) return `${errCode}: ${InternalErrors[errCode]}`
    else return `${errCode}: Unknown error`;
}

// TEST
console.log(getErrorLabel(1001));
console.log(getErrorLabel(1004));
console.log(getErrorLabel(9999));
console.log(getErrorLabel(404));
