export function estimateResponseTime(promptLength: number = 100, modelType: string = "text"): number {

    switch (modelType) {
        case "text": return Math.round(2 + (0.01 * promptLength));
        case "image": return Math.round(5 + (0.02 * promptLength));
        case "code": return Math.round(3 + (0.05 * promptLength));
        default: return -1;
    }
}

// TEST
console.log(estimateResponseTime() + "== 3");
console.log(estimateResponseTime(200) + "== 4");
console.log(estimateResponseTime(150, "image") + "== 8");
