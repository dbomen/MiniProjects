export function formatLabels(...labels: string[]) {

    if (labels.length == 0) return "No Labels";
    if (labels.length == 1) return `Label: ${labels[0]}`

    let sb = "Labels: ";
    for (let i = 0; i < labels.length; i++) {
        if (i != 0) sb += ", ";
        sb += labels[i];
    }
    return sb;
}

// TEST
console.log(formatLabels("urgent", "critical", "important"));
console.log(formatLabels("wontfix"));
console.log(formatLabels());
