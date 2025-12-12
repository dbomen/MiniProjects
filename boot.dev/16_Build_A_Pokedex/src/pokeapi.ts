import { Cache } from "./pokecache.js";

export class PokeAPI {
    private static readonly baseURL = "https://pokeapi.co/api/v2";
    private static cache = new Cache(10000);

    async fetchLocations(pageURL?: string): Promise<ShallowLocations> {
        const url = pageURL
            ? pageURL
            : `${PokeAPI.baseURL}/location-area`;

        // try to get from cache
        let cacheEntry = PokeAPI.cache.get<ShallowLocations>(url);
        if (cacheEntry) {
            console.log("Used cache");
            return cacheEntry.val;
        }

        const res = await fetch(url);
        const data = await res.json();
        // put in cache to get from cache
        PokeAPI.cache.add<ShallowLocations>(url, data);

        return data;
    }

    async fetchLocation(location: string): Promise<Location> {
        const url = `${PokeAPI.baseURL}/location-area/${location}`;

        // try to get from cache
        let cacheEntry = PokeAPI.cache.get<Location>(url);
        if (cacheEntry) {
            console.log("Used cache");
            return cacheEntry.val;
        }

        const res = await fetch(url);
        const data = await res.json();
        // put in cache to get from cache
        PokeAPI.cache.add<Location>(url, data);

        return data;
    }

    async fetchPokemon(pokemon: string): Promise<Pokemon> {
        const url = `${PokeAPI.baseURL}/pokemon/${pokemon}`;

        // try to get from cache
        let cacheEntry = PokeAPI.cache.get<Pokemon>(url);
        if (cacheEntry) {
            console.log("Used cache");
            return cacheEntry.val;
        }

        const res = await fetch(url);
        const data = await res.json();
        // put in cache to get from cache
        PokeAPI.cache.add<Pokemon>(url, data);

        return data;
    }
}

export type ShallowLocations = {
    next: string, // URL to next
    previous: string, // URL to prev
    results: Location[]
}

export type Location = {
    id: number,
    name: string,
    game_index: number,
    pokemon_encounters: PokemonEntry[]
};
type PokemonEntry = {
    pokemon: {
        name: string,
        url: string
    }
}

export type Pokemon = {
    base_experience: number
}
