export class FeatureFlag<T extends string> {
    #flags: Set<T>;

    constructor() {
        this.#flags = new Set();
    }

    enable(flag: T): void {
        this.#flags.add(flag);
    }

    isEnabled(flag: T) {
        return this.#flags.has(flag);
    }
}
