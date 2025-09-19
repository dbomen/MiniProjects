export function combinePrompts(systemPrompt: string, userPrompt: string) {
    return `${systemPrompt}\n${userPrompt}`;
}

console.log(combinePrompts("[SYSTEM] HELLO", "[USER] HI"));
