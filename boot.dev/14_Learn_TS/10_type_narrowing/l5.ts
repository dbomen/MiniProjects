type ModelSkippity = {
    version: "3.5" | "4" | "4s";
    search: boolean;
};

type ModelJean = {
    version: "2" | "3" | "3.14";
    think: boolean;
};

export type Model = ModelSkippity | ModelJean;

function isModelSkippity(model: Model): model is ModelSkippity {
    return (
        "search" in model &&
        (model.version === "3.5" || model.version === "4" || model.version === "4s")
    );
}

function isModelJean(model: Model): model is ModelJean {
    return (
        "think" in model &&
        (model.version === "2" || model.version === "3" || model.version === "3.14")
    );
}

export function activateModel(model: Model) {
    if (isModelSkippity(model)) return `Activated model Skippity version ${model.version} with searching ${model.search}`;
    else return `Activated model Skippity version ${model.version} with thinking ${model.think}`;
}
