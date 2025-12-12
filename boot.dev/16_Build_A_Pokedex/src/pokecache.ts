export class Cache {
    #cache = new Map<string, CacheEntry<any>>();
    #reapIntervalId: NodeJS.Timeout | undefined = undefined;
    #interval: number;

    constructor(interval: number) {
        this.#interval = interval;
        this.#startReapLoop();
    }

    add<T>(key: string, val: T) {
        let cacheEntry = {
            createdAt: Date.now(),
            val
        } as CacheEntry<T>;
        this.#cache.set(key, cacheEntry);
    }
    get<T>(key: string): CacheEntry<T> | undefined {
        return this.#cache.get(key) as CacheEntry<T> | undefined;
    }

    stopReadLoop() {
        clearInterval(this.#reapIntervalId);
        this.#reapIntervalId = undefined;
    }
    #startReapLoop() {
        this.#reapIntervalId = setInterval(() => this.#reap, this.#interval);
    }
    #reap() {
        this.#cache.forEach((entry, key, map) => {
            if (entry.createdAt < Date.now() - this.#interval)
                map.delete(key);
        });
    }
}

type CacheEntry<T> = {
    createdAt: number // Date.now()
    val: T
};
